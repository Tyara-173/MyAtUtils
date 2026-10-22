// by https://github.com/Tyara-173/MyAtUtils/blob/main/Utils/LazySegmentTreeLong.java
class LazySegmentTreeLong {
    private static final long MOD = 998244353;
    private static final byte NONE = 0, ADD = 1, SET = 2;
    private final int size;
    private final long[] max, min, sum, sumMod;
    private final int[] cnt;        // ノードが覆う実要素の個数
    private final byte[] lazyType;   // 子にまだ伝えていない作用の種類
    private final long[] lazy;

    LazySegmentTreeLong(int n) { this(n, new long[n]); }

    LazySegmentTreeLong(int n, long[] a) {
        int s = 1;
        while (s < n) s <<= 1;
        size = s;
        max = new long[2 * size];
        min = new long[2 * size];
        sum = new long[2 * size];
        sumMod = new long[2 * size];
        cnt = new int[2 * size];
        lazyType = new byte[2 * size];
        lazy = new long[2 * size];
        Arrays.fill(max, Long.MIN_VALUE);
        Arrays.fill(min, Long.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            int k = size + i;
            max[k] = min[k] = sum[k] = a[i];
            sumMod[k] = norm(a[i]);
            cnt[k] = 1;
        }
        for (int k = size - 1; k >= 1; k--) pull(k);
    }

    private static long norm(long v) {
        v %= MOD;
        return v < 0 ? v + MOD : v;
    }

    private void pull(int k) {
        int l = 2 * k, r = 2 * k + 1;
        max[k] = Math.max(max[l], max[r]);
        min[k] = Math.min(min[l], min[r]);
        sum[k] = sum[l] + sum[r];
        sumMod[k] = (sumMod[l] + sumMod[r]) % MOD;
        cnt[k] = cnt[l] + cnt[r];
    }

    // ノード k に作用を適用する（自分の値を更新し、タグを合成する）
    private void apply(int k, byte type, long v) {
        if (cnt[k] == 0) return; // パディング部分には触らない
        if (type == SET) {
            max[k] = min[k] = v;
            sum[k] = v * cnt[k];
            sumMod[k] = norm(v) * cnt[k] % MOD;
            lazyType[k] = SET;
            lazy[k] = v;
        } else { // ADD
            max[k] += v;
            min[k] += v;
            sum[k] += v * cnt[k];
            sumMod[k] = (sumMod[k] + norm(v) * cnt[k]) % MOD;
            if (lazyType[k] == NONE) {
                lazyType[k] = ADD;
                lazy[k] = v;
            } else {
                lazy[k] += v; // ADD+ADD → ADD,  SET(s)+ADD(a) → SET(s+a)
            }
        }
    }

    private void push(int k) {
        if (lazyType[k] == NONE) return;
        apply(2 * k, lazyType[k], lazy[k]);
        apply(2 * k + 1, lazyType[k], lazy[k]);
        lazyType[k] = NONE;
    }

    private void update(int a, int b, byte type, long v, int k, int l, int r) {
        if (b <= l || r <= a) return;
        if (a <= l && r <= b) {
            apply(k, type, v);
            return;
        }
        push(k);
        int m = (l + r) >>> 1;
        update(a, b, type, v, 2 * k, l, m);
        update(a, b, type, v, 2 * k + 1, m, r);
        pull(k);
    }

    void add(int a, int b, long x) { update(a, b, ADD, x, 1, 0, size); }
    void set(int a, int b, long x) { update(a, b, SET, x, 1, 0, size); }

    long getMax(int a, int b) { return getMax(a, b, 1, 0, size); }
    private long getMax(int a, int b, int k, int l, int r) {
        if (b <= l || r <= a) return Long.MIN_VALUE;
        if (a <= l && r <= b) return max[k];
        push(k);
        int m = (l + r) >>> 1;
        return Math.max(getMax(a, b, 2 * k, l, m), getMax(a, b, 2 * k + 1, m, r));
    }

    long getMin(int a, int b) { return getMin(a, b, 1, 0, size); }
    private long getMin(int a, int b, int k, int l, int r) {
        if (b <= l || r <= a) return Long.MAX_VALUE;
        if (a <= l && r <= b) return min[k];
        push(k);
        int m = (l + r) >>> 1;
        return Math.min(getMin(a, b, 2 * k, l, m), getMin(a, b, 2 * k + 1, m, r));
    }

    long getSum(int a, int b) { return getSum(a, b, 1, 0, size); }
    private long getSum(int a, int b, int k, int l, int r) {
        if (b <= l || r <= a) return 0;
        if (a <= l && r <= b) return sum[k];
        push(k);
        int m = (l + r) >>> 1;
        return getSum(a, b, 2 * k, l, m) + getSum(a, b, 2 * k + 1, m, r);
    }

    long getSumMod(int a, int b) { return getSumMod(a, b, 1, 0, size); }
    private long getSumMod(int a, int b, int k, int l, int r) {
        if (b <= l || r <= a) return 0;
        if (a <= l && r <= b) return sumMod[k];
        push(k);
        int m = (l + r) >>> 1;
        return (getSumMod(a, b, 2 * k, l, m) + getSumMod(a, b, 2 * k + 1, m, r)) % MOD;
    }
}