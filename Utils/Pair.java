class IntPair implements Comparable<IntPair>{
    int f;
    int s;
    IntPair(int f,int s){
        this.f = f;
        this.s = s;
    }

    @Override
    public int compareTo(IntPair o) {
        return o.f == this.f ? Integer.compare(o.s,this.s) : Integer.compare(o.f,this.f);
    }
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
        return o.f == this.f ? Long.compare(o.s,this.s) : Long.compare(o.f,this.f);
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