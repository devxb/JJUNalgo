import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Main{
	
	int nodeCount, edgeCount;
	private List<ArrayList<Integer> > edges;
	int[] cycleCheck;
	
	public static void main(String[] args){
		(new Main()).solve();
	}
	
	private void solve(){
		this.input();
		System.out.println(this.call());
	}
	
	private void input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			this.input(br);
			this.initCycle();
		}catch(IOException IOE){}
	}
	
	private void input(BufferedReader br) throws IOException{
		String[] read = br.readLine().split(" ");
		this.nodeCount = Integer.parseInt(read[0]);
		this.edgeCount = Integer.parseInt(read[1]);
		this.edges = this.initEdges(new ArrayList<ArrayList<Integer>>());
		Set<Edge> edgeCheck = new HashSet<Edge>();
		for(int i = 0; i < this.edgeCount; i++){
			read = br.readLine().split(" ");
			int from = Integer.parseInt(read[0]);
			int to = Integer.parseInt(read[1]);
			Edge edge = new Edge(from, to);
			if(edgeCheck.contains(edge)) continue;
			edges.get(from).add(to);
			edgeCheck.add(edge);
		}
	}
	
	private List<ArrayList<Integer>> initEdges(List<ArrayList<Integer>> edges){
		for(int i = 0; i <= this.nodeCount+1; i++) edges.add(new ArrayList<Integer>());
		return edges;
	}
	
	private void initCycle(){
		this.cycleCheck = new int[this.nodeCount+1];
	}
	
	private int call(){
		int callable = 0;
		for(int i = 1; i <= this.nodeCount; i++){
			if(this.cycleCheck[i] == 0) this.cycleCheck[i] = this.isCallable(i, new boolean[this.nodeCount+1]);
			if(this.cycleCheck[i] == 1) callable++;
		}
		return callable;
	}
	
	private int isCallable(int idx, boolean[] visited){
		int ans = 1;
		visited[idx] = true;
		for(int i = 0; i < this.edges.get(idx).size(); i++){
			int nextIdx = this.edges.get(idx).get(i);
			if(this.cycleCheck[nextIdx] > 0) ans = max(ans, cycleCheck[nextIdx]);
			else if(visited[nextIdx]) return this.cycleCheck[idx] = 2;
			else ans = max(ans, this.isCallable(nextIdx, visited));
		}
		return this.cycleCheck[idx] = max(this.cycleCheck[idx], ans);
	}
	
	private static class Edge{
		
		int from, to;
		
		public Edge(int from, int to){
			this.from = from;
			this.to = to;
		}
		
		@Override
		public boolean equals(Object obj){
			if(!(obj instanceof Edge)) return false;
			Edge edge = (Edge)(obj);
			if(edge.from == this.from && edge.to == this.to) return true;
			return false;
		}
		
		@Override
		public int hashCode(){
			int hash = Objects.hash(this.from, this.to);
			return hash;
		}
		
	}
	
}
