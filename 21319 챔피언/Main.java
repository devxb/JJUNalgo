import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main{
    
    public static void main(String[] args){
        Solve solve = new Solve();
		solve.run();
    }
    
}

class Solve{
	
	private class Champion{
		
		private final int initIdx;
		private final int power;
		
		public Champion(int initIdx, int power){
			this.initIdx = initIdx;
			this.power = power;
		}
		
	}
	
	private int N;
	private int startIdx = 987654321;
	private ArrayList<Champion> champions = new ArrayList<Champion>();
	
	public void run(){
		input();
		if(N == 1) System.out.println(1);
		else if(N > 1 && champions.size() == 1) System.out.println(-1);
		else System.out.println(findChampion().toString());
	}
	
	private void input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			this.N = Integer.parseInt(br.readLine());
			String[] read = br.readLine().split(" ");
			for(int i = 1; i <= N; i++){
				if(i > 1 && read[i-2].equals(read[i-1])) continue;
				champions.add(new Champion(i, Integer.parseInt(read[i-1])));
			}
		}catch(IOException IOE){}
	}
	
	private StringBuilder findChampion(){
		StringBuilder sb = new StringBuilder();
		if(!canWin(champions.get(champions.size()-1))){
			sb.append("-1");
			return sb;
		}
		int left = 1, right = champions.size()-1;
		while(left < right){
			int mid = (left+right)/2;
			if(canWin(champions.get(mid))) right = mid;
			else left = mid+1;
		}
		for(Champion champion : champions){
			if(champion.initIdx < startIdx) continue;
			sb.append(champion.initIdx);
			sb.append(" ");
		}
		return sb;
	}
	
	private boolean canWin(Champion champion){
		for(Champion c : champions){
			if(c.initIdx <= champion.initIdx) continue;
			if(c.power >= champion.power + c.initIdx-2) return false;
		}
		startIdx = Math.min(champion.initIdx, startIdx);
		return true;
	}
	
}
