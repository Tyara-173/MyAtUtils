long inv(long a) {
    long b = mod;
    long u = 1, v = 0;
    while (b >= 1) {
        long t = a / b;
        a -= t * b;
        u -= t * v;
        if (a < 1) {
            return (v %= mod) < 0 ? v + mod : v;
        }
        t = b / a;
        b -= t * a;
        v -= t * u;
    }
    return (u %= mod) < 0 ? u + mod : u;
}