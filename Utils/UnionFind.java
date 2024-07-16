class UnionFind{

    int n;
    int m;
    int[] parent;
    int[] size;

    UnionFind(int n){
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        this.n = n;
        m = n;
    }

    public int root(int x){
        while (parent[x] != x){
            x = parent[x] = parent[parent[x]];
        }
        return x;
    }

    public boolean unite(int x,int y){
        x = root(x);
        y = root(y);
        if(x == y)return false;
        if(size[x] > size[y]){
            parent[y] = x;
            size[x] += size[y];
        }else{
            parent[x] = y;
            size[y] += size[x];
        }
        m--;
        return true;
    }

    @Override
    public String toString(){
        List<StringJoiner> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            list.add(new StringJoiner(" "));
        }
        int[] p = new int[n];
        Arrays.fill(p,m);
        int l = 0;
        for (int i = 0; i < m; i++) {
            if(p[root(i)] == m){
                p[root(i)] = l;
                l++;
            }
            list.get(p[root(i)]).add(i+"");
        }
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < m; i++) {
            s.append(list.get(i));
        }
        return s.toString();
    }
}