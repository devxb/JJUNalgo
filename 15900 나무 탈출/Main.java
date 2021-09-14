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
	
	private int N;
	private int dis;
	private boolean[] check;
	private ArrayList<ArrayList<Integer>> tree = new ArrayList<ArrayList<Integer>>();
	
	public void run(){
		input();
		setNodeDistance(1, 0);
		System.out.println(doGame());
	}
	
	private void input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			String[] read = br.readLine().split(" ");
			N = Integer.parseInt(read[0]);
			check = new boolean[N+1];
			for(int i = 0; i <= N; i++) tree.add(new ArrayList<Integer>());
			for(int i = 0; i < N-1; i++){
				read = br.readLine().split(" ");
				tree.get(Integer.parseInt(read[0])).add(Integer.parseInt(read[1]));
				tree.get(Integer.parseInt(read[1])).add(Integer.parseInt(read[0]));
			}
		}catch(IOException IOE){}
	}
	
	private void setNodeDistance(int idx, int distance){
		check[idx] = true;
		if(tree.get(idx).size() == 1 && idx != 1) dis += distance;
		for(int i = 0; i < tree.get(idx).size(); i++){
			if(check[tree.get(idx).get(i)]) continue;
			setNodeDistance(tree.get(idx).get(i), distance+1);
		}
	}
	
	private String doGame(){
		if(this.dis % 2 == 0) return "No";
		return "Yes";
	}
	
}
