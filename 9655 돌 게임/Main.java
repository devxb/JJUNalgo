// 9655 돌 게임
import java.io.*;
import static java.lang.Math.min;

public class Main{
	
	private static final int[] pick = {1, 3};
	private static final int SK = 1, CY = 2;
	private int stone;
	private int[][] dp;
	
	public static void main(String[] args){
		(new Main()).solve();
	}

	private void solve(){
		this.inputStone();
		this.initDp();
		int pickResult = this.pickStone(this.stone, this.SK);
		System.out.println((pickResult == this.SK ? "SK" : "CY"));
	}
	
	private void inputStone(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			this.stone = Integer.parseInt(br.readLine());
		}catch(IOException IOE){}
	}
	
	private void initDp(){
		this.dp = new int[this.stone+1][3];
	}
	
	private int pickStone(int remainStone, int turn){
		if(turn == 0) return nextTurn(turn);
		if(this.dp[remainStone][turn] != 0) return this.dp[remainStone][turn];
		for(int i = 0; i < this.pick.length; i++){
			if(remainStone - this.pick[i] < 0) continue;
			int winPlayer = this.pickStone(remainStone-this.pick[i], this.nextTurn(turn));
			if(winPlayer == turn) this.dp[remainStone][turn] = winPlayer;
		}
		if(this.dp[remainStone][turn] == 0) this.dp[remainStone][turn] = this.nextTurn(turn);
		return dp[remainStone][turn];
	}
	
	private int nextTurn(int nowTurn){
		return nowTurn == this.SK ? this.CY : this.SK;
	}
	
}
