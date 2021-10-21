import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Math.min;

public class Main{
    
    public static void main(String[] args){
        Solve solve = new Solve();
		solve.run();
    }
    
}

class Solve{
	
	private int N;
	private boolean[] visit;
	private int[][] dp;
	private ArrayList<ArrayList<Integer> > graph = new ArrayList<ArrayList<Integer> >();
	
	public void run(){
		input();
		System.out.println(getEarlyAdopterCount(1));
	}
	
	private void input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			this.N = Integer.parseInt(br.readLine());
			this.visit = new boolean[N+1];
			this.dp = new int[N+1][2];
			String[] read = null;
			for(int i = 0; i <= N+1; i++) graph.add(new ArrayList<Integer>());
			for(int i = 1; i < N; i++){
				read = br.readLine().split(" ");
				int from = Integer.parseInt(read[0]);
				int to = Integer.parseInt(read[1]);
				graph.get(from).add(to);
				graph.get(to).add(from);
			}
		}catch(IOException IOE){}
	}
	
	private int getEarlyAdopterCount(int idx){
		visit[idx] = true;
		for(int i = 0; i < graph.get(idx).size(); i++){
			int son = graph.get(idx).get(i);
			if(visit[son]) continue;
			dp[idx][1] += getEarlyAdopterCount(son);
			dp[idx][0] += dp[son][1];
		}
		dp[idx][1]++;
		return min(dp[idx][1], dp[idx][0]);
	}
	
}
