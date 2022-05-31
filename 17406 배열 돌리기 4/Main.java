import java.io.*;
import java.util.*;

public class Main{
	
	private static int INF = 987654321;
	private BufferedReader br;
	private int[][] ordinary;
	private int[][] arr;
	private int[][] temp;
	private boolean[] picked;
	private int queryCount;
	private List<ArrayList<Integer>> querys;
	private int arrMinNum = INF;
	
	{
		this.br = new BufferedReader(new InputStreamReader(System.in));	
	}
	
	public static void main(String[] args) throws IOException{
		(new Main()).solve();
	}
	
	private void solve() throws IOException{
		this.inputInit();
		this.queryOnArr(9, 0);
		System.out.println(arrMinNum);
	}
	
	private void inputInit() throws IOException{
		String[] read = br.readLine().split(" ");
		arr = new int[Integer.parseInt(read[0])+1][Integer.parseInt(read[1])+1];
		temp = new int[arr.length][arr[0].length];
		ordinary = new int[arr.length][arr[0].length];
		queryCount = Integer.parseInt(read[2]);
		for(int y = 1; y < arr.length; y++){
			read = br.readLine().split(" ");
			for(int x = 0; x < read.length; x++) {
				arr[y][x+1] = Integer.parseInt(read[x]);
				ordinary[y][x+1] = arr[y][x+1];
			}
		}
		picked = new boolean[queryCount+1];
		querys = new ArrayList<ArrayList<Integer>>(queryCount+1);
		querys.add(new ArrayList<Integer>());
		for(int q = 0; q < queryCount; q++){
			read = br.readLine().split(" ");
			int r = Integer.parseInt(read[0]);
			int c = Integer.parseInt(read[1]);
			int s = Integer.parseInt(read[2]);
			int topY = r-s, topX = c-s;
			int botY = r+s, botX = c+s;
			querys.add(new ArrayList<Integer>(Arrays.asList(topY, topX, botY, botX)));
		}
	}
	
	private void queryOnArr(int query, int pickCount){
		if(pickCount == queryCount){
			this.setArrOrdinary();
			while(query != 9){
				List<Integer> nowQuery = querys.get(query % 10);
				this.turnArr(nowQuery);
				query /= 10;
			}
			arrMinNum = Math.min(arrMinNum, this.calcArrMinNum());
		}
		for(int q = 1; q < querys.size(); q++){
			if(picked[q]) continue;
			picked[q] = true;
			queryOnArr(query*10 + q, pickCount+1);
			picked[q] = false;
		}
	}
	
	private void setArrOrdinary(){
		for(int y = 1; y < arr.length; y++)
			for(int x = 1; x < arr[y].length; x++) arr[y][x] = ordinary[y][x];
	}
	
	private void turnArr(List<Integer> query){
		int topY = query.get(0), topX = query.get(1);
		int botY = query.get(2), botX = query.get(3);
		int layer = (botY - topY + 1) / 2;
		for(int y = 1; y < arr.length; y++){
			for(int x = 1; x < arr[y].length; x++) temp[y][x] = 0;
		}
		for(int l = 0; l < layer; l++){
			for(int x = topX+l; x <= botX-l; x++){
				if(x == topX+l) temp[topY+l][x] = arr[topY+l+1][x];
				else temp[topY+l][x] = arr[topY+l][x-1];
				if(x == botX-l) temp[botY-l][x] = arr[botY-l-1][x];
				else temp[botY-l][x] = arr[botY-l][x+1];
			}
			for(int y = topY+l; y <= botY-l; y++){
				if(y == topY+l) temp[y][botX-l] = arr[y][botX-l-1];
				else temp[y][botX-l] = arr[y-1][botX-l];
				if(y == botY-l) temp[y][topX+l] = arr[y][topX+l+1];
				else temp[y][topX+l] = arr[y+1][topX+l];
			}
		}
		this.copyTempToArr(topY, topX, botY, botX);
	}
	
	private void copyTempToArr(int topY, int topX, int botY, int botX){
		for(int y = topY; y <= botY; y++) 
			for(int x = topX; x <= botX; x++) {
				if(temp[y][x] == 0) continue;
				arr[y][x] = temp[y][x];
			}
	}
	
	private int calcArrMinNum(){
		int sum = INF;
		for(int y = 1; y < arr.length; y++){
			int columnSum = 0;
			for(int x = 1; x < arr[y].length; x++) columnSum += arr[y][x];
			sum = Math.min(sum, columnSum);
		}
		return sum;
	}
	
}
