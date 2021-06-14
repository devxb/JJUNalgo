import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args){
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    private Scanner sc = new Scanner(System.in);
    private int[] dp = new int[5]; // 1 : A , 2 : B
    private int[] tempDp = new int[5]; // 1 : A , 2 : B
    private int K;
    
    public void run(){
        K = sc.nextInt();
        dp[1] = 1; // initial
        getDp(1);
        System.out.println(dp[1] + " " + dp[2]);
    }
    
    private void getDp(int cnt){
        if(cnt > K) return;
        tempDp[1] = dp[2];
        tempDp[2] = dp[1]+dp[2];
        dp[1] = tempDp[1];
        dp[2] = tempDp[2];
        getDp(cnt+1);
    }
    
    // A -> B -> AB -> BAB -> ABBAB
}
