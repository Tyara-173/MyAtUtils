class LazySegmentTree{
    long inf = Long.MAX_VALUE;
    private final int size;
    private final long[] dat;
    private final long[] lazy;
    LazySegmentTree(int size){
        this.dat = new long[size*4];
        this.lazy = new long[size*4];
        int x = 1;
        while (x < size){
            x *= 2;
        }
        this.size = x;
    }

    void eval(int k) {
        if (lazy[k] == 0) return;
        if (k < size - 1) {
            lazy[k * 2 + 1] += lazy[k] / 2;
            lazy[k * 2 + 2] += lazy[k] / 2;
//            lazy[k * 2 + 1] = lazy[k];
//            lazy[k * 2 + 2] = lazy[k];
        }
        // 自身を更新
        dat[k] += lazy[k];
        lazy[k] = 0;
    }

    void update(int a, int b, long x, int k, int l, int r) {
        eval(k);
        if (a <= l && r <= b) {  // 完全に内側の時
            lazy[k] += x * (r - l);
//            lazy[k] = x;
            eval(k);
        } else if (a < r && l < b) {                     // 一部区間が被る時
            update(a, b, x, k * 2 + 1, l, (l + r) / 2);  // 左の子
            update(a, b, x, k * 2 + 2, (l + r) / 2, r);  // 右の子
            dat[k] = f(dat[k * 2 + 1] , dat[k * 2 + 2]);
        }
    }

    void update(int a, int b, long x) { update(a, b, x, 0, 0, size); }

    long query(int a,int b){
        return query(a,b,0,0,size);
    }

    long query(int a,int b,int k,int l,int r){
        eval(k);
        if(r <= a || b <= l){
            return 0;
        }
        if(a <= l && r <= b){
            return dat[k];
        }
        long vl = query(a,b,k*2+1,l,(l+r)/2);
        long vr = query(a,b,k*2+2,(l+r)/2,r);
        return f(vl,vr);
    }
    long f(long a,long b){
        return a+b;
    }
    List<List<Long>> getDat(){
        int x = 1;
        int index = 0;
        List<List<Long>> lists = new ArrayList<>();
        while (x <= size){
            List<Long> list = new ArrayList<>();
            for (int i = 0; i < x; i++) {
                list.add(dat[index]);
                index++;
            }
            lists.add(list);
            x *= 2;
        }
        return lists;
    }
}