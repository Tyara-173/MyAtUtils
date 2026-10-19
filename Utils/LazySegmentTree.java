// バグってるかも
class LazySegmentTree<T,U>{
    private final int size;
    private final List<T> dat;
    private final List<U> lazy;
    private Monoid<T> monoid;
    private Monoid<U> operatorMonoid;
    private Modifier<T,U> modifier;

    LazySegmentTree(int n, Monoid<T> monoid, Monoid<U> operatorMonoid, Modifier<T,U> modifier){
        this.dat = new ArrayList<>();
        this.lazy = new ArrayList<>();
        this.size = Integer.highestOneBit(n * 2 - 1);
        for (int i = 0; i < this.size*2; i++) {
            dat.add(monoid.identity());
            lazy.add(operatorMonoid.identity());
        }
        this.monoid = monoid;
        this.operatorMonoid = operatorMonoid;
        this.modifier = modifier;
    }

    LazySegmentTree(int n, Monoid<T> monoid, Monoid<U> operatorMonoid, Modifier<T,U> modifier, U[] a){
        this(n,monoid,operatorMonoid,modifier);
        for (int i = 0; i < n; i++) {
            this.update(i,i+1,a[i]);
        }
    }

    void eval(int k,int len) {
        if (lazy.get(k).equals(operatorMonoid.identity())) return;
        if (k < size - 1) {
//            lazy[k * 2 + 1] += lazy[k] / 2;
//            lazy[k * 2 + 2] += lazy[k] / 2;
//            lazy[k * 2 + 1] = lazy[k];
//            lazy[k * 2 + 2] = lazy[k];
            lazy.set(k*2+1, operatorMonoid.operator(lazy.get(k*2+1),lazy.get(k)));
            lazy.set(k*2+2, operatorMonoid.operator(lazy.get(k*2+2),lazy.get(k)));
        }
        // 自身を更新
        dat.set(k, modifier.modify(dat.get(k), lazy.get(k), len));
        lazy.set(k, operatorMonoid.identity());
    }

    void update(int a, int b, U x, int k, int l, int r) {
        eval(k,r-l);
        if (a <= l && r <= b) {  // 完全に内側の時
//            lazy[k] += x * (r - l);
//            lazy[k] = x;
            lazy.set(k, operatorMonoid.operator(lazy.get(k),x));
            eval(k,r-l);
        } else if (a < r && l < b) {                     // 一部区間が被る時
            update(a, b, x, k * 2 + 1, l, (l + r) / 2);  // 左の子
            update(a, b, x, k * 2 + 2, (l + r) / 2, r);  // 右の子
//            dat[k] = f(dat[k * 2 + 1] , dat[k * 2 + 2]);
            dat.set(k,monoid.operator(dat.get(k*2+1),dat.get(k*2+2)));
        }
    }

    void update(int a, int b, U x) { update(a, b, x, 0, 0, size); }

    T query(int a,int b){
        return query(a,b,0,0,size);
    }

    T query(int a,int b,int k,int l,int r){
        eval(k,r-l);
        if(r <= a || b <= l){
            return monoid.identity();
        }
        if(a <= l && r <= b){
            return dat.get(k);
        }
        T vl = query(a,b,k*2+1,l,(l+r)/2);
        T vr = query(a,b,k*2+2,(l+r)/2,r);
        return monoid.operator(vl,vr);
    }

    List<List<T>> getDat(){
        int x = 1;
        int index = 0;
        List<List<T>> lists = new ArrayList<>();
        while (x <= size){
            List<T> list = new ArrayList<>();
            for (int i = 0; i < x; i++) {
                list.add(dat.get(index));
                index++;
            }
            lists.add(list);
            x *= 2;
        }
        return lists;
    }

    interface Modifier<T, U> {
        T modify(T t, U u, int length);
    }

    // Range Add Range Sum
    static LazySegmentTree<Long, Long> createRSQSUM(int n){
        return new LazySegmentTree<>(
                n,
                Monoid.SUM,
                Monoid.SUM,
                (t, u, len) -> t + (u * len)
        );
    }

