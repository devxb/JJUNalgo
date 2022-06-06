// 14621 나만 안되는 연애
import java.io.*;
import java.util.*;

public class Main{
	
	private static final int INF = 987654321;
	private int schools;
	private int roads;
	private List<ArrayList<Node>> nodes;
	private boolean[] schoolGender;
	
	public static void main(String[] args){
		(new Main()).solve();
	}
	
	private void solve(){
		this.input();
		int favoriteRoadWeight = this.getFavoriteRoadWeight();
		System.out.println(favoriteRoadWeight);
	}
	
	private void input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			this.inputSchoolAndRoads(br);
			this.inputSchoolInfos(br);
			this.inputEdges(br);
		}catch(IOException IOE){}
	}
	
	private void inputSchoolAndRoads(BufferedReader br) throws IOException{
		String[] read = br.readLine().split(" ");
		this.schools = Integer.parseInt(read[0]);
		this.roads = Integer.parseInt(read[1]);
	}
	
	private void inputSchoolInfos(BufferedReader br) throws IOException{
		String[] read = br.readLine().split(" ");
		this.schoolGender = new boolean[read.length+1];
		for(int g = 0; g < read.length; g++) schoolGender[g+1] = (read[g].equals("M")) ? true : false;
	}
	
	private void inputEdges(BufferedReader br) throws IOException{
		this.initNodes();
		for(int e = 0; e < this.roads; e++){
			String[] read = br.readLine().split(" ");
			int from = Integer.parseInt(read[0]);
			int to = Integer.parseInt(read[1]);
			int weight = Integer.parseInt(read[2]);
			this.nodes.get(from).add(new Node(to, weight));
			this.nodes.get(to).add(new Node(from, weight));
		}
	}
	
	private void initNodes(){
		this.nodes = new ArrayList<ArrayList<Node>>();
		for(int i = 0; i <= this.schools; i++) nodes.add(new ArrayList<Node>());
	}
	
	private int getFavoriteRoadWeight(){
		int[] visited = this.setFavoriteRoadWeight();
		if(!this.isAllRoadConnected(visited)) return -1;
		int favoriteRoadWeight = 0;
		for(int v = 1; v < visited.length; v++) favoriteRoadWeight += visited[v];
		return favoriteRoadWeight;
	}
	
	private int[] setFavoriteRoadWeight(){
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add(new Node(1, 0));
		int[] visited = this.initVisited(new int[this.schools+1]);
		while(!pq.isEmpty()){
			Node nowNode = pq.poll();
			if(visited[nowNode.idx] != this.INF) continue;
			visited[nowNode.idx] = nowNode.weight;
			List<Node> nowNodeList = this.nodes.get(nowNode.idx);
			for(int n = 0; n < nowNodeList.size(); n++){
				Node nextNode = new Node(nowNodeList.get(n).idx, nowNodeList.get(n).weight);
				if(this.isSameGenderSchool(nowNode.idx, nextNode.idx) || visited[nextNode.idx] != this.INF) continue;
				pq.add(nextNode);
			}
		}
		return visited;
	}
	
	private int[] initVisited(int[] visited){
		for(int v = 0; v < visited.length; v++) visited[v] = this.INF;
		return visited;
	} 
	
	private boolean isSameGenderSchool(int school1, int school2){
		return this.schoolGender[school1] == this.schoolGender[school2];
	}
	
	private boolean isAllRoadConnected(int[] visited){
		for(int v = 1; v <= this.schools; v++)
			if(visited[v] == this.INF) return false;
		return true;
	}
	
	private static class Node implements Comparable<Node>{
		
		int idx, weight;
		
		public Node(int idx, int weight){
			this.idx = idx;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node node){
			if(this.weight > node.weight) return 1;
			if(this.weight < node.weight) return -1;
			return 0;
		}
		
	}
	
}
