from nicegui import ui
import pyperclip

utils = {
    'graph_1':
        '''
            int n = 10;
            int m = 10;
            List<List<Integer>> e = new ArrayList<>();
            boolean[] a = new boolean[n];
        
            void solve() {
                for (int i = 0; i < n; i++) {
                    e.add(new ArrayList<>());
                }
                for (int i = 0; i < m; i++) {
                    int u = 0;
                    int v = 0;
                    e.get(u).add(v);
                    e.get(v).add(u);
                }
            }
        ''',
    'graph_2':
        '''
            int n = 10;
            int m = 10;
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
    'upeerBound':
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
                    return o.f == this.f ? Integer.compare(o.s,this.s) : Integer.compare(o.f,this.f);
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
                    return o.f == this.f ? Long.compare(o.s,this.s) : Long.compare(o.f,this.f);
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
}


def copy(str):
    pyperclip.copy(utils[str])


with ui.card():
    ui.label('探索')
    with ui.row():
        ui.button('bit全探索', on_click=lambda: copy('bit'))
        ui.button('にぶたん_lower', on_click=lambda: copy('lowerBound'))
        ui.button('にぶたん_upper', on_click=lambda: copy('upperBound'))

with ui.card():
    ui.label('グラフアルゴリズム')
    with ui.row():
        ui.button('Input_重みなし', on_click=lambda: copy('graph_1'))
        ui.button('Input_重みあり', on_click=lambda: copy('graph_2'))
    with ui.row():
        ui.button('dfs', on_click=lambda: copy('dfs'))
        ui.button('bfs', on_click=lambda: copy('bfs'))
        ui.button('Dijkstra', on_click=lambda: copy('dijkstra'))
    with ui.row():
        ui.button('隣のマス_4', on_click=lambda: copy('neighbor4'))
        ui.button('隣のマス_8', on_click=lambda: copy('neighbor8'))

with ui.card():
    ui.label('データ構造')
    with ui.row():
        ui.button('Pair', on_click=lambda: copy('pair'))
        ui.button('IntPair', on_click=lambda: copy('intPair'))
        ui.button('LongPair', on_click=lambda: copy('longPair'))
    with ui.row():
        ui.button('セグ木', on_click=lambda: copy('segTree'))

with ui.card():
    ui.label('数学系')
    with ui.row():
        ui.button('lcm', on_click=lambda: copy('lcm'))
        ui.button('arraylcm', on_click=lambda: copy('arraylcm'))
        ui.button('gcd', on_click=lambda: copy('gcd'))
        ui.button('arraygcd', on_click=lambda: copy('arraygcd'))

with ui.card():
    ui.label('その他')
    with ui.row():
        ui.button('一次元累積和', on_click=lambda: copy('cumul1'))
        ui.button('二次元累積和', on_click=lambda: copy('cumul2'))


ui.run()
