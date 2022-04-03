// boj 1195 킥다운

import java.io.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Main{
	
	private String firstPart;
	private String secondPart;
	
	public static void main(String[] args){
		Main main = new Main();
		main.solve();
	}
	
	public void solve(){
		this.input();
		System.out.println(this.getCombinationSize());
	}
	
	private void input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			this.firstPart = br.readLine();
			this.secondPart = br.readLine();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	
	private int getCombinationSize(){
		int res = this.firstPart.length() + this.secondPart.length();
		for(int i = 0; i < secondPart.length(); i++){
			if(!this.isStringCombineable(i, this.firstPart, this.secondPart)) continue;
			res = min(res, max(this.firstPart.length() + i, this.secondPart.length()));
		}
		
		for(int i = 0; i < firstPart.length(); i++){
			if(!this.isStringCombineable(i, this.secondPart, this.firstPart)) continue;
			res = min(res, max(this.secondPart.length() + i, this.firstPart.length()));
		}
		return res;
	}
	
	private boolean isStringCombineable(int rightMoveIdx, String movePart, String staticPart){
		for(int i = 0; i < min(movePart.length(), staticPart.length()-rightMoveIdx); i++)
			if(!this.isCharCombineable(movePart.charAt(i), staticPart.charAt(i+rightMoveIdx))) return false;
		return true;
	}
	
	private boolean isCharCombineable(char moveC, char staticC){
		return !(moveC == '2' && staticC == '2') ? true : false;
	}
	
}
