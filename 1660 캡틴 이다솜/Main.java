// 1660 캡틴 이다솜
import java.util.*;
import java.io.*;
import static java.lang.Math.min;

public class Main{
	
	private final static int INF = 987654321;
	private int N;
	
	public static void main(String[] args){
		(new Main()).solve();
	}
	
	private void solve(){
		this.input();
		System.out.println(this.countQuad());
	}

	private void input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			this.N = Integer.parseInt(br.readLine());
		}catch(IOException IOE){}
	}
	
	private int countQuad(){
		int ans = this.INF;
		List<Integer> tris = this.initTris();
		int[][] knap = this.initKnap(new int[this.N+1][tris.size()], tris);
		for(int i = 1; i <= this.N; i++){
			int minElement = this.INF;
			for(int j = 0; j < tris.size(); j++){
				int element = tris.get(j);
				if(i < element) knap[i][j] = min(minElement, knap[i][j]);
				else knap[i][j] = min(minElement, knap[element][j] + knap[i-element][j]);
				minElement = min(minElement, knap[i][j]);
			}
			ans = minElement;
		}
		return ans;
	}
	
	private List<Integer> initTris(){
		int inc = 1, triSum = 0;
		List<Integer> tris = new ArrayList<Integer>();
		for(int i = 1; triSum <= this.N; i += inc){
			triSum += i;
			if(triSum > this.N) break;
			tris.add(triSum);
			inc++;
		}
		return tris;
	}
	
	private int[][] initKnap(int[][] knap, List<Integer> triSum){
		for(int i = 1; i < knap.length; i++)
			for(int j = 0; j < knap[0].length; j++) knap[i][j] = this.INF;
		for(int i = 0; i < triSum.size(); i++) knap[triSum.get(i)][i] = 1;
		return knap;
	}
	
}
