import java.util.*;

public class Utils {
    long[] cumulativeSum(long[] a){
        long[] b = new long[a.length+1];
        for (int i = 1; i <= a.length; i++) {
            b[i] = b[i-1] + a[i-1];
        }
        return b;
    }

    Map<Long,Integer> typeMap(long[] arr){
        Map<Long,Integer> map = new HashMap<>();
        for (long value : arr) {
            add(map,value);
        }
        return map;
    }

    void add(Map<Long,Integer> map,long value){
        if(map.containsKey(value)){
            map.put(value,map.get(value) + 1);
        }else{
            map.put(value,1);
        }
    }

    void bitAll(int n){
        for (int i = 0; i < 1 << n; i++) {
            for (int j = 0; j < n; j++) {
                if(((i >> j) & 1) == 1){

                }else{

                }
            }
        }
    }

    int lowerBound(long[] a,long x){
        int left = -1;
        int right = a.length;
        while (right - left > 1){
            int mid = (right + left) / 2;
            if(a[mid] < x){
                left = mid;
            }else{
                right = mid;
            }
        }
        return right;
    }

    int upperBound(long[] a,long x){
        int left = -1;
        int right = a.length;
        while (right - left > 1){
            int mid = (right + left) / 2;
            if(a[mid] <= x){
                left = mid;
            }else{
                right = mid;
            }
        }
        return right;
    }

    List<IntPair> neighbor8(int y, int x, int h, int w){
        int[] yy = {1,1,0,-1,-1,-1, 0, 1};
        int[] xx = {0,1,1, 1, 0,-1,-1,-1};
        List<IntPair> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            int ny = y + yy[i];
            int nx = x + xx[i];
            if(ny >= 0 && ny < h && nx >= 0 && nx < w){
                list.add(new IntPair(ny,nx));
            }
        }
        return list;
    }

    List<IntPair> neighbor4(int y, int x, int h, int w){
        int[] yy = {1, 0,-1, 0};
        int[] xx = {0, 1, 0,-1};
        List<IntPair> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            int ny = y + yy[i];
            int nx = x + xx[i];
            if(ny >= 0 && ny < h && nx >= 0 && nx < w){
                list.add(new IntPair(ny,nx));
            }
        }
        return list;
    }

    long lcm(long a,long b){
        return a / gcd(a,b) * b;
    }

    long gcd(long a,long b){
        return b == 0 ? a : gcd(b,a%b);
    }

}

