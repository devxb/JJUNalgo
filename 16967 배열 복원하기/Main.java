import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args) throws Exception{
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private String[] read;
    private int[][] arr;
    private int[][] ans;
    private int H,W,Down,Right;
    
    public void run() throws Exception{
        input();
        setAns();
        for(int i = 0; i < H; i++){
            for(int j = 0; j < W; j++) System.out.print(ans[i][j] + " ");
            System.out.println();
        }
    }
    
    private void input() throws Exception{
        read = br.readLine().split(" ");
        H = Integer.parseInt(read[0]);
        W = Integer.parseInt(read[1]);
        Down = Integer.parseInt(read[2]);
        Right = Integer.parseInt(read[3]);
        arr = new int[H+Down+5][W+Right+5];
        ans = new int[H+5][W+5];
        for(int i = 0; i < H+Down; i++){
            read = br.readLine().split(" ");
            for(int j = 0; j < W+Right; j++) arr[i][j] = Integer.parseInt(read[j]);
        }
    }
    
    private void setAns(){
        for(int i = 0; i < H; i++){
            for(int j = 0; j < W; j++){
                if(outOfIndex(i-Down,j-Right)) ans[i][j] = arr[i][j];
                else ans[i][j] = arr[i][j] - ans[i-Down][j-Right];
            }
        }
    }
    
    private boolean outOfIndex(int y, int x){ // true : 범위 벗어남 false : 안벗어남
        return (y < 0 || x < 0 || y >= H || x >= W);
    }
    
}
