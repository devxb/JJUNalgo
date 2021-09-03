import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main{
    
    public static void main(String[] args){
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    private int N, M, H;
    private int[][] dp;
    
    public void run(){
        input();
        System.out.println(dp[N][H]);
    }
    
    private void input(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            String[] read = br.readLine().split(" ");
            N = Integer.parseInt(read[0]);
            M = Integer.parseInt(read[1]);
            H = Integer.parseInt(read[2]);
            dp = new int[N+1][H+1];
            int[][] arr = new int[N+1][1001];
            for(int i = 1; i <= N; i++){
                read = br.readLine().split(" ");
                for(int j = 0; j < read.length; j++) arr[i][Integer.parseInt(read[j])] = 1;
                setDp(i, arr[i]);
            }
        }catch(IOException IOE){}
    }
    
    private void setDp(int i, int[] arr){
        for(int j = 1; j <= H; j++){
            dp[i][j] = ((arr[j]%10007) + (dp[i-1][j]%10007))%10007;
            for(int l = 1; l < j; l++) 
                if(arr[l] == 1) dp[i][j] = ((dp[i][j]%10007)+(dp[i-1][j-l]%10007))%10007;
        }
    }
    
}
