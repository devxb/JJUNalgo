// 16438 원숭이 스포츠
import java.io.*;
import static java.lang.Math.pow;

public class Main{
	
	private int monkeys;
	private char[][] monkeyTeam;
	
	public static void main(String[] args){
		(new Main()).solve();
	}
	
	private void solve(){
		this.input();
		this.divideTeam(0, 0, (this.monkeys-1)/2, 'A');
		this.divideTeam(0, (this.monkeys-1)/2+1, this.monkeys-1, 'B');
		System.out.print(this.buildResultPrinter(new StringBuilder()).toString());
	}
	
	private void input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			this.inputMonkeys(br);
			this.monkeyTeam = new char[7][this.monkeys];
		}catch(IOException IOE){}
	}
	
	private void inputMonkeys(BufferedReader br) throws IOException{
		this.monkeys = Integer.parseInt(br.readLine());
	}
	
	private void divideTeam(int day, int left, int right, char team){
		if(day == 7 || left > right) return;
		for(int i = left; i <= right; i++) this.monkeyTeam[day][i] = team;
		this.divideTeam(day+1, left, (left+right)/2, 'A');
		this.divideTeam(day+1, (left+right)/2+1, right, 'B');
	}
	
	private StringBuilder buildResultPrinter(StringBuilder resultPrinter){
		for(char[] row : this.monkeyTeam){
			boolean allSameTeam = true;
			for(char monkey : row) {
				resultPrinter.append(monkey);
				if(monkey == 'B') allSameTeam = false;
			}
			if(allSameTeam) resultPrinter.replace(resultPrinter.length()-1, resultPrinter.length(), "B");
			resultPrinter.append("\n");
		}
		return resultPrinter;
	}
	
}
