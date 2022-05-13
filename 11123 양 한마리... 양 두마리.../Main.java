import java.io.*;

public class Main{
	
	private final static int[] dy = {-1, 0, 1 ,0};
	private final static int[] dx = {0, -1, 0, 1};
	
	public static void main(String[] args){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			(new Main()).solve(br);
		}catch(IOException IOE){}
	}
	
	private void solve(BufferedReader br) throws IOException{
		int testRepeatCnt = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int test = 0; test < testRepeatCnt; test++){
			char[][] grid = this.inputGrid(br);
			sb.append(this.countSheepSwarm(grid)).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	private char[][] inputGrid(BufferedReader br) throws IOException{
		String[] heightAndWeight = br.readLine().split(" ");
		int height = Integer.parseInt(heightAndWeight[0]);
		int weight = Integer.parseInt(heightAndWeight[1]);
		char[][] grid = new char[height][weight];
		for(int y = 0; y < height; y++){
			String field = br.readLine();
			for(int x = 0; x < weight; x++) grid[y][x] = field.charAt(x);
		}
		return grid;
	}
	
	private int countSheepSwarm(char[][] grid){
		boolean[][] alreadyCounted = new boolean[grid.length][grid[0].length];
		int swarms = 0;
		for(int y = 0; y < grid.length; y++){
			for(int x = 0; x < grid[0].length; x++){
				if(!this.isSheep(grid[y][x]) || alreadyCounted[y][x]) continue;
				this.checkSwarm(y, x, grid, alreadyCounted);
				swarms++;
			}
		}
		return swarms;
	}
	
	private void checkSwarm(int y, int x, char[][] grid, boolean[][] alreadyCounted){
		alreadyCounted[y][x] = true;
		for(int i = 0; i < 4; i++){
			int nextY = y + dy[i];
			int nextX = x + dx[i];
			if(this.isUnexpectedPosition(nextY, nextX, grid, alreadyCounted)) continue;
			this.checkSwarm(nextY, nextX, grid, alreadyCounted);
		}
	}
	
	private boolean isUnexpectedPosition(int y, int x, char[][] grid, boolean[][] alreadyCounted){
		if(y < 0 || y >= grid.length || x < 0 || x >= grid[0].length
		  || alreadyCounted[y][x]
		  || !this.isSheep(grid[y][x])) return true;
		return false;
	}
	
	private boolean isSheep(char sheep){
		return sheep == '#';
	}
	
}
