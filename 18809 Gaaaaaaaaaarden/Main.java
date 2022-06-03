import java.io.*;
import java.util.*;

public class Main{
	
	private final static int[] dy = {-1, 0, 1, 0};
	private final static int[] dx = {0, -1, 0, 1};
	private int height, width;
	private int greenDropCount, redDropCount;
	private int[][] garden;
	private List<Position> positions = new ArrayList<Position>();
	private Set<Long> combinationCheck = new HashSet<Long>();
	private long maxCombination;
	
	public static void main(String[] args){
		(new Main()).solve();
	}
	
	private void solve(){
		this.input();
		System.out.println(this.combineDrop(0, 0, 0, 9L));
	}
	
	private void input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			this.inputInit(br);
			this.inputGarden(br);
		}catch(IOException IOE){}
	}
	
	private void inputInit(BufferedReader br) throws IOException{
		String[] read = br.readLine().split(" ");
		this.height = Integer.parseInt(read[0]);
		this.width = Integer.parseInt(read[1]);
		this.greenDropCount = Integer.parseInt(read[2]);
		this.redDropCount = Integer.parseInt(read[3]);
	}
	
	private void inputGarden(BufferedReader br) throws IOException{
		this.garden = new int[this.height][this.width];
		for(int y = 0; y < this.height; y++){
			String[] read = br.readLine().split(" ");
			for(int x = 0; x < this.width; x++) {
				this.garden[y][x] = Integer.parseInt(read[x]);
				if(this.garden[y][x] == 2) this.positions.add(new Position(y, x));
			}
		}
		this.setMaxCombination();
	}
	
	private void setMaxCombination(){
		this.maxCombination = 1;
		for(int i = 0; i < positions.size(); i++) this.maxCombination *= 10;
	}
	
	private int combineDrop(int idx, int cosumedGreenDropCount, int cosumedRedDropCount, long combination){
		if(cosumedRedDropCount == this.redDropCount && cosumedGreenDropCount == this.greenDropCount){
			while(combination < this.maxCombination) combination *= 10;
			if(this.combinationCheck.contains(combination)) return 0;
			this.combinationCheck.add(combination);
			int flower = this.bloom(combination);
			return flower;
		}
		int ans = 0;
		for(int i = idx; i < this.positions.size(); i++){
			if(cosumedGreenDropCount < this.greenDropCount) 
				ans = Math.max(ans, this.combineDrop(i+1, cosumedGreenDropCount+1, cosumedRedDropCount, combination*10+1L));
			if(cosumedRedDropCount < this.redDropCount)
				ans = Math.max(ans, this.combineDrop(i+1, cosumedGreenDropCount, cosumedRedDropCount+1, combination*10+2L));
			combination *= 10;
		}
		return ans;
	}
	
	private int bloom(long combination){
		int[][][] check = new int[3][this.height][this.width];
		boolean[][] bloomed = new boolean[this.height][this.width];
		LinkedList<Drop> drops = new LinkedList<Drop>();
		int idx = 0;
		while(combination != 9){
			int color = (int)(combination % 10L);
			combination /= 10;
			if(color == 0){
				idx++;
				continue;
			}
			drops.addLast(new Drop(color, 1, positions.get(idx)));
			check[color][positions.get(idx).y][positions.get(idx).x] = 1;
			idx++;
		}
		while(!drops.isEmpty()){
			Drop nowDrop = drops.pollFirst();
			int nowY = nowDrop.position.y, nowX = nowDrop.position.x;
			if(bloomed[nowY][nowX]) continue;
			for(int d = 0; d < 4; d++){
				int nextY = nowY + dy[d];
				int nextX = nowX + dx[d];
				if(this.isOutOfBound(nextY, nextX) || this.isPond(nextY, nextX) || !this.isPutable(nextY, nextX, nowDrop.color, check)) continue;
				check[nowDrop.color][nextY][nextX] = nowDrop.time+1; 
				if(!this.isPutable(nextY, nextX, this.reverseColor(nowDrop.color), check)){
					if(check[this.reverseColor(nowDrop.color)][nextY][nextX] == nowDrop.time+1) bloomed[nextY][nextX] = true;
					continue;
				}
				drops.addLast(new Drop(nowDrop.color, nowDrop.time+1, new Position(nextY, nextX)));
			}
		}
		return this.countBloomed(bloomed);
	}
	
	private boolean isOutOfBound(int nextY, int nextX){
		return nextY >= this.height || nextY < 0 || nextX >= this.width || nextX < 0;
	}
	
	private boolean isPond(int nextY, int nextX){
		return this.garden[nextY][nextX] == 0;
	}
	
	private boolean isPutable(int nextY, int nextX, int color, int[][][] check){
		return check[color][nextY][nextX] == 0;
	}
	
	private int reverseColor(int color){
		return color == 1 ? 2 : 1;
	}
	
	private int countBloomed(boolean[][] bloomed){
		int ans = 0;
		for(boolean[] bloomLine : bloomed)
			for(boolean isBloom : bloomLine) if(isBloom) ans++;
		return ans;
	}
	
	private static class Position{
		
		int y, x;
		
		public Position(int y, int x){
			this.y = y;
			this.x = x;
		}
		
	}
	
	private static class Drop{
		
		int color, time;
		Position position;
		
		public Drop(int color, int time, Position position){
			this.time = time;
			this.color = color;
			this.position = position;
		}
		
	}
	
}
