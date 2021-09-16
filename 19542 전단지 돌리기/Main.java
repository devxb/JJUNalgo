import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main{
    
    public static void main(String[] args){
        Solve solve = new Solve();
		solve.run();
    }
    
}

class Solve{
	
	private int N, S, D;
	private boolean[] check;
	private ArrayList<ArrayList<Integer>> tree = new ArrayList<ArrayList<Integer>>();
	
	public void run(){
		input();
		System.out.println(Math.max(getDistance(S)*2, 0));
	}
	
	private void input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			String[] read = br.readLine().split(" ");
			N = Integer.parseInt(read[0]);
			S = Integer.parseInt(read[1]);
			D = Integer.parseInt(read[2]);
			check = new boolean[N+1];
			for(int i = 0; i <= N; i++) tree.add(new ArrayList<Integer>());
			for(int i = 1; i < N; i++){
				read = br.readLine().split(" ");
				int from = Integer.parseInt(read[0]);
				int to = Integer.parseInt(read[1]);
				tree.get(from).add(to);
				tree.get(to).add(from);
			}
		}catch(IOException IOE){}
	}
	
	private int getDistance(int idx){
		check[idx] = true;
		if(tree.get(idx).size() == 1 && idx != S) return -1*D;
		int ret = -987654321;
		boolean upper = false;
		for(Integer nextIdx : tree.get(idx)){
			int unWrappedNextIdx = nextIdx;
			if(check[unWrappedNextIdx]) continue;
			int distance = getDistance(unWrappedNextIdx)+1;
			if(distance >= 0){
				if(ret < 0) ret = 0;
				ret += distance;
				upper = true;
			}
			if(distance < 0 && !upper) ret = Math.max(distance, ret);
		}
		return ret;
	}
	
}
