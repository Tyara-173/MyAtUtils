import java.util.*;

public class dfs_bfs {
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

    void dfs(int num){
        a[num] = true;
        for (Integer i : e.get(num)) {
            if(!a[i]){
                dfs(i);
            }
        }
    }

    void bfs(){
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
    }

}
