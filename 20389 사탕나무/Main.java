import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

import static java.lang.Math.max;

public class Main{
    
	private int num;
	
    public static void main(String[] args){
		Solve solve = new Solve().run();
    }
    
}

class Solve{
	
	private int N, K;
	private int[][] dp;
	private int[] par;
	private boolean[] visit;
	private List<ArrayList<Integer> > graph = new ArrayList<ArrayList<Integer>>();
	private Queue<Integer> orders = new LinkedList<Integer>();
	
 	public Solve run(){
		input();
		setDp(1);
		setPar();
		System.out.println(getDp());
		return this;
	}
	
	private void input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			String[] read = br.readLine().split(" ");
			this.N = Integer.parseInt(read[0]);
			this.K = Integer.parseInt(read[1]);
			this.dp = new int[N+1][K+1];
			this.par = new int[N+1];
			this.visit = new boolean[N+1];
			for(int i = 0; i <= N; i++) this.graph.add(new ArrayList<Integer>());
			for(int i = 1; i < N; i++){
				read = br.readLine().split(" ");
				int from = Integer.parseInt(read[0]);
				int to = Integer.parseInt(read[1]);
				graph.get(from).add(to);
				graph.get(to).add(from);
			}
		}catch(IOException IOE){}
	}
	
	private int setDp(int idx){
		visit[idx] = true;
		orders.add(idx);
		for(int i = 0; i <= K; i++) dp[idx][i] = 1;
		List<Integer> list = graph.get(idx);
		for(int i = 0; i < list.size(); i++){
			int nextIdx = list.get(i);
			if(visit[nextIdx]) continue;
			par[nextIdx] = idx;
			setDp(nextIdx);
			for(int j = 1; j <= K; j++) dp[idx][j] += dp[nextIdx][j-1];
		}
		return dp[idx][K];
	}
	
	private void setPar(){
		orders.poll();
		while(!orders.isEmpty()){
			int idx = orders.poll();
			int[] tmp = new int[K+1];
			for(int i = 0; i <= K; i++) tmp[i] = dp[idx][i];
			for(int i = 1; i <= K; i++){
				if(i == 1) dp[idx][i] += 1;
				else dp[idx][i] += dp[par[idx]][i-1] - tmp[i-2];
			}
		}
	}
	
	private int getDp(){
		int ret = 0;
		for(int i = 1; i <= N; i++) ret = max(ret, dp[i][K]);
		return ret;
	}
	
}
