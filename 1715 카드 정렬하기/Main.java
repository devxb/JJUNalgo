import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.PriorityQueue;

public class Main{
    
    public static void main(String[] args){
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
	
	private int N;
	private PriorityQueue<Long> pq = new PriorityQueue<Long>();
	
	public void run(){
		input();
		System.out.println(getCards());
	}
	
	private void input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			N = Integer.parseInt(br.readLine());
			for(int i = 0; i < N; i++) pq.add(Long.valueOf(br.readLine()));
		}catch(IOException IOE){}
	}
	
	private long getCards(){
		long ret = 0L;
		while(pq.size() > 1){
			long first = pq.poll();
			long last = pq.poll();
			ret += first+last;
			pq.add(first+last);
		}
		return ret;
	}
	
}
