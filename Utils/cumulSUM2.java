long[][] cumulativeSum2(long[][] a){
    int n = a.length;
    int m = a[0].length;
    long[][] b = new long[n+1][m+1];
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            b[i][j] = b[i][j-1] + a[i-1][j-1];
        }
    }
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            b[i][j] += b[i-1][j];
        }
    }
    return b;
}
