// 백준 22866 탑 보기

import java.util.*;
import java.io.*;

public class Main{
	
	public static void main(String[] args){
		Main main = new Main();
		main.solve();
	}
	
	private void solve(){
		int[] towerHeights = this.getTowerHeights();
		Tower[] towers = this.countWatchableTowers(towerHeights);
		this.printTower(towers);
	}
	
	private int[] getTowerHeights(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			return this.input(br);	
		}catch(IOException IOE){
			return new int[0];
		}
	}
	
	private int[] input(BufferedReader br) throws IOException{
		int N = Integer.parseInt(br.readLine());
		String[] heights = br.readLine().split(" ");
		int[] ans = new int[heights.length];
		for(int i = 0; i < N; i++) ans[i] = Integer.parseInt(heights[i]);
		return ans;
	}
	
	private Tower[] countWatchableTowers(int[] towerHeights){
		Tower[] watchableTowerCounts = new Tower[towerHeights.length];
		this.calcLeftWatchableTowerCounts(watchableTowerCounts, towerHeights);
		this.calcRightWatchableTowerCounts(watchableTowerCounts, towerHeights);
		return watchableTowerCounts;
	}
	
	private void calcLeftWatchableTowerCounts(Tower[] watchableTowerCounts, int[] towerHeights){
		Stack<Tower> continuosDecreaseStack = new Stack<Tower>();
		for(int i = 0; i < towerHeights.length; i++){
			int nowTowerHeight = towerHeights[i];
			this.deleteUnableWatchTowers(nowTowerHeight, continuosDecreaseStack);
			Tower nowTower = new Tower(i, this.getCloseTowerIdx(continuosDecreaseStack, -987654321), nowTowerHeight, continuosDecreaseStack.size());
			watchableTowerCounts[i] = nowTower;
			continuosDecreaseStack.add(nowTower);
		}
	}
	
	private void calcRightWatchableTowerCounts(Tower[] watchableTowerCounts, int[] towerHeights){
		Stack<Tower> continuosDecreaseStack = new Stack<Tower>();
		for(int i = towerHeights.length-1; i >= 0; i--){
			int nowTowerHeight = towerHeights[i];
			this.deleteUnableWatchTowers(nowTowerHeight, continuosDecreaseStack);
			watchableTowerCounts[i].watchableTowerCount += continuosDecreaseStack.size();
			watchableTowerCounts[i].right = this.getCloseTowerIdx(continuosDecreaseStack, 987654321);
			continuosDecreaseStack.add(watchableTowerCounts[i]);
		}
	}
	
	private void deleteUnableWatchTowers(int towerHeight, Stack<Tower> continuosDecreaseStack){
		while(!continuosDecreaseStack.isEmpty() && towerHeight >= continuosDecreaseStack.peek().height) continuosDecreaseStack.pop();
	}
	
	private int getCloseTowerIdx(Stack<Tower> continuosDecreaseStack, int errReturn){
		if(continuosDecreaseStack.isEmpty()) return errReturn;
		return continuosDecreaseStack.peek().idx;
	}
	
	private void printTower(Tower[] towers){
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < towers.length; i++){
			if(towers[i].watchableTowerCount == 0) sb.append(towers[i].watchableTowerCount).append("\n");
			else sb.append(towers[i].watchableTowerCount).append(" ").append(getCloseTowerIdx(towers[i])).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	private int getCloseTowerIdx(Tower tower){
		int leftTowerIdxDistance = tower.idx - tower.left;
		int rightTowerIdxDistance = tower.right - tower.idx;
		if(leftTowerIdxDistance <= rightTowerIdxDistance) return tower.left+1;
		else return tower.right+1;
	}
	
	private static class Tower{
		
		int idx;
		int height;
		int watchableTowerCount;
		int right,left;
		
		public Tower(int idx, int left, int height, int watchableTowerCount){
			this.idx = idx;
			this.left = left;
			this.right = -1;
			this.height = height;
			this.watchableTowerCount = watchableTowerCount;
		}
		
	}
	
}
