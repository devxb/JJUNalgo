import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args) throws Exception{
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    class Dungeon{
        private int needExp, getExp;
        
        public Dungeon(int needExp, int getExp){
            this.needExp = needExp;
            this.getExp = getExp;
        }
        
    }
    
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private String[] read;
    private int N, T;
    private Dungeon[] dungeon;
    private int[][] dp;
    private int[][] move;
    
    public void run() throws Exception{
        input();
        System.out.println(getMaximumExp(1));
    }
    
    
    private void input() throws Exception{
        read = br.readLine().split(" ");
        N = Integer.parseInt(read[0]);
        T = Integer.parseInt(read[1]);
        dp = new int[N+5][T+5];
        move = new int[N+5][N+5];
        dungeon = new Dungeon[N+5];
        for(int i = 0; i <= N; i++)
            for(int j = 0; j <= T; j++) dp[i][j] = -1;
            
        for(int i = 0; i < N; i++){
            read = br.readLine().split(" ");
            dungeon[i] = new Dungeon(Integer.parseInt(read[0]), Integer.parseInt(read[1]));
            if(dungeon[i].needExp == 0) dp[i][0] = 0;
        }
        
        for(int i = 0; i < N; i++){
            read = br.readLine().split(" ");
            for(int j = 0; j < N; j++) move[i][j] = Integer.parseInt(read[j]);
        }
    }
    
    private int getMaximumExp(int time){
        if(time > T){
            int ret = 0;
            for(int i = 0; i < N; i++) ret = Math.max(dp[i][T], ret);
            return ret;
        }
        for(int i = 0; i < N; i++){
            if(dp[i][time-1] != -1) dp[i][time] = dp[i][time-1] + dungeon[i].getExp;
            for(int j = 0; j < N; j++){ // 다른경로에서 i로 오는경우
                if(i == j) continue;
                int bTime = time - move[j][i];
                if(bTime <= 0 || dp[j][bTime] < dungeon[i].needExp) continue;
                dp[i][time] = Math.max(dp[i][time], dp[j][bTime]);
            }
        }
        return getMaximumExp(time+1);
    }
    
}
