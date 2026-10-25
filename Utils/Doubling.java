int[] doubling(int[] a,long k){
    int n = a.length;
    int[][] dp = new int[64][n];
    int[] ans = new int[n];
    for (int i = 0; i < n; i++) {
        dp[0][i] = a[i];
        ans[i] = i;
    }
    for (int i = 1; i < 64; i++) {
        for (int j = 0; j < n; j++) {
            dp[i][j] = dp[i-1][dp[i-1][j]];
        }
    }
    for (int i = 0; i < 64; i++) {
        if(((k >> i) & 1) == 1){
            for (int j = 0; j < n; j++) {
                ans[j] = dp[i][ans[j]];
            }
        }
    }
    return ans;
}