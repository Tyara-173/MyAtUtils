int doubling(int[] a,long k){
    int n = a.length;
    int[][] dp = new int[64][n];
    for (int i = 0; i < n; i++) {
        dp[0][i] = a[i];
    }
    for (int i = 1; i < 64; i++) {
        for (int j = 0; j < n; j++) {
            dp[i][j] = dp[i-1][dp[i-1][j]];
        }
    }
    int ans = 0;
    for (int i = 0; i < 64; i++) {
        if(((k >> i) & 1) == 1){
            ans = dp[i][ans];
        }
    }
    return ans;
}