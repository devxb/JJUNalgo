// 백준 16401 과자 나눠주기

import java.util.*;
import java.io.*;
import static java.lang.Math.max;

public class Main{
	
	private int cousin;
	private int[] snacks;
	
	public static void main(String[] args){
		Main algorithm = new Main();
		algorithm.solve();
	}
	
	private void solve(){
		this.input();
		System.out.println(this.getMaxSnackLength());
	}
	
	private void input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			this.input(br);
		}catch(IOException IOE){
			return;
		}
	}
	
	private void input(BufferedReader br) throws IOException{
		String[] inputs = br.readLine().split(" ");
		this.cousin = Integer.parseInt(inputs[0]);
		this.snacks = new int[Integer.parseInt(inputs[1])];
		String[] snackString = br.readLine().split(" ");
		for(int i = 0; i < snacks.length; i++) this.snacks[i] = Integer.parseInt(snackString[i]);
		Arrays.sort(this.snacks);
	}
	
	private int getMaxSnackLength(){
		int maxSnackLength = 0;
		int left = 1, right = 1000000000;
		for(int i = 0; i < 64; i++){
			int snackLength = (left + right) / 2;
			if(this.isSnackDivideable(snackLength)) {
				maxSnackLength = max(maxSnackLength, snackLength);
				left = snackLength+1;
			}
			else right = snackLength-1;
		}
		return maxSnackLength;
	}
	
	private boolean isSnackDivideable(int snackLength){
		if(snackLength <= 0) return false;
		int divideTarget = this.cousin;
		for(int i = this.snacks.length-1; i >= 0; i--){
			if(this.snacks[i] < snackLength || divideTarget <= 0) break;
			divideTarget = divideTarget - (this.snacks[i] / snackLength);
		}
		if(divideTarget <= 0) return true;
		return false;
	}
	
}
