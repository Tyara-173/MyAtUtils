class Pair<K,V>{
    K f;
    V s;
    Pair(K f,V s){
        this.f = f;
        this.s = s;
    }
    @Override
    public String toString(){
        return f.toString() + " " + s.toString();
    }
}