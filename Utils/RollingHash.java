class RollingHash {
    private static final int BASE = 256; // 基数
    private static final int MOD = 1000000007; // 10^9 + 7
    private static final int PRIME = 31; // 素数

    long hash(String str) {
        long hashValue = 0;
        for (int i = 0; i < str.length(); i++) {
            hashValue = (hashValue * BASE + str.charAt(i)) % MOD;
        }
        return hashValue;
    }

    long rollingHash(String str, int oldIndex, int newIndex, long oldHash, int patternLength) {
        long newHash = (oldHash - str.charAt(oldIndex) * pow(BASE, patternLength - 1)) % MOD;
        newHash = (newHash * BASE + str.charAt(newIndex)) % MOD;
        return (newHash + MOD) % MOD;
    }

    private long pow(int base, int exponent) {
        long result = 1;
        for (int i = 0; i < exponent; i++) {
            result = (result * base) % MOD;
        }
        return result;
    }
}