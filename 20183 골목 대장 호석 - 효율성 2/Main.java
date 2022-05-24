import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Main{
	
	private int node;
	private int edge;
	private int startNode, endNode;
	private long money;
	private List<ArrayList<Street>> streets;
	
	public static void main(String[] args){
		(new Main()).solve();
	}
	
	private void solve(){
		this.input();
		long shame = this.findMinShame();
		System.out.println(shame);
	}
	
	private void input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			this.input(br);
		}catch(IOException IOE){}
	}
	
	private void input(BufferedReader br) throws IOException{
		String[] line = br.readLine().split(" ");
		this.node = Integer.parseInt(line[0]);
		this.edge = Integer.parseInt(line[1]);
		this.startNode = Integer.parseInt(line[2]);
		this.endNode = Integer.parseInt(line[3]);
		this.money = Long.parseLong(line[4]);
		List<ArrayList<Street>> streets = new ArrayList<ArrayList<Street>>(node);
		for(int i = 0; i <= node; i++) streets.add(new ArrayList<Street>());
		for(int i = 0; i < edge; i++){
			line = br.readLine().split(" ");
			int from = Integer.parseInt(line[0]);
			int to = Integer.parseInt(line[1]);
			long money = Long.parseLong(line[2]);
			streets.get(from).add(new Street(from, to, money));
			streets.get(to).add(new Street(to, from, money));
		}
		this.streets = streets;
	}
	
	private long findMinShame(){
		PriorityQueue<Position> pq = new PriorityQueue<Position>();
		pq.add(new Position(this.startNode, 0, 0));
		long[] check = new long[this.node+1];
		for(int i = 0; i <= this.node; i++) check[i] = 9223372036854775800L;
		while(!pq.isEmpty()){
			Position position = pq.poll();
			if(position.maxWeight >= check[position.idx]) continue;
			if(position.idx == this.endNode) return position.maxWeight;
			check[position.idx] = position.maxWeight;
			List<Street> nowStreets = this.streets.get(position.idx);
			for(int i = 0; i < nowStreets.size(); i++){
				Street nextStreet = nowStreets.get(i);
				int nextIdx = nextStreet.to;
				long nextMaxWeight = max(position.maxWeight, nextStreet.money);
				long nextSumWeight = position.sumWeight + nextStreet.money;
				if(check[nextIdx] <= nextMaxWeight || nextSumWeight > this.money) continue;
				Position nextPosition = new Position(nextIdx, nextMaxWeight, nextSumWeight);
				pq.add(nextPosition);
			}
		}
		return -1;
	}
	
	private static class Street{
		
		int from, to; 
		long money;
		
		public Street(int from, int to, long money){
			this.from = from;
			this.to = to;
			this.money = money;
		}
		
		@Override
		public int hashCode(){
			return Objects.hash(this.from, this.to);
		}
		
		@Override
		public boolean equals(Object obj){
			if(!(obj instanceof Street)) return false;
			Street street = (Street)obj;
			if(this.from == street.from && this.to == street.to && this.money == street.money) return true;
			return false;
		}
		
	}
	
	private static class Position implements Comparable<Position>{
		
		int idx;
		long maxWeight, sumWeight;
		
		public Position(int idx, long maxWeight, long sumWeight){
			this.idx = idx;
			this.maxWeight = maxWeight;
			this.sumWeight = sumWeight;
		}
		
		@Override
		public int compareTo(Position position){
			if(this.maxWeight > position.maxWeight) return 1;
			if(this.maxWeight < position.maxWeight) return -1;
			if(this.maxWeight == position.maxWeight){
				if(this.sumWeight > position.sumWeight) return 1;
				if(this.sumWeight < position.sumWeight) return -1;
			}
			return 0;
		}
		
	}
	
}
