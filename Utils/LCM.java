long lcm(long a,long b){
    return a / gcd(a,b) * b;
}