import java.io.*;
import java.util.*;

public class Main{
	
	private static final int[] dy = {-1, 0, 1, 0};
	private static final int[] dx = {0, -1, 0, 1};
	
	public static void main(String[] args){
		(new Main()).solve();
	}
	
	private void solve(){
		int[][] board = this.input();
		int killPieceCount = this.playBaduk(0, board);
		System.out.println(killPieceCount);
	}
	
	private int[][] input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			return this.inputBoard(br);
		}catch(IOException IOE){}
		return null;
	}
	
	private int[][] inputBoard(BufferedReader br) throws IOException{
		String[] line = br.readLine().split(" ");
		int height = Integer.parseInt(line[0]);
		int width = Integer.parseInt(line[1]);
		int[][] board = new int[height][width];
		for(int y = 0; y < height; y++){
			line = br.readLine().split(" ");
			for(int x = 0; x < width; x++) board[y][x] = Integer.parseInt(line[x]);
		}
		return board;
	}
	
	private int playBaduk(int killCount, int[][] board){
		if(this.allKill(killCount)) return this.findKillablePieceCount(board);
		int ans = 0;
		for(int nowY = 0; nowY < board.length; nowY++){
			for(int nowX = 0; nowX < board[nowY].length; nowX++){
				if(!this.isPutable(board[nowY][nowX])) continue;
				board[nowY][nowX] = 1;
				ans = Math.max(ans, this.playBaduk(killCount+1, board));
				board[nowY][nowX] = 0;
			}
		}
		return ans;
	}
	
	private boolean allKill(int killCount){
		return killCount == 2;
	}
	
	private int findKillablePieceCount(int[][] board){
		int deadPieces = 0;
		boolean[][] alreadyChecked = new boolean[board.length][board[0].length];
		for(int y = 0; y < board.length; y++){
			for(int x = 0; x < board[y].length; x++){
				if(alreadyChecked[y][x] || board[y][x] != 2) continue;
				deadPieces += Math.max(0, 1+this.findDeadPieces(y, x, board, alreadyChecked));
			}
		}
		return deadPieces;
	}
	
	private int findDeadPieces(int y, int x, int[][] board, boolean[][] alreadyChecked){
		if(alreadyChecked[y][x]) return -1;
		alreadyChecked[y][x] = true;
		int ans = 0;
		for(int d = 0; d < 4; d++){
			int nextY = y + this.dy[d];
			int nextX = x + this.dx[d];
			if(this.isOutOfBound(nextY, nextX, board) || board[nextY][nextX] == 1 || alreadyChecked[nextY][nextX]) continue;
			if(board[nextY][nextX] == 2) ans += 1 + this.findDeadPieces(nextY, nextX, board, alreadyChecked);
			else if(board[nextY][nextX] == 0) ans += -500;
		}
		return ans;
	}
	
	private boolean isOutOfBound(int y, int x, int[][] board){
		return y < 0 || y >= board.length || x < 0 || x >= board[0].length;
	}
	
	private boolean isPutable(int state){
		return state == 0;
	}
	
}
