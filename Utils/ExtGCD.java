long[] extGCD(long a,long b){
    if(b == 0){
        return new long[]{1,0};
    }
    long[] t = extGCD(b,a%b);
    long tt = t[0];
    t[0] = t[1];
    t[1] = tt;
    t[1] -= a / b * t[0];
    return t;
}