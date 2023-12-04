import java.util.ArrayList;
import java.util.List;

public class neighbors {
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
}
