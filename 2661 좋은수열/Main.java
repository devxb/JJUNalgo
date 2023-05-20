import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	private int N;
	private List<Character> resultList = null;

	public static void main(String[] args) {
		(new Main()).solve();
	}

	private void solve() {
		input();
		findMinimumGoodNum(new ArrayList<>());
		for(Character character : resultList) {
			System.out.print(character);
		}
	}

	private void input() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			N = Integer.parseInt(br.readLine());
		} catch(IOException ignored) {
		}
	}

	private void findMinimumGoodNum(List<Character> numList) {
		if(resultList != null || isNotGoodNum(numList)) {
			return;
		}
		if(numList.size() == N) {
			resultList = new ArrayList<>(numList);
			return;
		}
		for(char i = '1'; i <= '3'; i++) {
			numList.add(i);
			findMinimumGoodNum(numList);
			numList.remove(numList.size() - 1);
		}
	}

	private boolean isNotGoodNum(List<Character> numList) {
		for(int i = 0; i < numList.size(); i++) {
			for(int l = i; l < numList.size(); l++) {
				int left = l-i;
				if(l + 1 + left >= numList.size()){
					continue;
				}
				int tempI = i;
				boolean isGoodNum = false;
				for(int c = l+1; c <= l+1+left; c++){
					if(!numList.get(tempI).equals(numList.get(c))){
						isGoodNum = true;
						break;
					}
					tempI++;
				}
				if(!isGoodNum){
					return true;
				}
			}
		}
		return false;
	}

}
