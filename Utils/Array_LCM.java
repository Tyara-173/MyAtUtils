long arrayLCM(long... a){
    long ret = a[0];
    for (long l : a) {
        ret = lcm(ret,l);
    }
    return ret;
}