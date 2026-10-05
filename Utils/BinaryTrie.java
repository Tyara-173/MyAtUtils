class BinaryTrie{
    private static final int B = 63;
    Node root = new Node();
    public boolean add(long x,long num){
        if(x < 0){
            return false;
        }
        Node node = root;
        node.cnt += num;
        node.sum += x * num;
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
            node.cnt += num;
            node.sum += x * num;
        }
        return true;
    }
    public boolean add(long x){
        if(x < 0){
            return false;
        }
        return add(x,1);
    }
    public boolean remove(long x,long num){
        if(x < 0 || !contains(x)){
            return false;
        }
        Node node = root;
        num = Math.min(num,count(x));
        node.cnt -= num;
        node.sum -= x * num;
        for (int i = B-1; i >= 0; i--) {
            if(((x >> i) & 1) == 0){
                node.left.cnt -= num;
                node.left.sum -= x * num;
                if(node.left.cnt == 0){
                    node.left = null;
                    return true;
                }
                node = node.left;
            }else{
                node.right.cnt -= num;
                node.right.sum -= x * num;
                if(node.right.cnt == 0){
                    node.right = null;
                    return true;
                }
                node = node.right;
            }
        }
        return true;
    }
    public boolean remove(long x){
        if(x < 0 || !contains(x)){
            return false;
        }
        return remove(x,1);
    }

    // ordered index[i]
    long get(long i){
        if(i < 0 || i >= size()){
            return -1;
        }
        Node node = root;
        long val = 0;
        long k = i;
        for (int j = 0; j < B; j++) {
            val <<= 1;
            long now = node.left == null ? 0 : node.left.cnt;
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

    // count: == x
    long count(long x){
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

    // count: < x
    long countLT(long x){
        if(x < 0){
            return 0;
        }
        Node node = root;
        long cnt = 0;
        for (int i = B-1; i >= 0; i--) {
            if(((x >> i) & 1) == 0){
                node = node.left;
            }else{
                if(node.left != null){
                    cnt += node.left.cnt;
                }
                node = node.right;
            }
            if(node == null){
                break;
            }
        }
        return cnt;
    }

    // count: <= x
    long countLE(long x){
        if(x < 0){
            return 0;
        }
        return countLT(x+1);
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

    // search max: < x
    long lowerBound(long x){
        return get(countLT(x) - 1);
    }

    // search max: <= x
    long upperBound(long x){
        return get(countLE(x) - 1);
    }

    long LT(long x){
        return lowerBound(x);
    }
    long LE(long x){
        return upperBound(x);
    }

    // search min: > x
    long GT(long x){
        return get(countLE(x));
    }

    // search min: >= x
    long GE(long x){
        return get(countLT(x));
    }

    long sum(){
        return root.sum;
    }

    long sum(int i) {
        if (i <= 0) return 0;
        if (i >= size()) return root.sum;
        Node node = root;
        long val = 0;
        long x = 0;
        long k = i;
        for (int j = 0; j < B; j++) {
            x <<= 1;
            long now = node.left == null ? 0 : node.left.cnt;
            if (k < now) {
                node = node.left;
            } else {
                val += node.left == null ? 0 : node.left.sum;
                k -= now;
                node = node.right;
                x++;
            }
        }
        return val + x * k;
    }

    // min(xor(x,ある値 in S))
    long minXor(long x){
        if(isEmpty()){
            return -1;
        }
        Node node = root;
        long val = 0;
        for (int i = B - 1; i >= 0; i--) {
            if(((x >> i) & 1) == 0){
                if(node.left == null){
                    node = node.right;
                    val |= 1L << i;
                }else{
                    node = node.left;
                }
            }else{
                if(node.right == null){
                    node = node.left;
                    val |= 1L << i;
                }else{
                    node = node.right;
                }
            }
        }
        return val;
    }

    // max(xor(x,ある値 in S))
    long maxXor(long x){
        if(isEmpty()){
            return -1;
        }
        Node node = root;
        long val = 0;
        for (int i = B - 1; i >= 0; i--) {
            if(((x >> i) & 1) == 1){
                if(node.left == null){
                    node = node.right;
                    val |= 1L << i;
                }else{
                    node = node.left;
                }
            }else{
                if(node.right == null){
                    node = node.left;
                    val |= 1L << i;
                }else{
                    node = node.right;
                }
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

    long size(){
        return root.cnt;
    }

    long length(){
        return size();
    }
    boolean isEmpty(){
        return size() == 0;
    }
    private static class Node{
        long cnt;
        long sum;
        Node left;
        Node right;
    }
}