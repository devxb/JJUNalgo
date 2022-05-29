// boj 5875

import java.io.*;

public class Main{
	
	public static void main(String[] args){
		(new Main()).solve();
	}
	
	private void solve(){
		String brackets = this.inputBrackets();
		int fixablePoint = this.countBracketsFixablePointCount(brackets);
		System.out.println(fixablePoint);
	}
	
	private String inputBrackets(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			return br.readLine();
		}catch(IOException IOE){}
		return null;
	}
	
	private int countBracketsFixablePointCount(String brackets){
		int[] bracketSum = this.sumBrackets(brackets);
		int leftMinusIdx = this.getLeftMinusIdx(bracketSum);
		int[] leftPlusArray = this.getLeftPlusArray(bracketSum);
		int fixablePoint = 0;
		boolean fixed = false;
		for(int i = 0; i < brackets.length(); i++){
			fixed = false;
			int lastPoint = bracketSum[bracketSum.length-1];
			if(this.isCloseBracket(brackets.charAt(i)) && i < brackets.length()-1 && i <= leftMinusIdx) {
				fixed = true;
				lastPoint += 2;
			}
			else if(this.isOpenBracket(brackets.charAt(i)) && i > 0 && leftPlusArray[i] == 0){
				fixed = true;
				lastPoint -= 2;
			}
			if(lastPoint == 0 && fixed) fixablePoint++;
		}
		return fixablePoint;
	}
	
	private int[] sumBrackets(String brackets){
		int[] bracketSum = new int[brackets.length()];
		bracketSum[0] = this.isOpenBracket(brackets.charAt(0)) ? 1 : -1;
		for(int i = 1; i < bracketSum.length; i++) bracketSum[i] = this.isOpenBracket(brackets.charAt(i)) ? bracketSum[i-1] + 1 : bracketSum[i-1] - 1;
		return bracketSum;
	}
	
	private boolean isOpenBracket(char bracket){
		return bracket == '(';
	}
	
	private boolean isCloseBracket(char bracket){
		return bracket == ')';
	}

	private int getLeftMinusIdx(int[] bracketSum){
		for(int idx = 0; idx < bracketSum.length; idx++) if(bracketSum[idx] < 0) return idx;
		return 0;
	}
	
	private int[] getLeftPlusArray(int[] bracketSum){
		int[] leftPlusArray = new int[bracketSum.length+1];
		for(int idx = bracketSum.length-1; idx >= 0; idx--) {
			if(bracketSum[idx] == 1 || bracketSum[idx] == 0) leftPlusArray[idx]++;
			leftPlusArray[idx] += leftPlusArray[idx+1];
		}
		return leftPlusArray;
	}
	
}
