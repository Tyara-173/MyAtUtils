class IntPair implements Comparable<IntPair>{
    int f;
    int s;
    IntPair(int f,int s){
        this.f = f;
        this.s = s;
    }

    @Override
    public int compareTo(IntPair o) {
        return o.f == this.f ? Integer.compare(this.s,o.s) : Integer.compare(this.f,o.f);
    }

    public String toString(){
        return "[" + this.f + "," + this.s + "]";
}

class LongPair implements Comparable<LongPair>{
    long f;
    long s;
    LongPair(long f,long s){
        this.f = f;
        this.s = s;
    }

    @Override
    public int compareTo(LongPair o) {
        return o.f == this.f ? Long.compare(this.s,o.s) : Long.compare(this.f,o.f);
    }

    public String toString(){
        return "[" + this.f + "," + this.s + "]";
    }
}

class Pair<T>{
    T f;
    T s;
    Pair(T f,T s){
        this.f = f;
        this.s = s;
    }
}