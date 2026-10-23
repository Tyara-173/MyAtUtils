// https://qiita.com/keymoon/items/11fac5627672a6d6a9f6
class RollingHash{
    private static final long mask30 = 0x3fffffff;
    private static final long mask31 = 0x7fffffff;
    private static final long mod = 0x1fffffffffffffffL;
    private static final long positivizer = 0x7ffffffffffffffcL;
    long base = 1665600673;
    private long[] hash;
    private List<Long> pow = new ArrayList<>();

    public RollingHash(char[] s){
        this.hash = new long[s.length + 1];
        for (int i = 0; i < s.length; i++) {
            hash[i + 1] = mod(mul(hash[i], base) + s[i]);
        }
        pow.add(1L);
    }

    public RollingHash(String s){
        this(s.toCharArray());
    }

    public long sub(int l,int r){
        return mod(hash[r] + positivizer - mul(hash[l], pow(r - l)));

    }

    public long add(long l,long r,int lLength){
        return mod(mul(r,pow(lLength))+l);
    }

    private long mul(long a, long b)
    {
        long au = a >> 31;
        long ad = a & mask31;
        long bu = b >> 31;
        long bd = b & mask31;
        long mid = ad * bu + au * bd;
        long midu = mid >> 30;
        long midd = mid & mask30;
        return mod(au * bu * 2 + midu + (midd << 31) + ad * bd);
    }

    private long mod(long x) {
        x = (x & mod) + (x >>> 61);
        return x >= mod ? x - mod : x;
    }

    private long pow(int x){
        while(pow.size() <= x){
            pow.add(mod(mul(pow.getLast(), base)));
        }
        return pow.get(x);
    }
}