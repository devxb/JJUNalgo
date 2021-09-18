import java.util.ArrayList;
import java.util.PriorityQueue;
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
	
	private static class Node implements Comparable<Node>{
		
		private int idx, cnt;
		
		public Node(int idx, int cnt){
			this.idx = idx;
			this.cnt = cnt;
		}
		
		@Override
		public int compareTo(Node node){
			if(this.cnt > node.cnt) return 1;
			if(this.cnt < node.cnt) return 1;
			return 0;
		}
		
	}
	
	private int N;
	private int[] check;
	private int[] rumor;
	private int[] firRumor;
	private ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
	private PriorityQueue<Node> pq = new PriorityQueue<Node>();
	
	public void run(){
		input();
		setRumor();
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++){
			if(check[i] == 987654321) sb.append("-1");
			else sb.append(check[i]);
			sb.append(" ");
		}
		System.out.print(sb.toString());
	}
	
	private void input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			this.N = Integer.parseInt(br.readLine());
			check = new int[N+1];
			rumor = new int[N+1];
			firRumor = new int[N+1];
			for(int i = 0; i <= N; i++){
				list.add(new ArrayList<Integer>());
				check[i] = 987654321;
			}
			String[] read = null;
			for(int i = 1; i <= N; i++){
				rumor[i]--;
				firRumor[i]--;
				read = br.readLine().split(" ");
				for(String str : read){
					int idx = Integer.parseInt(str);
					rumor[idx]++;
					firRumor[idx]++;
					list.get(i).add(idx);
				}
			}
			int M = Integer.parseInt(br.readLine());
			read = br.readLine().split(" ");
			for(String str : read) pq.add(new Node(Integer.parseInt(str), 0));
		}catch(IOException IOE){}
	}
	
	private void setRumor(){
		while(!pq.isEmpty()){
			Node node = pq.poll();
			if(check[node.idx] <= node.cnt) continue;
			check[node.idx] = node.cnt;
			for(Integer nextIdx : list.get(node.idx)){
				if(check[nextIdx] <= node.cnt+1) continue;
				rumor[nextIdx]--;
				if(2*rumor[nextIdx] < firRumor[nextIdx]) pq.add(new Node(nextIdx, node.cnt+1));
			}
		}
	}
	
}
