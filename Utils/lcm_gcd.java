public class lcm_gcd {
    long lcm(long a,long b){
        return a / gcd(a,b) * b;
    }
    long gcd(long a,long b){
        return b == 0 ? a : gcd(b,a%b);
    }
    long arrayLCM(long... a){
        long ret = a[0];
        for (long l : a) {
            ret = lcm(ret,l);
        }
        return ret;
    }


    long arrayGCD(long... a){
        long ret = a[0];
        for (long l : a) {
            ret = gcd(ret,l);
        }
        return ret;
    }
}
