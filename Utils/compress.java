long[] compress(long[] a){
    int n = a.length;
    long[] b = a.clone();
    Arrays.sort(b);
    long[] c = new long[n];
    Map<Long,Long> map = new HashMap<>();
    long temp = b[0];
    long now = 1;
    map.put(b[0],0L);
    for (int i = 0; i < n; i++) {
        if(temp != b[i]){
            map.put(b[i],now++);
            temp = b[i];
        }
    }
    for (int i = 0; i < n; i++) {
        c[i] = map.get(a[i]);
    }
    return c;
}