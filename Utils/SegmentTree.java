
class SegmentTree<T>{
    private final int size;
    private final List<T> dat;
    private final Monoid<T> monoid;

    SegmentTree(int size,BinaryOperator<T> operator,T identity){
        int x = 1;
        while (x < size){
            x *= 2;
        }
        this.size = x;
        this.dat = new ArrayList<>();
        monoid = new Monoid<>(identity,operator);
    }

    SegmentTree(int size,Monoid<T> monoid){
        int x = 1;
        while (x < size){
            x *= 2;
        }
        this.size = x;
        this.dat = new ArrayList<>();
        this.monoid = monoid;
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

    class Monoid<T>{
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
    }
}
