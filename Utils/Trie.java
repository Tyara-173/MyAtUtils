import java.util.*;

class TrieTree{
    Node root = new Node();
    List<String> stringList = new ArrayList<>();

    boolean add(String s){
        return add(s.toCharArray());
    }

    boolean add(char[] s){
        stringList.add(String.valueOf(s));
        Node node = root;
        node.count++;
        for (char c : s) {
            if(node.child.containsKey(c)){
                node = node.child.get(c);
            }else{
                Node childNode = new Node();
                childNode.parent = node;
                childNode.e = c;
                node.child.put(c,childNode);
                node = childNode;
            }
            node.count++;
        }
        node.end++;
        return true;
    }

    boolean remove(String s){
        return remove(s.toCharArray());
    }

    boolean remove(char[] s){
        Node node = root;
        for (char c : s) {
            if(node.child.containsKey(c)){
                node = node.child.get(c);
            }else{
                return false;
            }
        }
        if(node.end <= 0){
            return false;
        }
        node.end--;
        while (node != null){
            node.count--;
            if(node.count == 0){
                node.parent.child.remove(node.e);
            }
            node = node.parent;
        }
        return true;
    }

    int count(String s){
        return count(s.toCharArray());
    }

    int count(char[] s){
        Node node = root;
        for (char c : s) {
            if(node.child.containsKey(c)){
                node = node.child.get(c);
            }else{
                return 0;
            }
        }
        return node.count;
    }

    int lcp(String s){
        return lcp(s.toCharArray());
    }

    int lcp(char[] s){
        Node node = root;
        int sum = 0;
        for (char c : s) {
            if(node.child.containsKey(c)){
                node = node.child.get(c);
                sum++;
            }else{
                break;
            }
        }
        return sum;
    }

    int lcpsum(String s){
        return lcpsum(s.toCharArray());
    }

    int lcpsum(char[] s){
        Node node = root;
        int sum = 0;
        for (char c : s) {
            if(node.child.containsKey(c)){
                node = node.child.get(c);
                sum += node.count;
            }else{
                break;
            }
        }
        return sum;
    }

    boolean contains(String s){
        return count(s) > 0;
    }

    boolean contains(char[] s){
        return count(s) > 0;
    }

    List<String> get(String s){
        return get(s.toCharArray());
    }

    List<String> get(char[] s){
        if(!contains(s)){
            return new ArrayList<>();
        }
        Node node = root;
        for (char c : s) {
            if(node.child.containsKey(c)){
                node = node.child.get(c);
            }else{
                break;
            }
        }
        List<String> list = new ArrayList<>();
        List<Node> nodes = new ArrayList<>();
        Deque<Node> q = new ArrayDeque<>();
        q.add(node);
        while(!q.isEmpty()){
            Node now = q.remove();
            for(Map.Entry<Character, Node> i : now.child.entrySet()){
                q.add(i.getValue());
            }
            for (int i = 0; i < now.end; i++) {
                nodes.add(now);
            }
        }
        for(Node i : nodes){
            StringBuilder str = new StringBuilder();
            Node now = i;
            while(now.parent != null){
                str.append(now.e);
                now = now.parent;
            }
            list.add(str.reverse().toString());
        }
        return list;
    }

    int size(){
        return root.count;
    }

    void clear(){
        for (String s : stringList) {
            remove(s);
        }
        root = new Node();
        stringList.clear();
    }

    // @Override
    // public String toString(){
    //     return makeString(root);
    // }

    // String makeString(Node node){
    //     StringJoiner str = new StringJoiner("\n");
    //     str.add(node.e + "");
    //     for(Map.Entry<Character,Node> i : node.child.entrySet()){
    //         str.add(node.e + makeString(i.getValue()));
    //     }
    //     return str.toString();
    // }

    class Node{
        char e;
        int count = 0;
        int end = 0;
        Map<Character, Node> child = new HashMap<>();
        Node parent = null;
    }
}
