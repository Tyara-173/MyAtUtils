class SegmentTree{
    private final int size;
    private final long[] dat;
    SegmentTree(int size,long[] a){
        this.dat = new long[size*4];
        int x = 1;
        while (x < size){
            x *= 2;
        }
        this.size = x;
        for (int i = 0; i < size; i++) {
            update(i,a[i]);
        }
    }

    void update(int index,long x){
        int i = index;
        i += size-1;
        dat[i] = x;
        while (i > 0) {
            i = (i - 1) / 2;
            dat[i] = Math.min(dat[i * 2 + 1] , dat[i * 2 + 2]);
        }
    }

    long query(int a,int b){
        return query(a,b,0,0,size);
    }

    long query(int a,int b,int k,int l,int r){
        if(r <= a || b <= l){
            return 0;
        }
        if(a <= l && r <= b){
            return dat[k];
        }
        long vl = query(a,b,k*2+1,l,(l+r)/2);
        long vr = query(a,b,k*2+2,(l+r)/2,r);
        return Math.min(vl, vr);
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