import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    class Array{
        int h, w, ans;
        
        public Array(){}
        
        public Array(int ans, int h, int w){
            this.ans = ans;
            this.h = h;
            this.w = w;
        }
        
    }
    
    private Scanner sc = new Scanner(System.in);
    private Array[] arr;
    private int N, INF = (int)Math.pow(2,31);
    private Array[][] dp;
    
    public void run(){
        input();
        System.out.println(getDp(0,N-1).ans);
    }
    
    private void input(){
        N = sc.nextInt();
        arr = new Array[N+5];
        dp = new Array[N+5][N+5];
        for(int i = 0; i < N; i++){
            arr[i] = new Array();
            arr[i].h = sc.nextInt();
            arr[i].w = sc.nextInt();
            dp[i][i] = arr[i];
        }
    }
    
    private Array getDp(int left, int right){
        if(dp[left][right] != null) return dp[left][right];
        if(left - right == 1 || left - right == -1) 
            return dp[left][right] = calcArray(arr[left], arr[right]);
        Array ansArray = new Array(INF, INF, INF);
        for(int i = 1; i < right-left; i++) 
            ansArray = getMin(ansArray, getMin(
                calcArray(dp[left][left+(i-1)], getDp(left+i, right)),
                calcArray(getDp(left, right-i),dp[right-(i-1)][right])
                ));
        return dp[left][right] = ansArray;
    }
    
    private Array calcArray(Array arr1, Array arr2){
        return new Array(arr1.h*arr1.w*arr2.w+arr1.ans+arr2.ans, arr1.h, arr2.w);
    }
    
    private Array getMin(Array arr1, Array arr2){
        if(arr1.ans < arr2.ans) return arr1;
        return arr2;
    }
    
}
