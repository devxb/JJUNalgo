import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;

public class Main{
    
    public static void main(String[] args){
        Solve solve = new Solve();
		solve.run();
    }
    
}

class Solve{
	
	private Deque<Character> S = new LinkedList<>();
	private Deque<Character> T = new LinkedList<>();
	
	public void run(){
		input();
		System.out.println(sToT());
	}

	private void input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			String s = br.readLine();
			String t = br.readLine();
			for(int idx = 0; idx < s.length(); idx++) S.add(s.charAt(idx));
			for(int idx = 0; idx < t.length(); idx++) T.add(t.charAt(idx));
		}catch(IOException IOE){}
	}
	
	private int sToT(){
		int flip = 0;
		while(S.size() < T.size()){
			char c = '-';
			if(flip % 2 == 0) c = T.pollLast();
			else c = T.pollFirst();
			flip = (c == 'B') ? flip+1 : flip;
		}
		while(flip % 2 == 0 && !S.isEmpty() && S.peekLast() == T.peekLast()){
			S.pollLast();
			T.pollLast();
		}
		while(flip % 2 == 1 && !S.isEmpty() && S.peekLast() == T.peekFirst()){
			S.pollLast();
			T.pollFirst();
		}
		if(!S.isEmpty()) return 0;
		return 1;
	}
	
}
