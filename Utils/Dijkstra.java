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