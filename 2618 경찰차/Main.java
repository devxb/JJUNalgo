import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args) throws Exception{
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    class Position{
        
        public int y, x;
        
        public Position(int y, int x){
            this.y = y;
            this.x = x;
        }
        
    }
    
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private ArrayList<Position> events = new ArrayList<Position>();
    private int[][] dp; // 마지막으로 선택한 사건번호[][] = 최댓값 
    private int N, W, INF = 987654321;
    
    public void run() throws IOException{
        input();
        System.out.println(setDp(0, 0));
        track(0, 0);
    }
    
    private void input() throws IOException{
        N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());
        dp = new int[W+5][W+5];
        for(int i = 0; i <= W; i++) for(int j = 0; j <= W; j++) dp[i][j] = INF;
        events.add(new Position(INF, INF));
        for(int i = 0; i < W; i++){
            String[] read = br.readLine().split(" ");
            Position pos = new Position(Integer.parseInt(read[0]), Integer.parseInt(read[1]));
            events.add(pos);
        }
    }
    
    private int setDp(int police1, int police2){
        int event = Math.max(police1, police2)+1;
        if(event > W) return 0;
        if(dp[police1][police2] != INF) return dp[police1][police2];
        Position nextPosition = events.get(event);
        Position police1Pos;
        Position police2Pos;
        if(police1 > 0) police1Pos = events.get(police1);
        else police1Pos = new Position(1, 1);
        if(police2 > 0) police2Pos = events.get(police2);
        else police2Pos = new Position(N, N);
        int case1 = setDp(event, police2) + abs(police1Pos.y, nextPosition.y) + abs(police1Pos.x, nextPosition.x);
        int case2 = setDp(police1, event) + abs(police2Pos.y, nextPosition.y) + abs(police2Pos.x, nextPosition.x);
        return dp[police1][police2] = Math.min(dp[police1][police2], Math.min(case1, case2));
    }
    
    private void track(int police1, int police2){
        int nextEvent = Math.max(police1,police2)+1;
        if(nextEvent > W) return;
        Position police1Pos;
        Position police2Pos;
        Position nextPosition = events.get(nextEvent);
        if(police1 > 0) police1Pos = events.get(police1);
        else police1Pos = new Position(1, 1);
        if(police2 > 0) police2Pos = events.get(police2);
        else police2Pos = new Position(N, N);
        int case1 = setDp(nextEvent, police2) + abs(police1Pos.y, nextPosition.y) + abs(police1Pos.x, nextPosition.x);
        int case2 = setDp(police1, nextEvent) + abs(police2Pos.y, nextPosition.y) + abs(police2Pos.x, nextPosition.x);
        if(case1 > case2){
            System.out.println(2);
            track(police1, nextEvent);
        }
        else{
            System.out.println(1);
            track(nextEvent, police2);
        }
    }
    
    private int abs(int a, int b){
        return Math.max(a-b, b-a);
    }
    
}
