long[] cumulativeSum(long[] a){
    long[] b = new long[a.length+1];
    for (int i = 1; i <= a.length; i++) {
        b[i] = b[i-1] + a[i-1];
    }
    return b;
}
