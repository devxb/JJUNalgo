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
	private final static int rem = 1000000007;
	private String[] read;
	
	public void run(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			int T = Integer.parseInt(br.readLine());
			StringBuilder builder = new StringBuilder();
			for(int t = 0; t < T; t++){
				int[] arr = input(br);
				int[] negArr = new int[N];
				for(int i = 0; i < N; i++) negArr[i] = rem;
				for(int i = 0; i < N; i++){
					if(negArr[i] != rem) continue;
					setNegative(arr, negArr, i);
				}
				builder.append(getLargestNum(arr, negArr));
				builder.append("\n");
			}
			System.out.println(builder.toString());
		}catch(IOException IOE){}
	}

	private long getLargestNum(int[] arr, int[] negArr){
		int block = 0;
		int oldestOddCount = -1;
		int num = 0;
		int maximum = 0;
		for(int i = 0; i < N; i++){
			if(arr[i] == 0){
				if(block == 1) maximum = Math.max(num - oldestOddCount, maximum);
				block = 0;
				num = 0;
				oldestOddCount = -1;
				continue;
			}
			num += ((arr[i] % 2 == 0) ? 1 : 0);
			block += ((arr[i] < 0) ? 1 : 0);
			if(block == 2) block = 0;
			if(block == 0) maximum = Math.max(num, maximum);
			if(i < N-1 && block == 1 && oldestOddCount == -1) oldestOddCount = num;
		}
		if(arr[N-1] != 0 && block == 1 && oldestOddCount != -1) maximum = Math.max(num - oldestOddCount, maximum);
		long ret = 1L;
		if(maximum == 0){
			int cntNegativeOne = 0;
			for(int i = 0; i < N; i++){
				if(arr[i] == 1) return 1;
				if(arr[i] == 0) cntNegativeOne = 0;
				if(arr[i] == -1) cntNegativeOne++;
				if(cntNegativeOne == 2) return 1;
			}
			return 0;
		}
		while(maximum > 0){
			ret = ((ret % rem) * (2 % rem)) % rem;
			maximum -= 1;
		}
		return ret % rem;
	}
	
	private void setNegative(int[] arr, int[] target, int idx){
		if(idx == N || arr[idx] == 0) return;
		if(idx == 0) target[idx] = (arr[idx] < 0) ? 1 : 0;
		else target[idx] = target[idx-1] + ((arr[idx] < 0) ? 1 : 0);
		setNegative(arr, target, idx+1);
		return;
	}
	
	private int[] input(BufferedReader br) throws IOException{
		int[] ret = null;
		N = Integer.parseInt(br.readLine());
		ret = new int[N];
		read = br.readLine().split(" ");
		for(int idx = 0; idx < N; idx++) ret[idx] = Integer.parseInt(read[idx]);
		return ret;
	}
	
}
