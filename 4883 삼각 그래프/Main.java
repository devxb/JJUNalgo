package algorithm;

import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args){
        try{
            int N = Integer.parseInt(br.readLine()),tc = 1;
            while(N != 0){
                Solve sol = new Solve(N, br);
                System.out.println(tc+". "+sol.solve(2));
                tc++;
                N = Integer.parseInt(br.readLine());
            }
        } catch (IOException ioe){}
    }
}

class Solve{
    private int N, INF = 987654321;
    private int dp[][];
    private int arr[][];
    private int dy[] = {0, -1, -1, -1};
    private int dx[] = {-1, -1, 0, 1};
    private BufferedReader br;
    
    public Solve(int N, BufferedReader br){
        this.N = N;
        this.br = br;
        input();
    }
    
    public void input(){
        try{
            this.dp = new int[N+5][5];
            this.arr = new int[N+5][5];
            for(int i = 1; i <= N; i++){
                String str[] = br.readLine().split(" ");
                for(int j = 0; j < 3; j++) {
                    arr[i][j+1] = Integer.parseInt(str[j]);
                    if(i > 1) dp[i][j+1] = INF;
                }
            }
            dp[1][1] = arr[1][2];
            dp[1][2] = arr[1][2];
            dp[1][3] = dp[1][2]+arr[1][3];
        } catch (IOException ioe){}
    }
    
    public int solve(int y){
        for(int x = 1; x <= 3; x++){
            for(int dir = 0; dir < 4; dir++){
                int _y = y + dy[dir];
                int _x = x + dx[dir];
                if(_x < 1 || _x > 3) continue;
                dp[y][x] = Math.min(dp[_y][_x]+arr[y][x],dp[y][x]);
            }
        }
        if(y == N) return dp[y][2];
        return solve(y+1);
    }
    
}
