import java.util.ArrayList;
import java.util.Collections;
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
	
	private static class Conference implements Comparable<Conference>{
		
		private int from, to;
		
		public Conference(int from, int to){
			this.from = from;
			this.to = to;
		}
		
		@Override
		public int compareTo(Conference c){
			if(this.to > c.to) return 1;
			if(this.to < c.to) return -1;
			if(this.to == c.to){
				if(this.from > c.from) return 1;
				if(this.from < c.from) return -1;
			}
			return 0;
		}
		
	}
	
	private int N;
	private ArrayList<Conference> list = new ArrayList<Conference>();
	
	public void run(){
		input();
		System.out.println(doConference());
	}
	
	private int doConference(){
		int ret = 0;
		int last = 0;
		for(int i = 0; i < list.size(); i++){
			if(last <= list.get(i).from){
				ret++;
				last = list.get(i).to;
			}
		}
		return ret;
	}
	
	private void input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			N = Integer.parseInt(br.readLine());
			String[] read = null;
			for(int i = 0; i < N; i++){
				read = br.readLine().split(" ");
				list.add(new Conference(Integer.parseInt(read[0]), Integer.parseInt(read[1])));
			}
			Collections.sort(list);
		}catch(IOException IOE){}
	}
	
}
