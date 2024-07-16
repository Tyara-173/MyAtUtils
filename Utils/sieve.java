List<Long> sieve(int n){
    List<Long> list = new ArrayList<>();
    boolean[] a = new boolean[n+1];
    for (int i = 2; i <= n; i++) {
        if(!a[i]){
            for (int j = i; j <= n; j+=i) {
                a[j] = true;
            }
            list.add((long)i);
        }
    }
    return list;
}