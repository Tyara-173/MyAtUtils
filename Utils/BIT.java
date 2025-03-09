class BIT {
    int n;
    long[][] bit;
    BIT(int n) {
        this.n = n+1;
        bit = new long[2][n+1];
    }

    void add_sub(int p, int i, long x) {
        for (int idx = i; idx < n; idx += (idx & -idx)) {
            bit[p][idx] += x;
        }
    }
    void add(int l, int r, long x) {  // [l,r)
        add_sub(0, l, -x * (l - 1));
        add_sub(0, r, x * (r - 1));
        add_sub(1, l, x);
        add_sub(1, r, -x);
    }
    long sum_sub(int p, int i) {
        long s = 0;
        for (int idx = i; idx > 0; idx -= (idx & -idx)) {
            s += bit[p][idx];
        }
        return s;
    }
    long sum(int i) {
        return sum_sub(0, i) + sum_sub(1, i) * i;
    }
}