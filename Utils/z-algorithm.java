    int[] z_algo(char[] s){
        int n = s.length;
        int[] z = new int[n];
        int l = 0;
        int r = 0;
        for (int i = 1; i < n; i++) {
            if(z[i-l] < r - i){
                z[i] = z[i-l];
            }else{
                r = Math.max(r,i);
                while (r < n && s[r] == s[r-i]){
                    r++;
                }
                z[i] = r-i;
                l = i;
            }
        }
        return z;
    }