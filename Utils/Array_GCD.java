long arrayGCD(long... a){
    long ret = a[0];
    for (long l : a) {
        ret = gcd(ret,l);
    }
    return ret;
}