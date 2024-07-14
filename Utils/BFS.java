long[] bfs(int n,int start,List<List<Integer> e){
    long[] cost = new long[n];
    Arrays.fill(cost,Long.MAX_VALUE);
    Deque<Integer> q = new ArrayDeque<>();
    q.add(start);
    while (!q.isEmpty()){
        int num = q.poll();
        for (Integer i : e.get(num)) {
            if(cost[i] > cost[num] + 1){
                q.add(i);
                cost[i] = cost[num] + 1;
            }
        }
    }
    return cost;
}