void dfs(int num){
    a[num] = true;
    for (Integer i : e.get(num)) {
        if(!a[i]){
            dfs(i);
        }
    }
}