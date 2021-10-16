import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Math.min;
import static java.lang.Math.abs;

public class Main{
    
    public static void main(String[] args){
        Solve solve = new Solve();
		solve.run();
    }
    
}

class Solve{
	
	private int N;
	private int[] peo;
	private int totalNum;
	private int ans = 987654321;
	private ArrayList<ArrayList<Integer> > node = new ArrayList<ArrayList<Integer> >(); 
	
	public void run(){
		input();
		ans = getMinimumDif(1, 2*(1 << (N+2)), 0);
		if(ans == 987654321) System.out.println(-1);
		else System.out.println(ans);
	}
	
	private void input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			String[] read = br.readLine().split(" ");
			N = Integer.parseInt(read[0]);
			peo = new int[N+1];
			// init
			for(int i = 0; i <= N+1; i++){
				node.add(new ArrayList<Integer>());
				node.get(i).add(i);
			}
			
			read = br.readLine().split(" ");
			for(int i = 1; i <= N; i++) {
				peo[i] = Integer.parseInt(read[i-1]);
				totalNum += peo[i];
			}
			
			for(int i = 1; i <= N; i++){
				read = br.readLine().split(" ");
				int num = Integer.parseInt(read[0]);
				for(int j = 1; j <= num; j++){
					node.get(i).add(Integer.parseInt(read[j]));
					node.get(Integer.parseInt(read[j])).add(i);
				}
			}
		}catch(IOException IOE){}
	}
	
	private int getMinimumDif(int idx, int field, int num){
		if(idx > N && checkConnection(field)) return abs((totalNum - num) - num);
		if(idx > N) return 987654321;
		return Math.min(
			getMinimumDif(idx+1, field | (1 << idx), num + peo[idx])
			, getMinimumDif(idx+1, field, num)
		);
	}
	
	private boolean checkConnection(int field){
		int[] visit = new int[N+2];
		boolean visitA = false, visitB = false;
		for(int i = 0; i <= N; i++) visit[i] = -1;
		for(int i = 1; i <= N; i++){
			if((field & (1 << i)) > 0){
				markVisit(i, 1, visit, field);
				visitA = true;
				break;
			}
		}
		for(int i = 1; i <= N; i++){
			if((field & (1 << i)) == 0){
				markVisit(i, 0, visit, field);
				visitB = true;
				break;
			}
		}
		if(!visitA || !visitB) return false;
		for(int j = 1; j <= N; j++) if(visit[j] == -1) return false;
		return true;
	}
	
	private void markVisit(int idx, int num, int[] visit, int field){
		visit[idx] = num;
		for(int i = 1; i < node.get(idx).size(); i++){
			int nextIdx = node.get(idx).get(i);
			if(visit[nextIdx] != -1) continue;
			if(num == 0 && (field & (1 << nextIdx)) > 0) continue;
			if(num > 0 && (field & (1 << nextIdx)) == 0) continue;
			markVisit(nextIdx, num, visit, field);
		}
	}
	
}
