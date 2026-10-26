class SegmentTree<T>{
    private final int size;
    private final List<T> dat;
    private final Monoid<T> monoid;

    SegmentTree(int n, Monoid<T> monoid){
        this.size = Integer.highestOneBit(n * 2 - 1);
        this.dat = new ArrayList<>();
        for (int i = 0; i < this.size*2; i++) {
            dat.add(monoid.identity());
        }
        this.monoid = monoid;
    }

    SegmentTree(int n, Monoid<T> monoid, IntFunction<T> initFunc){
        this(n,monoid);
        for (int i = 0; i < n; i++) {
            dat.set(i+size-1,initFunc.apply(i));
        }
        for (int i = size-2; i >= 0; i--) {
            dat.set(i,monoid.operator(dat.get(i*2+1),dat.get(i*2+2)));
        }
    }

    void update(int index,T x){
        int i = index;
        i += size-1;
        dat.set(i,x);
        while (i > 0) {
            i = (i - 1) / 2;
            dat.set(i, monoid.operator(dat.get(i * 2 + 1), dat.get(i * 2 + 2)));
        }
    }

    T query(int a,int b){
        return query(a,b,0,0,size);
    }

    T query(int a,int b,int k,int l,int r){
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

//    List<List<Long>> getDat(){
//        int x = 1;
//        int index = 0;
//        List<List<Long>> lists = new ArrayList<>();
//        while (x <= size){
//            List<Long> list = new ArrayList<>();
//            for (int i = 0; i < x; i++) {
//                list.add(dat[index]);
//                index++;
//            }
//            lists.add(list);
//            x *= 2;
//        }
//        return lists;
//    }

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
    }
}