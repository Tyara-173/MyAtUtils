class BinaryNum{
    public static char[] toBinary(long decimalNum, boolean revese){
        long t = decimalNum;
        StringBuilder str = new StringBuilder();
        while (t > 0){
            str.append(t & 1);
            t >>= 1;
        }
        if(revese){
            str.reverse();
        }
        return str.toString().toCharArray();
    }
    public static long toDecimal(char[] binaryNum){
        long t = 1;
        long sum = 0;
        for (char c : binaryNum) {
            if(c == '1'){
                sum += t;
            }
            t <<= 1;
        }
        return sum;
    }
}