    // Range Update Range Sum
    static LazySegmentTree<Long, Long> createRUQSUM(int n){
        return new LazySegmentTree<>(
                n,
                Monoid.SUM,
                new Monoid<>(
                        Long.MAX_VALUE,
                        (a, b) -> b.equals(Long.MAX_VALUE) ? a : b
                ),
                (t, u, len) -> u.equals(Long.MAX_VALUE) ? t : u * len
        );
    }

    // Range Add Range Max
    static LazySegmentTree<Long, Long> createRSQMAX(int n){
        return new LazySegmentTree<>(
                n,
                Monoid.MAX,
                Monoid.SUM,
                (t, u, len) -> t + u
        );
    }

    // Range Update Range Max
    static LazySegmentTree<Long, Long> createRUQMAX(int n){
        return new LazySegmentTree<>(
                n,
                Monoid.MAX,
                new Monoid<>(
                        Long.MAX_VALUE,
                        (a, b) -> b.equals(Long.MAX_VALUE) ? a : b
                ),
                (t, u, len) -> u.equals(Long.MAX_VALUE) ? t : u
        );
    }

    // Range Add Range Min
    static LazySegmentTree<Long, Long> createRSQMIN(int n){
        return new LazySegmentTree<>(
                n,
                Monoid.MIN,
                Monoid.SUM,
                (t, u, len) -> t + u
        );
    }

    // Range Update  Range Min
    static LazySegmentTree<Long, Long> createRUQMIN(int n){
        return new LazySegmentTree<>(
                n,
                Monoid.MIN,
                new Monoid<>(
                        Long.MAX_VALUE,
                        (a, b) -> b.equals(Long.MAX_VALUE) ? a : b
                ),
                (t, u, len) -> u.equals(Long.MAX_VALUE) ? t : u
        );
    }

    static class Monoid<T>{
        private T identity;
        private Supplier<T> identitySupplier;
        private final BinaryOperator<T> operator;

        Monoid(T identity,BinaryOperator<T> operator){
            this.identity = identity;
            this.operator = operator;
        }

        Monoid(Supplier<T> identitySupplier,BinaryOperator<T> operator){
            this.identitySupplier = identitySupplier;
            this.operator = operator;
        }

        T identity(){
            return identitySupplier == null ? identity : identitySupplier.get();
        }

        T operator(T a,T b){
            return operator.apply(a,b);
        }

        static Monoid<Long> MIN = new Monoid<>(Long.MAX_VALUE,Long::min);
        static Monoid<Long> MAX = new Monoid<>(Long.MIN_VALUE,Long::max);
        static Monoid<Integer> MIN_INT = new Monoid<>(Integer.MAX_VALUE,Integer::min);
        static Monoid<Integer> MAX_INT = new Monoid<>(Integer.MIN_VALUE,Integer::max);

        static Monoid<Long> SUM = new Monoid<>(0L, Long::sum);
        static Monoid<Long> PROD = new Monoid<>(1L,(a, b) -> a * b);
        static Monoid<Long> SUM_MOD = new Monoid<>(0L, (a, b) -> (a + b) % Main.mod);
        static Monoid<Long> PROD_MOD = new Monoid<>(1L, (a, b) -> a * b % Main.mod);

        static Monoid<Long> GCD = new Monoid<>(0L,(a, b) -> {
            if (b == 0) {
                return a;
            }
            while (a % b != 0) {
                long t = a % b;
                a = b;
                b = t;
            }
            return b;
        });
        static Monoid<Long> LCM = new Monoid<>(1L,(a, b) -> (a * b) / GCD.operator(a, b));
        static Monoid<Long> XOR = new Monoid<>(0L, (a, b) -> a ^ b);

        static Monoid<Long> RSQ = new Monoid<>(Long.MAX_VALUE,(a, b) -> b.equals(Long.MAX_VALUE) ? a : b);
    }
}