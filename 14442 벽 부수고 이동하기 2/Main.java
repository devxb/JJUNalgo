import java.util.*;
import java.io.*;

public class Main{
	
	private static final int[] dy = {-1, 0, 1, 0};
	private static final int[] dx = {0, -1, 0, 1};
	private int width, height, breakable;
	private int[][] arr;
	
	public static void main(String[] args){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			(new Main()).solve(br);	
		}catch(IOException IOE){}
	}
	
	private void solve(BufferedReader br) throws IOException{
		this.input(br);
		System.out.println(this.findMinimumDistance());
	}
	
	private void input(BufferedReader br) throws IOException{
		String[] NMK = br.readLine().split(" ");
		this.height = Integer.parseInt(NMK[0]);
		this.width = Integer.parseInt(NMK[1]);
		this.breakable = Integer.parseInt(NMK[2]);
		this.arr = new int[height][width];
		for(int y = 0; y < this.height; y++){
			String line = br.readLine();
			for(int x = 0; x < this.width; x++) arr[y][x] = (int)line.charAt(x) - (int)'0';
		}
	}
	
	private int findMinimumDistance(){
		Queue<PositionWithBreak> q = new LinkedList<PositionWithBreak>();
		q.add(new PositionWithBreak(0,0,1,0));
		int[][][] distanceDp = this.initDistanceDp();
		int minimumDistance = 987654321;
		while(!q.isEmpty()){
			PositionWithBreak nowPos = q.poll();
			if(this.isFar(distanceDp[nowPos.y][nowPos.x][nowPos.breakCount], minimumDistance, nowPos.distance)) continue;
			if(this.isGoal(nowPos.y, nowPos.x)){
				minimumDistance = Math.min(minimumDistance, nowPos.distance);
				continue;
			}
			distanceDp[nowPos.y][nowPos.x][nowPos.breakCount] = nowPos.distance;
			for(int d = 0; d < 4; d++){
				int nextY = nowPos.y + dy[d];
				int nextX = nowPos.x + dx[d];
				int nextDistance = nowPos.distance+1;
				int nextBreakCount = nowPos.breakCount;
				if(this.isOutOfBound(nextY, nextX)) continue;
				if(this.isWall(nextY, nextX)){
					if(!this.isBreakable(nextBreakCount)) continue;
					nextBreakCount++;
				}
				if(this.isFar(distanceDp[nextY][nextX][nextBreakCount], minimumDistance, nextDistance)) continue;
				q.add(new PositionWithBreak(nextY, nextX, nextDistance, nextBreakCount));
			}
		}
		return minimumDistance == 987654321 ? -1 : minimumDistance;
	}
	
	private int[][][] initDistanceDp(){
		int[][][] distanceDp = new int[this.height][this.width][this.breakable+1];
		for(int i = 0; i < this.height; i++)
			for(int j = 0; j < this.width; j++)
				for(int p = 0; p <= this.breakable; p++) distanceDp[i][j][p] = 987654321;
		return distanceDp;
	}
	
	private boolean isFar(int oldDistance, int updatedDistance, int newDistance){
		return oldDistance <= newDistance || updatedDistance <= newDistance;
	}
	
	private boolean isGoal(int y, int x){
		return y == this.height-1 && x == this.width-1;
	}
	
	private boolean isOutOfBound(int y, int x){
		return y >= this.height || y < 0 || x >= this.width || x < 0;
	}
	
	private boolean isWall(int y, int x){
		return this.arr[y][x] == 1;
	}
	
	private boolean isBreakable(int breakCount){
		return this.breakable > breakCount;
	}
	
	private int findMinimumDistanceAtGoal(int[][][] distanceDp){
		int ans = 987654321;
		for(int i = 0; i <= this.breakable; i++) ans = Math.min(ans, distanceDp[this.height-1][this.width-1][i]);
		return ans == 987654321 ? -1 : ans;
	}
	
	private static class PositionWithBreak{
		
		int y, x, distance;
		int breakCount;
		
		public PositionWithBreak(int y, int x, int distance, int breakCount){
			this.y = y;
			this.x = x;
			this.distance = distance;
			this.breakCount = breakCount;
		}
		
	}
	
}
