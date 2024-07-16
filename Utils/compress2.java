LongPair[] compress(LongPair[] a){
    int n = a.length;
    long[] x = new long[n];
    long[] y = new long[n];
    for (int i = 0; i < n; i++) {
        y[i] = a[i].f;
        x[i] = a[i].s;
    }
    Arrays.sort(x);
    Arrays.sort(y);
    LongPair[] c = new LongPair[n];
    Map<Long,Long> xMap = new HashMap<>();
    Map<Long,Long> yMap = new HashMap<>();
    long temp = x[0];
    long now = 1;
    xMap.put(temp,0L);
    for (int i = 0; i < n; i++) {
        if(temp != x[i]){
            xMap.put(x[i],now++);
            temp = x[i];
        }
    }
    temp = y[0];
    now = 1;
    yMap.put(temp,0L);
    for (int i = 0; i < n; i++) {
        if(temp != y[i]){
            yMap.put(y[i],now++);
            temp = y[i];
        }
    }
    for (int i = 0; i < n; i++) {
        c[i] = new LongPair(yMap.get(a[i].f),xMap.get(a[i].s));
    }
    return c;
}