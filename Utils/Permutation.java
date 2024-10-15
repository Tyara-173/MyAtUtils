class Permutation implements Iterator<int[]>, Iterable<int[]>{
    private List<List<Integer>> per;
    private int[] arr;

    Permutation(int n){
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
    }

    @Override
    public boolean hasNext() {
        return arr != null;
    }

    @Override
    public int[] next() {
        int[] temp = arr.clone();
        if(!nextPermutation(arr)){
            arr = null;
        }
        return temp;
    }
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

    boolean nextPermutation(int[] arr){
        int len = arr.length;
        int left = len - 2;
        while (left >= 0 && arr[left] >= arr[left+1]) left--;
        if (left < 0) return false;
        int right = len - 1;
        while (arr[left] >= arr[right]) right--;
        { int t = arr[left]; arr[left] = arr[right];  arr[right] = t; }
        left++;
        right = len - 1;
        while (left < right) {
            { int t = arr[left]; arr[left] = arr[right]; arr[right] = t; }
            left++;
            right--;
        }
        return true;
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

    @Override
    public Iterator<int[]> iterator() {
        return this;
    }
}