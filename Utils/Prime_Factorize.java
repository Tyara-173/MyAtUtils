List<Long> primeFactorize(long n){
    List<Long> list = new ArrayList<>();
    for (long i = 2; i*i <= n; i++) {
        while (n % i == 0){
            list.add(i);
            n /= i;
        }
    }
    if(n != 1){
        list.add(n);
    }
    return list;
}