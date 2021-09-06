import java.util.List;
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
	
	private int N, M;
	private int[] arr;
	private int[] dp;
	
	public void run(){
		input();
		System.out.println(getRiderNum());
	}
	
	private long getRiderNum(){
		if(M >= N) return N;
		long left = 0;
		long right = (N*30L);
		for(int i = 0; i < 64; i++){
			long mid = (left + right) / 2;
			if(getPeople(mid) < N && N <= getPeople(mid+1)) return findRider(mid);
			else if(getPeople(mid) < N) left = mid;
			else if(getPeople(mid) >= N) right = mid;
		}
		return findRider((left+right)/2);
	}
	
	private long findRider(long time){
		long startNum = getPeople(time);
		for(int T = 0; T < 2; T++){
			for(int idx = 1; idx <= M; idx++){
				if((time+T) % arr[idx] == 0) startNum++;
				if(startNum == N) return idx;
			}
		}
		return 0;
	}
	
	private long getPeople(long time){
		long ans = 0;
		boolean[] check = new boolean[35];
		for(int i = 1; i <= M; i++){
			if(check[arr[i]]) continue;
			check[arr[i]] = true;
			if(time % arr[i] == 0) ans += dp[arr[i]] * ((time / arr[i])-1);
			else ans += dp[arr[i]] * (time / arr[i]);
		}	
		return ans + M; // Time : 0 의 M명 더해줌
	}
	
	private void input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			String[] read = br.readLine().split(" ");
			N = Integer.parseInt(read[0]);
			M = Integer.parseInt(read[1]);
			arr = new int[M+1];
			dp = new int[31];
			read = br.readLine().split(" ");
			for(int i = 0; i < M; i++) {
				arr[i+1] = Integer.parseInt(read[i]);
				dp[arr[i+1]]++;
			}
		}catch(IOException IOE){}
	}
	
}
