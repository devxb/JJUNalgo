import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args){
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private long S, N, K, R1, R2, C1, C2;
    private int[][] arr = new int[55][55];
    private long grid = 1;
    
    public void run(){
        input();
        calcGrid();
        setArr(0, 0, 0, this.grid, 0);
        printArr();
    }
    
    private void input(){
        try{
            String[] read = br.readLine().split(" ");
            S = Long.parseLong(read[0]);
            N = Long.parseLong(read[1]);
            K = Long.parseLong(read[2]);
            R1 = Long.parseLong(read[3]);
            R2 = Long.parseLong(read[4]);
            C1 = Long.parseLong(read[5]);
            C2 = Long.parseLong(read[6]);
        } catch(Exception e){}
    }
    
    private void calcGrid(){
        for(int i = 0; i < S; i++) grid *= N;
    }
    
    private void setArr(int sec, long y, long x, long grid, int fill){
        if(y > R2 || x > C2) return;
        if(y + grid <= R1 || x + grid <= C1) return;
        if(sec == S) {
            arr[(int)(y-R1)][(int)(x-C1)] = fill;
            return;
        }
        long gridDivN = grid / N;
        long fillLeft = (N-K)/2;
        long fillRight = fillLeft+K-1;
        for(int i = 0; i < N; i++){
            long nextY = y + gridDivN * i;
            for(int j = 0; j < N; j++){
                long nextX = x + gridDivN * j;
                int nextFill = fill;
                if(nextFill == 0 && i >= fillLeft && i <= fillRight && j >= fillLeft && j <= fillRight) nextFill = 1;
                setArr(sec+1, nextY, nextX, gridDivN, nextFill);
            }
        }
    }
    
    private void printArr(){
        for(int i = 0; i <= R2-R1; i++){
            for(int j = 0; j <= C2-C1; j++){
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }
    
}
