long pow(long a, long b, long mod) {
    long ret = 1L;
    while (b > 0) {
        if ((b & 1)==1){
            ret *= a;
            ret %= mod;
        }
        a *= a;
        a %= mod;
        b >>= 1;
    }
    return ret;
}