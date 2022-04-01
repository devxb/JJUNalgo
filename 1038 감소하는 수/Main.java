// boj 1038 감소하는 수

import java.io.*;

import static java.lang.Math.min;

public class Main{
	
	private int N;
	private long res = -1;
	private boolean[] digitCheck = new boolean[12];
	
	public static void main(String[] args){
		Main main = new Main();
		main.run();
	}
	
	private void run(){
		this.input();
		this.recur(9, 1, 0);
		System.out.println(this.res);
	}
	
	private void input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			this.N = Integer.parseInt(br.readLine());
		}catch(IOException IOE){
			IOE.printStackTrace();
		}
	}
	
	private void recur(int maxNum, int digit, long res){
		this.digitCheck[digit] = true;
		if(digit > 10 || this.res != -1) return;
		if(digit == 0){
			this.N--;
			if(this.N == -1) this.res = res;
			return;
		}
		if(maxNum < 0) return;
		for(int num = 0; num <= maxNum; num++) recur(num-1, digit-1, res+((long)Math.pow(10,digit-1)*num));
		if(!this.digitCheck[digit+1]) recur(9, digit+1, 0L);
	}
	
}
