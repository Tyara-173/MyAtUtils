public class lcm_gcd {
    long lcm(long a,long b){
        return a / gcd(a,b) * b;
    }
    long gcd(long a,long b){
        return b == 0 ? a : gcd(b,a%b);
    }
}
