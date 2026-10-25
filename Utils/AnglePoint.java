class Point implements Comparable<Point>{
    long x;
    long y;
    int i;
    Point(long f,long s){
        this.x = f;
        this.y = s;
        this.i = -1;
    }

    Point(long f,long s,int i){
        this.x = f;
        this.y = s;
        this.i = i;
    }

    long cross(Point a, Point b){
        return a.x * b.y - a.y *b.x;
    }

    @Override
    public int compareTo(Point o) {
        int h = (this.y > 0 || (this.y == 0 && this.x > 0)) ? -1 : 1;
        int oh = (o.y > 0 || (o.y == 0 && o.x > 0)) ? -1 : 1;
        if(h != oh){
            return h;
        }
        if(cross(this,o) == 0){
            return 0;
        }else if(cross(this,o) > 0){
            return -1;
        }else{
            return 1;
        }
    }

    public String toString(){
        return String.format("(x:%d,y:%d)",x,y);
    }
}