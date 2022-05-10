import java.io.*;
import java.util.*;

public class Main{
	
	private int N, M;
	
	public static void main(String[] args){
		Main main = new Main();
		main.run();
	}
	
	private void run(){
		int stringCounts = this.countStringsInSet();
		System.out.println(stringCounts);
	}
	
	private int countStringsInSet(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			Set<String> stringSet = this.makeStringSet(br);
			return this.countStringsInSet(br, stringSet);
		}catch(IOException IOE){}
		return 0;
	}
	
	private Set<String> makeStringSet(BufferedReader br) throws IOException{
		String[] NM = br.readLine().split(" ");
		this.N = Integer.parseInt(NM[0]);
		this.M = Integer.parseInt(NM[1]);
		Set<String> stringSet = new HashSet<String>();
		for(int i = 0; i < N; i++) stringSet.add(br.readLine());
		return stringSet;
	}
	
	private int countStringsInSet(BufferedReader br, Set<String> stringSet) throws IOException{
		int count = 0;
		for(int i = 0; i < this.M; i++) if(stringSet.contains(br.readLine())) count++;
		return count;
	}
	
}
