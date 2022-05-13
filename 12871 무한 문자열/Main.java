import java.io.*;
import java.util.*;

public class Main{
	
	public static void main(String[] args){
		(new Main()).solve();
	}
	
	private void solve(){
		String[] strings = this.inputStrings();
		this.sortString(strings);
		this.balancingString(strings);
		System.out.println(
			this.convertBooleanToInt(this.isInifiniteString(strings[0], strings[1]))
		);
	}
	
	private String[] inputStrings(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			String[] ans = new String[2];
			ans[0] = input(br);
			ans[1] = input(br);
			return ans;
		}catch(IOException IOE){return null;}
	}
	
	private String input(BufferedReader br) throws IOException{
		return br.readLine();
	}
	
	private void sortString(String[] strings){
		if(strings[0].length() <= strings[1].length()) return;
		String temp = strings[0];
		strings[0] = strings[1];
		strings[1] = temp;
	}
	
	private void balancingString(String[] strings){
		int shortStringBalanceNum = strings[1].length();
		int longStringBalanceNum = strings[0].length();
		strings[0] = this.doBalancingString(strings[0], shortStringBalanceNum);
		strings[1] = this.doBalancingString(strings[1], longStringBalanceNum);
	}
	
	private String doBalancingString(String target, int shortStringBalanceNum){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < shortStringBalanceNum; i++) sb.append(target);
		return sb.toString();
	}
	
	private boolean isInifiniteString(String shortString, String longString){
		for(int i = 0; i < longString.length(); i++)
			if(shortString.charAt(i % shortString.length()) != longString.charAt(i)) return false;
		return true;
	}
	
	private int convertBooleanToInt(boolean bool){
		return bool == true ? 1 : 0;
	}
	
}
