from nicegui import ui
import pyperclip

utils = {
    'graph':
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
}


def copy(str):
    pyperclip.copy(utils[str])
    print(str)


with ui.card():
    ui.label('グラフアルゴリズム')
    with ui.row():
        ui.button('Util', on_click=lambda: copy('graph'))
        ui.button('dfs', on_click=lambda: copy('dfs'))
        ui.button('bfs', on_click=lambda: copy('bfs'))
        ui.button('Dijkstra', on_click=lambda: copy('bfs'))

with ui.card():
    ui.label('数学系')
    with ui.row():
        ui.button('lcm', on_click=lambda: copy('lcm'))
        ui.button('arraylcm', on_click=lambda: copy('arraylcm'))
        ui.button('gcd', on_click=lambda: copy('gcd'))
        ui.button('arraygcd', on_click=lambda: copy('arraygcd'))

with ui.card():
    ui.label('探索')
    with ui.row():
        ui.button('bit全探索', on_click=lambda: copy('bit'))
        ui.button('にぶたん_lower', on_click=lambda: copy('lowerBound'))
        ui.button('にぶたん_upper', on_click=lambda: copy('upperBound'))


ui.run()
