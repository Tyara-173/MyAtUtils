from nicegui import ui
import pyperclip

utils = {
    'graph_1':
        '''
            int n = in.nextInt();
            int m = in.nextInt();
            List<List<Integer>> e = new ArrayList<>();
            boolean[] a = new boolean[n];
        
            void solve() {
                for (int i = 0; i < n; i++) {
                    e.add(new ArrayList<>());
                }
                for (int i = 0; i < m; i++) {
                    int u = in.nextInt()-1;
                    int v = in.nextInt()-1;
                    e.get(u).add(v);
                    e.get(v).add(u);
                }
            }
        ''',
    'graph_2':
        '''
            int n = in.nextInt();
            int m = in.nextInt();
            List<List<LongPair>> e = new ArrayList<>();
            boolean[] a = new boolean[n];
        
            void solve() {
                for (int i = 0; i < n; i++) {
                    e.add(new ArrayList<>());
                }
                for (int i = 0; i < m; i++) {
                    int u = in.nextInt()-1;
                    int v = in.nextInt()-1;
                    long t = in.nextLong();
                    e.get(u).add(new LongPair(v,t));
                    e.get(v).add(new LongPair(u,t));
                }
            }
        ''',
    'dfs':
        '''
            void dfs(int num){
                a[num] = true;
                for (Integer i : e.get(num)) {
                    if(!a[i]){
                        dfs(i);
                    }
                }
            }
        ''',
    'bfs':
        '''
            long[] cost = new long[n];
            Arrays.fill(cost,Long.MAX_VALUE);
            Deque<Integer> q = new ArrayDeque<>();
            q.add(0);
            while (!q.isEmpty()){
                int num = q.poll();
                for (Integer i : e.get(num)) {
                    if(cost[i] > cost[num] + 1){
                        q.add(i);
                        cost[i] = cost[num] + 1;
                    }
                }
            }
        ''',
    'dijkstra':
        '''    
            long[] dijkstra(int n,int start,List<List<LongPair>> e){
                long[] ret = new long[n];
                Arrays.fill(ret,Long.MAX_VALUE);
                PriorityQueue<LongPair> queue = new PriorityQueue<>(Comparator.comparingLong(q -> q.s));
                queue.add(new LongPair(start,0));
                ret[start] = 0;
                while (!queue.isEmpty()){
                    LongPair num = queue.poll();
                    int p = (int)num.f;
                    if(ret[p] < num.s){
                        continue;
                    }
                    for (LongPair edge : e.get(p)) {
                        if(ret[(int)edge.f] > num.s + edge.s){
                            ret[(int)edge.f] = num.s + edge.s;
                            queue.add(new LongPair((int)edge.f,num.s + edge.s));
                        }
                    }
                }
                return ret;
            }
        ''',
    'neighbor4':
        '''
            List<IntPair> neighbor4(int y, int x, int h, int w){
                int[] yy = {1, 0,-1, 0};
                int[] xx = {0, 1, 0,-1};
                List<IntPair> list = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    int ny = y + yy[i];
                    int nx = x + xx[i];
                    if(ny >= 0 && ny < h && nx >= 0 && nx < w){
                        list.add(new IntPair(ny,nx));
                    }
                }
                return list;
            }
        ''',
    'neighbor8':
        '''
            List<IntPair> neighbor8(int y, int x, int h, int w){
                int[] yy = {1,1,0,-1,-1,-1, 0, 1};
                int[] xx = {0,1,1, 1, 0,-1,-1,-1};
                List<IntPair> list = new ArrayList<>();
                for (int i = 0; i < 8; i++) {
                    int ny = y + yy[i];
                    int nx = x + xx[i];
                    if(ny >= 0 && ny < h && nx >= 0 && nx < w){
                        list.add(new IntPair(ny,nx));
                    }
                }
                return list;
            }
        ''',
    'lcm':
        '''
            long lcm(long a,long b){
                return a / gcd(a,b) * b;
            }
            
            long gcd(long a,long b){
                return b == 0 ? a : gcd(b,a%b);
            }
        ''',
    'arraylcm':
        '''
            long arrayLCM(long... a){
                long ret = a[0];
                for (long l : a) {
                    ret = lcm(ret,l);
                }
                return ret;
            }
            
            long lcm(long a,long b){
                return a / gcd(a,b) * b;
            }
            
            long gcd(long a,long b){
                return b == 0 ? a : gcd(b,a%b);
            }
        ''',
    'gcd':
        '''
            long gcd(long a,long b){
                return b == 0 ? a : gcd(b,a%b);
            }
        ''',
    'arraygcd':
        '''
            long arrayGCD(long... a){
                long ret = a[0];
                for (long l : a) {
                    ret = gcd(ret,l);
                }
                return ret;
            }
            
            long gcd(long a,long b){
                return b == 0 ? a : gcd(b,a%b);
            }
        ''',
    'Nary':
        '''
        class NAryNumber{
            public static char[] toNary(long decimalNum,long N){
                long t = decimalNum;
                StringBuilder str = new StringBuilder();
                while (t > 0){
                    str.append((char)(t % N));
                    t /= N;
                }
                return str.reverse().toString().toCharArray();
            }
            public static long toDecimal(char[] NAryNumber,long N){
                long t = 1;
                long sum = 0;
                for (char c : NAryNumber) {
                    sum += (c - '0') * t;
                    t *= N;
                }
                return sum;
            }
        }
        ''',
    'binary':
        '''
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
        ''',
    'inv':
        '''
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
        ''',
    'bit':
        '''
            for (int i = 0; i < 1 << n; i++) {
                for (int j = 0; j < n; j++) {
                    if(((i >> j) & 1) == 1){
    
                    }else{
    
                    }
                }
            }
        ''',
    'lowerBound':
        '''
            int lowerBound(long[] a,long x){
                int left = -1;
                int right = a.length;
                while (right - left > 1){
                    int mid = (right + left) / 2;
                    if(a[mid] < x){
                        left = mid;
                    }else{
                        right = mid;
                    }
                }
                return right;
            }
        ''',
    'upperBound':
        '''
            int upperBound(long[] a,long x){
                int left = -1;
                int right = a.length;
                while (right - left > 1){
                    int mid = (right + left) / 2;
                    if(a[mid] <= x){
                        left = mid;
                    }else{
                        right = mid;
                    }
                }
                return right;
            }
        ''',
    'pair':
        '''
            class Pair<T>{
                T f;
                T s;
                Pair(T f,T s){
                    this.f = f;
                    this.s = s;
                }
            }
        ''',
    'intPair':
        '''
            class IntPair implements Comparable<IntPair>{
                int f;
                int s;
                IntPair(int f,int s){
                    this.f = f;
                    this.s = s;
                }
            
                @Override
                public int compareTo(IntPair o) {
                    return o.f == this.f ? Integer.compare(this.s,o.s) : Integer.compare(this.f,o.f);
                }
                
                @Override
                public String toString(){
                    return " " + this.f + " " + this.s + " ";
                }
            }
        ''',
    'longPair':
        '''
            class LongPair implements Comparable<LongPair>{
                long f;
                long s;
                LongPair(long f,long s){
                    this.f = f;
                    this.s = s;
                }
            
                @Override
                public int compareTo(LongPair o) {
                    return o.f == this.f ? Long.compare(this.s,o.s) : Long.compare(this.f,o.f);
                }
                
                @Override
                public String toString(){
                    return " " + this.f + " " + this.s + " ";
                }
            }
        ''',
    'NodePair':
        '''
            class NodePair implements Comparable<NodePair>{
                int index;
                long weight;
                NodePair(int index,long weight){
                    this.index = index;
                    this.weight = weight;
                }
            
                @Override
                public int compareTo(NodePair o) {
                    return o.index == this.index ? Long.compare(this.weight,o.weight) : Integer.compare(this.index,o.index);
                }
            
                @Override
                public String toString(){
                    return " " + this.index + "," + this.weight + " ";
                }
            }
        ''',
    'segTree':
        '''
            class SegmentTree{
                private final int size;
                private final long[] dat;
                SegmentTree(int size,long[] a){
                    this.dat = new long[size*4];
                    int x = 1;
                    while (x < size){
                        x *= 2;
                    }
                    this.size = x;
                    for (int i = 0; i < size; i++) {
                        update(i,a[i]);
                    }
                }
            
                void update(int index,long x){
                    int i = index;
                    i += size-1;
                    dat[i] = x;
                    while (i > 0) {
                        i = (i - 1) / 2;
                        dat[i] = Math.min(dat[i * 2 + 1] , dat[i * 2 + 2]);
                    }
                }
            
                long query(int a,int b){
                    return query(a,b,0,0,size);
                }
            
                long query(int a,int b,int k,int l,int r){
                    if(r <= a || b <= l){
                        return 0;
                    }
                    if(a <= l && r <= b){
                        return dat[k];
                    }
                    long vl = query(a,b,k*2+1,l,(l+r)/2);
                    long vr = query(a,b,k*2+2,(l+r)/2,r);
                    return Math.min(vl, vr);
                }
                List<List<Long>> getDat(){
                    int x = 1;
                    int index = 0;
                    List<List<Long>> lists = new ArrayList<>();
                    while (x <= size){
                        List<Long> list = new ArrayList<>();
                        for (int i = 0; i < x; i++) {
                            list.add(dat[index]);
                            index++;
                        }
                        lists.add(list);
                        x *= 2;
                    }
                    return lists;
                }
            }
        ''',
    'lazyseg':
        '''
            class LazySegmentTree{
                long inf = Long.MAX_VALUE;
                private final int size;
                private final long[] dat;
                private final long[] lazy;
                LazySegmentTree(int size){
                    this.dat = new long[size*4];
                    this.lazy = new long[size*4];
                    int x = 1;
                    while (x < size){
                        x *= 2;
                    }
                    this.size = x;
                }
            
                void eval(int k) { // 配列のk番目を更新
                    if (lazy[k] == 0) return;  // 更新するものが無ければ終了
                    if (k < size - 1) {             // 葉でなければ子に伝搬
                        lazy[k * 2 + 1] = lazy[k];
                        lazy[k * 2 + 2] = lazy[k];
                    }
                    // 自身を更新
                    dat[k] = lazy[k];
                    lazy[k] = 0;
                }
            
                void update(int a, int b, long x, int k, int l, int r) {
                    eval(k);
                    if (a <= l && r <= b) {  // 完全に内側の時
                        lazy[k] = x;
                        eval(k);
                    } else if (a < r && l < b) {                     // 一部区間が被る時
                        update(a, b, x, k * 2 + 1, l, (l + r) / 2);  // 左の子
                        update(a, b, x, k * 2 + 2, (l + r) / 2, r);  // 右の子
                        dat[k] = Math.max(dat[k * 2 + 1], dat[k * 2 + 2]);
                    }
                }
                void update(int a, int b, long x) { update(a, b, x, 0, 0, size); }
            
                long query(int a,int b){
                    return query(a,b,0,0,size);
                }
            
                long query(int a,int b,int k,int l,int r){
                    eval(k);
                    if(r <= a || b <= l){
                        return 0;
                    }
                    if(a <= l && r <= b){
                        return dat[k];
                    }
                    long vl = query(a,b,k*2+1,l,(l+r)/2);
                    long vr = query(a,b,k*2+2,(l+r)/2,r);
                    return Math.max(vl, vr);
                }
                List<List<Long>> getDat(){
                    int x = 1;
                    int index = 0;
                    List<List<Long>> lists = new ArrayList<>();
                    while (x <= size){
                        List<Long> list = new ArrayList<>();
                        for (int i = 0; i < x; i++) {
                            list.add(dat[index]);
                            index++;
                        }
                        lists.add(list);
                        x *= 2;
                    }
                    return lists;
                }
            }
        ''',
    'unionfind':
        '''
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
        ''',
    'binaryTrie':
    '''
        class BinaryTrie{
            private static final int B = 63;
            Node root = new Node();
        
            public boolean add(long x){
                if(x < 0){
                    return false;
                }
                Node node = root;
                node.cnt++;
                node.sum += x;
                for (int i = B-1; i >= 0; i--) {
                    if(((x >> i) & 1) == 0){
                        if(node.left == null){
                            node.left = new Node();
                        }
                        node = node.left;
                    }else{
                        if(node.right == null){
                            node.right = new Node();
                        }
                        node = node.right;
                    }
                    node.cnt++;
                    node.sum += x;
                }
                return true;
            }
        
            public boolean remove(long x){
                if(x < 0 || !contains(x)){
                    return false;
                }
                Node node = root;
                node.cnt--;
                node.sum -= x;
                for (int i = B-1; i >= 0; i--) {
                    if(((x >> i) & 1) == 0){
                        node.left.cnt--;
                        node.left.sum -= x;
                        if(node.left.cnt == 0){
                            node.left = null;
                            return true;
                        }
                        node = node.left;
                    }else{
                        node.right.cnt--;
                        node.right.sum -= x;
                        if(node.right.cnt == 0){
                            node.right = null;
                            return true;
                        }
                        node = node.right;
                    }
                }
                return true;
        
            }
        
            long get(int i){
                if(i < 0 || i > size()){
                    return -1;
                }
                Node node = root;
                long val = 0;
                int k = i;
                for (int j = 0; j < B; j++) {
                    val <<= 1;
                    int now = node.left == null ? 0 : node.left.cnt;
                    if(k < now){
                        node = node.left;
                    }else{
                        node = node.right;
                        val++;
                        k -= now;
                    }
                    if(node == null){
                        break;
                    }
                }
                return val;
            }
            boolean contains(long x){
                return count(x) > 0;
            }
        
            int count(long x){
                if(x < 0){
                    return 0;
                }
                Node node = root;
                for (int i = B-1; i >= 0; i--) {
                    if(((x >> i) & 1) == 0){
                        if(node.left == null){
                            return 0;
                        }
                        node = node.left;
                    }else{
                        if(node.right == null){
                            return 0;
                        }
                        node = node.right;
                    }
                }
                return node.cnt;
            }
        
            long min(){
                Node node = root;
                long val = 0;
                for (int i = 0; i < B; i++) {
                    val <<= 1;
                    if(node.left != null){
                        node = node.left;
                    }else{
                        node = node.right;
                        val++;
                    }
                }
                return val;
            }
        
            long max(){
                Node node = root;
                long val = 0;
                for (int i = 0; i < B; i++) {
                    val <<= 1;
                    if(node.right != null){
                        node = node.right;
                        val++;
                    }else{
                        node = node.left;
                    }
                }
                return val;
            }
        
            long sum(){
                return root.sum;
            }
        
            long sum(int i){    //TODO
                if(i < 0 || i > size()){
                    return -1;
                }
                Node node = root;
                long val = 0;
                int k = i;
                for (int j = 0; j < B; j++) {
                    int now = node.left == null ? 0 : node.left.cnt;
                    if(k < now){
                        node = node.left;
                    }else{
                        val += node.left == null ? 0 : node.left.sum;
                        k -= now;
                        node = node.right;
                    }
                    if(node == null){
                        break;
                    }
                }
                return val;
            }
        
            void clear(){
                root.cnt = 0;
                root.sum = 0;
                root.left = null;
                root.right = null;
            }
        
            int size(){
                return root.cnt;
            }
            int length(){
                return size();
            }
            boolean isEmpty(){
                return size() == 0;
            }
            private static class Node{
                int cnt;
                long sum;
                Node left;
                Node right;
            }
        }
    ''',
    'cumul1':
        '''
            long[] cumulativeSum(long[] a){
                long[] b = new long[a.length+1];
                for (int i = 1; i <= a.length; i++) {
                    b[i] = b[i-1] + a[i-1];
                }
                return b;
            }
        ''',
    'cumul2':
        '''
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
        ''',
    'compress':
        '''
            long[] compress(long[] a){
                int n = a.length;
                long[] b = a.clone();
                Arrays.sort(b);
                long[] c = new long[n];
                Map<Long,Long> map = new HashMap<>();
                long temp = b[0];
                long now = 1;
                map.put(b[0],0L);
                for (int i = 0; i < n; i++) {
                    if(temp != b[i]){
                        map.put(b[i],now++);
                        temp = b[i];
                    }
                }
                for (int i = 0; i < n; i++) {
                    c[i] = map.get(a[i]);
                }
                return c;
            }
        ''',
    'compress2':
        '''
            LongPair[] compress(LongPair[] a){
                int n = a.length;
                long[] x = new long[n];
                long[] y = new long[n];
                for (int i = 0; i < n; i++) {
                    y[i] = a[i].f;
                    x[i] = a[i].s;
                }
                Arrays.sort(x);
                Arrays.sort(y);
                LongPair[] c = new LongPair[n];
                Map<Long,Long> xMap = new HashMap<>();
                Map<Long,Long> yMap = new HashMap<>();
                long temp = x[0];
                long now = 1;
                xMap.put(temp,0L);
                for (int i = 0; i < n; i++) {
                    if(temp != x[i]){
                        xMap.put(x[i],now++);
                        temp = x[i];
                    }
                }
                temp = y[0];
                now = 1;
                yMap.put(temp,0L);
                for (int i = 0; i < n; i++) {
                    if(temp != y[i]){
                        yMap.put(y[i],now++);
                        temp = y[i];
                    }
                }
                for (int i = 0; i < n; i++) {
                    c[i] = new LongPair(yMap.get(a[i].f),xMap.get(a[i].s));
                }
                return c;
            }
        ''',
    'sieve':
        '''    
            List<Long> sieve(int n){
                List<Long> list = new ArrayList<>();
                boolean[] a = new boolean[n+1];
                for (int i = 2; i <= n; i++) {
                    if(!a[i]){
                        for (int j = i; j <= n; j+=i) {
                            a[j] = true;
                        }
                        list.add((long)i);
                    }
                }
                return list;
            }
        ''',
    'mapUtil':
        '''    
            Map<Long,Integer> typeMap(long[] arr){
                Map<Long,Integer> map = new HashMap<>();
                for (long value : arr) {
                    add(map,value);
                }
                return map;
            }
        
            void add(Map<Long,Integer> map,long value){
                if(map.containsKey(value)){
                    map.put(value,map.get(value) + 1);
                }else{
                    map.put(value,1);
                }
            }
        ''',
    'permutation':
        '''
            class Permutation{
                private List<List<Integer>> per;
                public List<List<Integer>> getPermutation(int size){
                    if(per == null || per.size() != size){
                        per = new ArrayList<>();
                        makePermutation(size,size,new boolean[size],new ArrayList<>());
                    }
                    return per;
                }
            
                void makePermutation(int size,int d,boolean[] used,List<Integer> list){
                    if(d == 0){
                        per.add(new ArrayList<>(list));
                        return;
                    }
                    for (int i = 0; i < size; i++) {
                        if(!used[i]){
                            used[i] = true;
                            list.add(i);
                            makePermutation(size,d-1,used,list);
                            used[i] = false;
                            list.remove(list.size()-1);
                        }
                    }
                }
            
                List<Integer> nextPermutation(List<Integer> per){
                    int n = per.size();
                    List<Integer> list = new ArrayList<>(per);
                    TreeSet<Integer> set = new TreeSet<>();
                    int index = 0;
                    for (int i = 0; i < n-1; i++) {
                        if(list.get(i) < list.get(i+1)){
                            index = i;
                        }
                    }
                    for (int i = index; i < n; i++) {
                        set.add(list.get(i));
                    }
                    int temp = list.get(index)+1;
                    while (!set.contains(temp)){
                        temp++;
                    }
                    set.remove(temp);
                    list.set(index,temp);
                    for (int i = index+1; i < n; i++) {
                        list.set(i,set.first());
                        set.remove(set.first());
                    }
                    return list;
                }
            
                List<Integer> prevPermutation(List<Integer> per){
                    int n = per.size();
                    List<Integer> list = new ArrayList<>(per);
                    TreeSet<Integer> set = new TreeSet<>();
                    int index = 0;
                    for (int i = 0; i < n-1; i++) {
                        if(list.get(i) > list.get(i+1)){
                            index = i;
                        }
                    }
                    for (int i = index; i < n; i++) {
                        set.add(list.get(i));
                    }
                    int temp = list.get(index)-1;
                    while (!set.contains(temp)){
                        temp--;
                    }
                    set.remove(temp);
                    list.set(index,temp);
                    for (int i = index+1; i < n; i++) {
                        list.set(i,set.last());
                        set.remove(set.last());
                    }
                    return list;
                }
            }
        ''',

}


