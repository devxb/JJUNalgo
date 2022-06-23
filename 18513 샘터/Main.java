// 18513 샘터
import java.io.*;
import java.util.*;

public class Main{
	
	private int pond, house;
	private Set<Integer> ponds = new HashSet<Integer>();
	
	public static void main(String[] args){
		(new Main()).solve();
	}
	
	private void solve(){
		this.input();
		System.out.println(this.buildHouse());
	}
	
	private void input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			this.inputPondsAndHouses(br);
		}catch(IOException IOE){}
	}
	
	private void inputPondsAndHouses(BufferedReader br) throws IOException{
		String[] read = br.readLine().split(" ");
		this.pond = Integer.parseInt(read[0]);
		this.house = Integer.parseInt(read[1]);
		read = br.readLine().split(" ");
		for(int i = 0; i < pond; i++) ponds.add(Integer.parseInt(read[i]));
	}
	
	private long buildHouse(){
		Queue<House> houses = new LinkedList<House>();
		Set<House> alreadyBuild = new HashSet<House>();
		int builtCount = -1*this.pond;
		long ans = 0;
		for(int pondIdx : this.ponds) houses.add(new House(pondIdx, 0));
		while(builtCount < this.house){
			House nowHouse = houses.poll();
			if(alreadyBuild.contains(nowHouse)) continue;
			alreadyBuild.add(nowHouse);
			ans += nowHouse.weight;
			builtCount++;
			houses.add(new House(nowHouse.idx+1, nowHouse.weight+1));
			houses.add(new House(nowHouse.idx-1, nowHouse.weight+1));
		}
		return ans;
	}
	
	private static class House{
		
		long weight, idx;
		
		public House(long idx, long weight){
			this.idx = idx;
			this.weight = weight;
		}
		
		@Override
		public int hashCode(){
			return Objects.hash(this.idx);
		}
		
		@Override
		public boolean equals(Object o){
			if(!(o instanceof House)) return false;
			House ho = (House)o;
			return ho.idx == idx;
		}
		
	}
	
}
