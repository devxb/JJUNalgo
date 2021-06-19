import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args) throws Exception{
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    private Scanner sc = new Scanner(System.in);
    private int N;
    private int cnt = 987654321;
    private char[][] arr;
    
    public void run(){
        input();
        flipCol(0);
        System.out.println(cnt);
    }
    
    private void input(){
        N = sc.nextInt();
        arr = new char[N+5][N+5];
        for(int i = 0; i < N; i++){
            String read = sc.next();
            for(int j = 0; j < N; j++){
                arr[i][j] = read.charAt(j);
            }
        }
    }
    
    private int flipRow(char[][] arr){
        for(int i = 0; i < N; i++){
            int hCnt = 0;
            for(int j = 0; j < N; j++){
                if(arr[i][j] == 'T') hCnt++;
            }
            if(hCnt*2 > N){
                for(int j = 0; j < N; j++){
                    if(arr[i][j] == 'T') arr[i][j] = 'H';
                    else arr[i][j] = 'T';
                }
            }
        }
        int ret = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(arr[i][j] == 'T') ret++;
            }
        }
        return ret;
    }
    
    private void flipCol(int idx){
        if(idx == N){
            this.cnt = Math.min(this.cnt,flipRow(this.arr));
            return;
        }
        flip(idx);
        flipCol(idx+1);
        flip(idx);
        flipCol(idx+1);
    }
    
    private void flip(int col){
        for(int i = 0; i < N; i++){
            if(arr[i][col] == 'T') arr[i][col] = 'H';
            else arr[i][col] = 'T';
        }
    }
    
}
