import java.io.*;
import java.util.*;

public class Main{
	
	public static void main(String[] args){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			(new Main()).solve(br);
		}catch(IOException IOE){}
	}
	
	private void solve(BufferedReader br) throws IOException{
		int testCount = Integer.parseInt(br.readLine());
		StringBuilder answer = new StringBuilder();
		StringBuilder inputString = new StringBuilder();
		for(int test = 0; test < testCount; test++){
			int elementCount = Integer.parseInt(br.readLine());
			String[] sequence = this.inputSequence(elementCount, inputString, br).split(" ");
			LinkedList<Integer> middleCandidates = new LinkedList<Integer>();
			int printed = 0;
			answer.append(this.getMiddleCount(sequence.length)).append("\n");
			for(int i = 0; i < sequence.length; i++){
				int element = Integer.parseInt(sequence[i]);
				middleCandidates.add(element);
				if(this.isOddInput(i)){
					int mid = this.getMiddle(middleCandidates);
					answer.append(mid).append(" ");
					printed++;
					if(i+2 < sequence.length && this.isMaximumPrinted(printed)) answer.append("\n"); 
				}
			}
			answer.append("\n");
		}
		System.out.println(answer.toString());
	}
	
	private String inputSequence(int elementCount, StringBuilder inputString, BufferedReader br) throws IOException{
		inputString.setLength(0);
		for(int i = 0; i < elementCount / 10; i++) inputString.append(br.readLine()).append(" ");
		if(elementCount % 10 > 0) inputString.append(br.readLine());
		return inputString.toString();
	}
	
	private int getMiddleCount(int length){
		int ans = length / 2;
		if(length % 2 == 1) ans++;
		return ans;
	}
	
	private boolean isOddInput(int i){
		return i % 2 == 0;
	}
	
	private int getMiddle(LinkedList<Integer> candidates){
		Collections.sort(candidates);
		int mid = 0;
		for(Integer candidate : candidates){
			if(candidates.size()/2 == mid) return candidate;
			mid++;
		}
		return -1;
	}
	
	private boolean isMaximumPrinted(int printed){
		return ((printed > 0) && (printed % 10 == 0));
	}
	
	private void filterNoneCandidates(LinkedList<Integer> candidates){
		while(candidates.size() > 3){
			candidates.pollFirst();
			candidates.pollLast();
		}
	}
	
}
