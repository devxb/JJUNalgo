// 7
import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    private int N, R, Q;
    private int[] dp;
    private ArrayList<ArrayList<Integer> > edge = new ArrayList<ArrayList<Integer> >();
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private String[] read;
    
    public void run() throws IOException{
        input();
        initialDp(R);
        for(int i = 0; i < Q; i++){
            read = br.readLine().split(" ");
            System.out.println(dp[Integer.parseInt(read[0])]);
        }
    }
    
    private void input() throws IOException{
        read = br.readLine().split(" ");
        N = Integer.parseInt(read[0]);
        R = Integer.parseInt(read[1]);
        Q = Integer.parseInt(read[2]);
        dp = new int[N+5];
        for(int i = 0; i <= N+5; i++) edge.add(new ArrayList<Integer>()); 
        for(int i = 1; i < N; i++){
            int from, to;
            read = br.readLine().split(" ");
            from = Integer.parseInt(read[0]);
            to = Integer.parseInt(read[1]);
            edge.get(from).add(to);
            edge.get(to).add(from);
        }
    }
    
    private int initialDp(int node){
        if(dp[node] > 0) return dp[node];
        dp[node] = 1;
        for(int i = 0; i < edge.get(node).size(); i++){
            int nextNode = edge.get(node).get(i);
            if(dp[nextNode] > 0) continue;
            dp[node] += initialDp(nextNode);
        }
        return dp[node];
    }
    
}
