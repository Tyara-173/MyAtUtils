class NAryNumber{
    public static char[] toNary(long decimalNum,long N){
        long t = decimalNum;
        StringBuilder str = new StringBuilder();
        while (t > 0){
            str.append((char)(t % N));
            t /= N;
        }
        return str.reverse().toString().toCharArray();
    }
    public static long toDecimal(char[] NAryNumber,long N){
        long t = 1;
        long sum = 0;
        for (char c : NAryNumber) {
            sum += (c - '0') * t;
            t *= N;
        }
        return sum;
    }
}