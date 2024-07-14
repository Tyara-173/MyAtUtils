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
        return this.f + " " + this.s;
    }
}