def copy(str):
    pyperclip.copy(utils[str])


with ui.card():
    ui.label('探索')
    with ui.row():
        ui.button('bit全探索', on_click=lambda: copy('bit'))
        ui.button('にぶたん_lower', on_click=lambda: copy('lowerBound'))
        ui.button('にぶたん_upper', on_click=lambda: copy('upperBound'))
        ui.button('順列全列挙', on_click=lambda: copy('permutation'))

with ui.card():
    ui.label('グラフアルゴリズム')
    with ui.row():
        ui.button('Input_重みなし', on_click=lambda: copy('graph_1'))
        ui.button('Input_重みあり', on_click=lambda: copy('graph_2'))
    with ui.row():
        ui.button('DFS', on_click=lambda: copy('dfs'))
        ui.button('BFS', on_click=lambda: copy('bfs'))
        ui.button('ダイクストラ', on_click=lambda: copy('dijkstra'))
    with ui.row():
        ui.button('隣接マス_4', on_click=lambda: copy('neighbor4'))
        ui.button('隣接マス_8', on_click=lambda: copy('neighbor8'))

with ui.card():
    ui.label('データ構造')
    with ui.row():
        ui.button('Pair', on_click=lambda: copy('pair'))
        ui.button('IntPair', on_click=lambda: copy('intPair'))
        ui.button('LongPair', on_click=lambda: copy('longPair'))
        ui.button('NodePair', on_click=lambda: copy('NodePair'))
    with ui.row():
        ui.button('セグ木', on_click=lambda: copy('segTree'))
        ui.button('遅延セグ木', on_click=lambda: copy('lazyseg'))
        ui.button('UnionFind', on_click=lambda: copy('unionfind'))
        ui.button('BinaryTrie', on_click=lambda: copy('binaryTrie'))


with ui.card():
    ui.label('数学系')
    with ui.row():
        ui.button('lcm', on_click=lambda: copy('lcm'))
        ui.button('lcm（配列）', on_click=lambda: copy('arraylcm'))
        ui.button('gcd', on_click=lambda: copy('gcd'))
        ui.button('gcd（配列）', on_click=lambda: copy('arraygcd'))
    with ui.row():
        ui.button('基数変換', on_click=lambda: copy('Nary'))
        ui.button('二進数変換', on_click=lambda: copy('binary'))
    with ui.row():
        ui.button('mod逆元', on_click=lambda: copy('inv'))
        ui.button('座標圧縮', on_click=lambda: copy('compress'))
        ui.button('二次元座標圧縮', on_click=lambda: copy('compress2'))
    with ui.row():
        ui.button('エラトステネスの篩', on_click=lambda: copy('sieve'))


with ui.card():
    ui.label('その他')
    with ui.row():
        ui.button('一次元累積和', on_click=lambda: copy('cumul1'))
        ui.button('二次元累積和', on_click=lambda: copy('cumul2'))
        ui.button('要素数Map', on_click=lambda: copy('mapUtil'))


ui.run()
