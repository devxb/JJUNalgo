// 16564 히오스 프로게이머
import java.io.*;
import java.util.*;

public class Main{
	
	private int levelUp;
	private int[] levels;
	
	public static void main(String[] args){
		(new Main()).solve();
	}
	
	private void solve(){
		this.input();
		int teamLevel = this.levelUpTeamLevel();
		System.out.println(teamLevel);
	}
	
	private void input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			this.inputLevelAndK(br);
		}catch(IOException IOE){}
	}
	
	private void inputLevelAndK(BufferedReader br) throws IOException{
		String[] read = br.readLine().split(" ");
		this.levels = new int[Integer.parseInt(read[0])];
		this.levelUp = Integer.parseInt(read[1]);
		for(int i = 0; i < this.levels.length; i++) levels[i] = Integer.parseInt(br.readLine());
		Arrays.sort(this.levels);
	}
	
	private int levelUpTeamLevel(){
		int maxFairLevelIdx = this.getMaxFairLevelIdx();
		for(int i = 0; i <= maxFairLevelIdx; i++){
			this.levelUp -= (this.levels[maxFairLevelIdx] - this.levels[i]);
			this.levels[i] = this.levels[maxFairLevelIdx];
		}
		return this.levels[maxFairLevelIdx] + (this.levelUp / (maxFairLevelIdx+1));
	}
	
	private int getMaxFairLevelIdx(){
		int spendedLevelUp = 0;
		for(int i = 1; i < this.levels.length; i++){
			spendedLevelUp += i*(this.levels[i] - this.levels[i-1]);
			if(spendedLevelUp == this.levelUp) return i;
			if(spendedLevelUp > this.levelUp) return i-1;
		}
		if(spendedLevelUp == 0) return 0;
		return this.levels.length-1;
	}
	
}
