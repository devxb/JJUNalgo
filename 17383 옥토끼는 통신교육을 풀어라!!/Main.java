import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;

public class Main{
    
    public static void main(String[] args){
        Solve solve = new Solve();
		solve.run();
    }
    
}

class Solve{
	
	private int N, maxValue;
	private int[] arr;
	
	public void run(){
		input();
		System.out.println(getMinimumTaskTime());
	}
	
	private void input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			String[] read = br.readLine().split(" ");
			this.N = Integer.parseInt(read[0]);
			read = br.readLine().split(" ");
			arr = new int[N];
			for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(read[i]);
			Arrays.sort(arr);
			maxValue = arr[N-1];
		}catch(IOException IOE){}
	}
	
	// logic
	
	private int getMinimumTaskTime(){
		int min = 1, max = maxValue;
		int ret = max;
		while(min <= max){
			int taskTime = (min + max) / 2;
			if(doTask(taskTime)){
				max = taskTime-1;
				ret = taskTime;
			}
			else min = taskTime+1;
		}
		return ret;
	}
    
	private boolean doTask(int taskTime){
		if(arr[0] > taskTime) return false;
		int prime = 0;
		int remain = 1;
		for(int i = 1; i < N; i++){
			if(arr[i] <= taskTime*1){
				prime++;
				continue;
			}
			int space = arr[i] / taskTime + (arr[i] % taskTime > 0 ? 1 : 0) - 2;
			if(space > prime) return false;
			prime -= space;
		}
		return true;
	}
	
}
