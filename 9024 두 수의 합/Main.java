// 9024 두 수의 합
import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class Main{
	
	private static final int INF = 987654321;
	
	public static void main(String[] args){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			(new Main()).solve(br);
		}catch(IOException IOE){}
	}
	
	private void solve(BufferedReader br) throws IOException{
		int test = Integer.parseInt(br.readLine());
		StringBuilder ans = new StringBuilder();
		for(int t = 0; t < test; t++){
			String[] read = br.readLine().split(" ");
			int targetSum = Integer.parseInt(read[1]);
			int[] nums = new int[Integer.parseInt(read[0])];
			read = br.readLine().split(" ");
			for(int i = 0; i < read.length; i++) nums[i] = Integer.parseInt(read[i]);
			Arrays.sort(nums);
			int maxCloseTargetSum = this.getMaxCloseTargetSum(targetSum, nums);
			int combinationCount = 0;
			if(maxCloseTargetSum != 0) combinationCount = this.getCloseCombinationCount(targetSum + maxCloseTargetSum, nums) + this.getCloseCombinationCount(targetSum - maxCloseTargetSum, nums);
			else combinationCount = this.getCloseCombinationCount(targetSum, nums);
			ans.append(combinationCount).append("\n");
		}
		System.out.println(ans.toString());
	}
	
	private int getMaxCloseTargetSum(int targetSum, int[] nums){
		int maxClose = this.INF, nowClose = 0, left = 0, right = nums.length-1;
		while(left < right){
			nowClose = abs(targetSum - (nums[left] + nums[right]));
			maxClose = min(maxClose, nowClose);
			if(nums[left] + nums[right] == targetSum){
				left++;
				right--;
			}
			else if(nums[left] + nums[right] < targetSum) left++;
			else if(nums[left] + nums[right] > targetSum) right--;
		}
		return maxClose;
	}
	
	private int getCloseCombinationCount(int targetSum, int[] nums){
		int left = 0, right = nums.length-1, combination = 0;
		while(left < right){
			int nowSum = nums[left] + nums[right];
			if(nowSum == targetSum) {
				combination++;
				left++;
				right--;
				continue;
			}
			if(nowSum > targetSum) right--;
			else if(nowSum < targetSum) left++;
		}
		return combination;
	}
	
}
