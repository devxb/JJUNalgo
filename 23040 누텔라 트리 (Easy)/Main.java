import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static java.lang.Math.max;

public class Main{
    
	private int num;
	private int N;
	private char[] leafColor;
	private long[] nutella;
	private Stack<Integer> nutellaStack = new Stack<Integer>();
	private int[] visit;
	private List<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
	
    public static void main(String[] args){
		new Main().run();
    }
	
	public Main run(){
		input();
		if(!existBlack()) System.out.println(0);
		else System.out.println(getNutellaTreeCount());
		return this;
	}
    
	private void input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			this.N = Integer.parseInt(br.readLine());
			for(int i = 0; i <= N; i++) list.add(new ArrayList<Integer>());
			visit = new int[N+1];
			nutella = new long[N+1];
			String[] read = null;
			for(int i = 1; i < N; i++){
				read = br.readLine().split(" ");
				int from = Integer.parseInt(read[0]);
				int to = Integer.parseInt(read[1]);
				list.get(from).add(to);
				list.get(to).add(from);
			}
			String color = br.readLine();
			leafColor = new char[N+1];
			for(int i = 1; i <= color.length(); i++) leafColor[i] = color.charAt(i-1);
		}catch(IOException IOE){}
	}
	
	private long getNutellaTreeCount(){
		long ret = 0L;
		for(int i = 1; i <= N; i++){
			if(leafColor[i] == 'R') continue;
			ret += enterNutella(i, i);;
		}
		return ret;
	}
	
	private long enterNutella(int idx, int v){
		if(nutella[idx] > 0L && leafColor[idx] != 'B') return nutella[idx];
		if(leafColor[idx] != 'B') nutellaStack.add(idx);
		visit[idx] = v;
		List<Integer> l = list.get(idx);
		long ret = 0L;
		for(int i = 0; i < l.size(); i++){
			int to = l.get(i);
			if(visit[to] == v || leafColor[to] == 'B') continue;
			long cnt = enterNutella(to, v);
			ret += cnt + 1L;
			if(leafColor[idx] == 'B') 
				while(!nutellaStack.isEmpty()) 
					nutella[nutellaStack.pop()] = cnt;
		}
		return ret;
	}
	
	private boolean existBlack(){
		for(int i = 1; i <= N; i++) 
			if(leafColor[i] == 'B') return true;
		return false;
	}
    
}
