import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public class Main{
    
    public static void main(String[] args){
        Solve solve = new Solve();
		solve.run();
    }
    
}

class Solve{
	
	private int N, M;
	private char[][] arr;
	private HashMap<Character, Character> dic = new HashMap<Character, Character>();

	public void run(){
		init();
		input();
		print();
	}
	
	private void init(){
		dic.put('.','.');
		dic.put('O','O');
		dic.put('-','|');
		dic.put('|','-');
		dic.put('/','\\');
		dic.put('\\','/');
		dic.put('^','<');
		dic.put('<','v');
		dic.put('v','>');
		dic.put('>','^');
	}
	
	private void input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			String[] read = br.readLine().split(" ");
			this.N = Integer.parseInt(read[0]);
			this.M = Integer.parseInt(read[1]);
			arr = new char[N][M];
			for(int i = 0; i < N; i++){
				read = br.readLine().split(" ");
				for(int j = 0; j < M; j++) arr[i][j] = dic.get(read[0].charAt(j));
			}
		}catch(IOException IOE){}
	}
	
	private void print(){
		StringBuilder sb = new StringBuilder();
		for(int x = M-1; x >= 0; x--){
			for(int y = 0; y < N; y++) sb.append(arr[y][x]);
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
}
