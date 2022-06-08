// 11060 점프 점프
import java.io.*;
import java.util.*;

public class Main{
	
	private static final int INF = 987654321;
	private int[] maze;
	
	public static void main(String[] args){
		(new Main()).solve();
	}

	private void solve(){
		this.input();
		int escapeLength = this.findEscapeLength();
		System.out.println(escapeLength);
	}
	
	private void input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			this.inputMaze(br);
		}catch(IOException IOE){}
	}
	
	private void inputMaze(BufferedReader br) throws IOException{
		this.maze = new int[Integer.parseInt(br.readLine())];
		String[] read = br.readLine().split(" ");
		for(int m = 0; m < this.maze.length; m++) maze[m] = Integer.parseInt(read[m]);
	}
	
	private int findEscapeLength(){
		Queue<Player> players = new LinkedList<Player>();
		players.add(new Player(0, 0));
		int[] jumped = this.initJumped(new int[this.maze.length]);
		while(!players.isEmpty()){
			Player nowPlayer = players.poll();
			if(nowPlayer.jumpCount >= jumped[nowPlayer.idx]) continue;
			jumped[nowPlayer.idx] = nowPlayer.jumpCount;
			for(int j = 1; j <= this.maze[nowPlayer.idx]; j++){
				int nextIdx = nowPlayer.idx + j;
				if(this.isOutOfLength(nextIdx) || jumped[nextIdx] <= nowPlayer.jumpCount+1) continue;
				players.add(new Player(nextIdx, nowPlayer.jumpCount+1));
			}
		}
		return jumped[this.maze.length-1] == this.INF ? -1 : jumped[this.maze.length-1];
	}
	
	private int[] initJumped(int[] jumped){
		for(int j = 0; j < jumped.length; j++) jumped[j] = this.INF;
		return jumped;
	}
	
	private boolean isOutOfLength(int idx){
		return idx >= this.maze.length;
	}
	
	private static class Player{
		
		int idx, jumpCount;
		
		public Player(int idx, int jumpCount){
			this.idx = idx;
			this.jumpCount = jumpCount;
		}
		
	}
	
}
