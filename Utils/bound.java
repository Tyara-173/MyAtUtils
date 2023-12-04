public class bound {
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
}
