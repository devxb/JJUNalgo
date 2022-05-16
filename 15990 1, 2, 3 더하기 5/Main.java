import java.io.*;

public class Main{
	
	private static final long MOD = 1000000009;
	
	public static void main(String[] args){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			(new Main()).solve(br, new StringBuilder());
		}catch(IOException IOE){}
	}
	
	private void solve(BufferedReader br, StringBuilder sb) throws IOException{
		long[][] dp = this.initDp();
		this.fillDp(dp);
		int testCount = Integer.parseInt(br.readLine());
		for(int test = 0; test < testCount; test++){
			int query = Integer.parseInt(br.readLine());
			long result = this.queryWithRemoveDup(query, dp);
			sb.append(result).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	private long[][] initDp(){
		long[][] dp = new long[100001][4];
		dp[1][1] = 1;
		dp[2][2] = 1;
		dp[3][1] = dp[3][2] = dp[3][3] = 1;
		return dp;
	}
	
	private void fillDp(long[][] dp){
		for(int idx = 4; idx <= 100000; idx++){
			dp[idx][1] = ((dp[idx-1][2]) + (dp[idx-1][3])) % MOD;
			dp[idx][2] = ((dp[idx-2][1]) + (dp[idx-2][3])) % MOD;
			dp[idx][3] = ((dp[idx-3][1]) + (dp[idx-3][2])) % MOD;
		}
	}
	
	private long queryWithRemoveDup(int idx, long[][] data){
		return ((data[idx][1]) + (data[idx][2]) + (data[idx][3])) % MOD;
	}
	
}
