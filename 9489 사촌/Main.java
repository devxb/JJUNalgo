import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import static java.lang.Math.max;

public class Main{
    
	private static class Node{
		private int idx, par;
		
		public Node(int idx, int par){
			this.idx = idx;
			this.par = par;
		}
	}
	
	private Node[] tree;
	private String[] read;
	private int[] arr;
	private int n, k, target;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args){
		Main main = new Main();
		main.run();
	}
	
	public void run(){
		StringBuilder sb = new StringBuilder();
		while(input()) sb.append(getSibiling()).append("\n");	
		System.out.println(sb.toString());
	}
	
	private boolean input(){
		try{
			read = br.readLine().split(" ");
			this.n = Integer.parseInt(read[0]);
			this.k = Integer.parseInt(read[1]);
			arr = new int[n+1];
			tree = new Node[n];
			if(n == 0 && k == 0) return false;
			read = br.readLine().split(" ");
			for(int i = 0; i < n; i++){
				arr[i] = Integer.parseInt(read[i]);
				if(arr[i] == k) this.target = i;
			}
			tree[0] = new Node(arr[0], -1);
			generateTree(0, 1);
		}catch(IOException ioe){ 
			ioe.printStackTrace();
			return false;
		}
		return true;
	}
	
	private void generateTree(int parIdx, int idx){
		if(idx >= n) return;
		int node = arr[idx];
		int prevNode = arr[idx-1];
		if(prevNode+1 != node && idx > 1) parIdx++;
		tree[idx] = new Node(node, parIdx);
		generateTree(parIdx, idx+1);
	}
	
	private int getSibiling(){
		int ret = 0;
		for(int idx = 1; idx < n; idx++)
			if(arr[tree[target].par] != arr[tree[idx].par] && tree[tree[target].par].par == tree[tree[idx].par].par) ret++;
		
		return ret;
	}
	
}
