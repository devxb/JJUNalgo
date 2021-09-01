// 18430
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main{
    
    public static void main(String[] args){
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    private int N, M;
    private int[][] arr;
    private boolean[][] check;
    private int[] dy = {1, 1, -1, -1};
    private int[] dx = {1, -1, -1, 1};
    
    public void run(){
        input();
        System.out.println(getBoomerang(0));
    }
    
    private void input(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            String[] read = br.readLine().split(" ");
            N = Integer.parseInt(read[0]);
            M = Integer.parseInt(read[1]);
            check = new boolean[N][M];
            arr = new int[N][M];
            for(int i = 0; i < N; i++){
                read = br.readLine().split(" ");
                for(int j = 0; j < M; j++) arr[i][j] = Integer.parseInt(read[j]);
            }
        }catch(IOException IOE){}
    }
    
    private int getBoomerang(int x){
        int ret = 0;
        for(int i = 0; i < N; i++){
            for(int j = x; j < M; j++){
                if(duplicateChecking(i,j)) continue;
                for(int l = 0; l < 4; l++){
                    int wing1 = i+dy[l];
                    int wing2 = j+dx[l];
                    if(duplicateChecking(wing1, j) || duplicateChecking(i, wing2)) continue;
                    flipCheck(i, j, wing1, wing2);
                    ret = Math.max(ret, getBoomerang(j)+calcBoomerangSize(i, j, wing1, wing2));
                    flipCheck(i, j, wing1, wing2);
                }
            }
        }
        return ret;
    }
    
    private void flipCheck(int y, int x, int wing1, int wing2){
        check[y][x] = (check[y][x] == true) ? false : true;
        check[wing1][x] = (check[wing1][x] == true) ? false : true;
        check[y][wing2] = (check[y][wing2] == true) ? false : true;
    }
    
    private int calcBoomerangSize(int y, int x, int wing1, int wing2){
        int ret = arr[y][x]*2;
        ret += arr[wing1][x];
        ret += arr[y][wing2];
        return ret;
    }
    
    /* true : out of index */
    private boolean outOfIndex(int y, int x){
        return  y < 0 || y >= N || x < 0 || x >= M;
    }
    
    /* true : duplicateChecking */
    private boolean duplicateChecking(int y, int x){
        if(outOfIndex(y,x)) return true;
        return check[y][x];
    }
    
}
