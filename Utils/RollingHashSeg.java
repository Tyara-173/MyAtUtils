
class SegmentTree{
    private final int size;
    private final T[] dat;
    int B = 1;
    long[] mods = {998244353L, 1000000007L, 1000000009L, 1000000021L, 1000000033L};
    long[] base = new long[B];

    SegmentTree(int size,char[] a){
        this.dat = new T[size*4];
        for (int i = 0; i < size * 4; i++) {
            this.dat[i] = e();
        }
        int x = 1;
        while (x < size){
            x *= 2;
        }
        this.size = x;
        Random rand = new Random();
        for (int i = 0; i < B; i++) {
            base[i] = rand.nextLong() % mods[i];
        }

        for (int i = 0; i < size; i++) {
            update(i,gen((a[i])));
        }
    }

    void update(int index,T x){
        int i = index;
        i += size-1;
        dat[i] = x;
        while (i > 0) {
            i = (i - 1) / 2;
            dat[i] = f(dat[i * 2 + 1] , dat[i * 2 + 2]);
        }
    }

    T query(int a,int b){
        return query(a,b,0,0,size);
    }

    T query(int a,int b,int k,int l,int r){
        if(r <= a || b <= l){
            return e();
        }
        if(a <= l && r <= b){
            return dat[k];
        }
        T vl = query(a,b,k*2+1,l,(l+r)/2);
        T vr = query(a,b,k*2+2,(l+r)/2,r);
        return f(vl, vr);
    }

    T f(T a,T b){
        T res = e();
        for (int i = 0; i < B; i++) {
            res.h1[i] = (a.h1[i] * b.pw[i] + b.h1[i]) % mods[i];
            res.h2[i] = (a.h2[i] + a.pw[i] * b.h2[i]) % mods[i];
            res.pw[i] = a.pw[i] * b.pw[i] % mods[i];
        }
        return res;
    }

    T e(){
        T res = new T();
        res.h1 = new long[5];
        res.h2 = new long[5];
        res.pw = new long[5];
        for (int i = 0; i < B; i++) {
            res.h1[i] = 0;
            res.h2[i] = 0;
            res.pw[i] = 1;
        }
        return res;
    }

    T gen(char c){
        T res = e();
        for (int i = 0; i < B; i++) {
            res.h1[i] = c;
            res.h2[i] = c;
            res.pw[i] = base[i];
        }
        return res;
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
}

class T{
    long[] h1;
    long[] h2;
    long[] pw;
}
