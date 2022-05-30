import java.io.*;
import java.util.*;

public class Main{
	
	private static final int[] dy = {-1, 0, 1, 0};
	private static final int[] dx = {0, -1, 0, 1};
	private String[] seat;
	private int[][] pick = new int[5][5];
	private List<Integer> pickList = new ArrayList<Integer>();
	
	public static void main(String[] args){
		(new Main()).solve();
	}
	
	private void solve(){
		this.seat = this.inputSeat();
		this.makeRanges(0, 1);
		int sevenPrincess = this.countSevenPrincess(0, 0);
		System.out.println(sevenPrincess);
	}
	
	private String[] inputSeat(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			return this.inputSeat(br);
		}catch(IOException IOE){}
		return null;
	}
	
	private String[] inputSeat(BufferedReader br) throws IOException{
		String[] seat = new String[5];
		for(int i = 0; i < 5; i++) seat[i] = br.readLine();
		return seat;
	}
	
	private void makeRanges(int idx, int picked){
		if(idx == 5){
			this.pickList.add(picked);
			return;
		}
		this.makeRanges(idx+1, picked*10);
		this.makeRanges(idx+1, picked*10+1);
	}
	
	private int countSevenPrincess(int y, int pickedCount){
		if(pickedCount == 7){
			if(this.isSevenPrincess()) return 1;
			return 0;
		}
		if(y == 5) return 0;
		int ans = 0;
		for(Integer currentPick : this.pickList){
			int currentPickedCount = this.countPickedCount(currentPick);
			if(currentPickedCount + pickedCount > 7) continue;
			this.doPick(y, currentPick);
			ans += this.countSevenPrincess(y+1, currentPickedCount + pickedCount);
			this.undoPick(y, currentPick);
		}
		return ans;
	}
	
	private boolean isSevenPrincess(){
		Queue<Position> q = this.getStartPosition();
		int connect = 0;
		int dasom = 0;
		boolean[][] check = new boolean[5][5];
		check[q.peek().y][q.peek().x] = true;
		while(!q.isEmpty()){
			Position position = q.poll();
			connect++;
			if(seat[position.y].charAt(position.x) == 'S') dasom++;
			for(int d = 0; d < 4; d++){
				int ny = position.y + this.dy[d];
				int nx = position.x + this.dx[d];
				if(this.isOutOfBound(ny, nx) || check[ny][nx]) continue;
				check[ny][nx] = true;
				q.add(new Position(ny, nx));
			}
		}
		if(dasom < 4 || connect != 7) return false;
		return true;
	}
	
	private Queue<Position> getStartPosition(){
		Queue<Position> q = new LinkedList<Position>();
		for(int y = 0; y < 5; y++){
			for(int x = 0; x < 5; x++){
				if(this.pick[y][x] == 1) {
					q.add(new Position(y, x));
					return q;
				}
			}
		}
		return q;
	}
	
	private boolean isOutOfBound(int y, int x){
		return y < 0 || y >= 5 || x < 0 || x >= 5 || pick[y][x] == 0;
	}
	
	private int countPickedCount(int picked){
		int ans = 0;
		for(int i = 0; i < 5; i++){
			if(picked % 10 == 1) ans++;
			picked /= 10;
		}
		return ans;
	}
	
	private void doPick(int y, int picked){
		this.pick(y, picked, 1);
	}
	
	private void undoPick(int y, int picked){
		this.pick(y, picked, 0);
	}
	
	private void pick(int y, int picked, int trigger){
		for(int i = 4; i >= 0; i--){
			if(picked % 10 == 1) this.pick[y][i] = trigger;
			picked /= 10;
		}
	}
	
	private static class Position{
		
		int y, x;
		
		public Position(int y, int x){
			this.y = y;
			this.x = x;
		}
		
	}
	
}
