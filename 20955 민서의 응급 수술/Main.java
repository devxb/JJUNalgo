import java.io.*;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.*;

public class Main{
	
	private int neurons;
	private int synapses;
	private List<ArrayList<Integer> > graph;
	
	public static void main(String[] args){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			(new Main()).solve(br);
		}catch(IOException IOE){}
	}
	
	private void solve(BufferedReader br) throws IOException{
		this.input(br);
		System.out.println(this.getTreeMakeOpreationCount());
	}
	
	private void input(BufferedReader br) throws IOException{
		String[] NM = br.readLine().split(" ");
		this.neurons = Integer.parseInt(NM[0]);
		this.synapses = Integer.parseInt(NM[1]);
		this.initGraph(neurons);
		for(int i = 0; i < synapses; i++){
			String[] fromTo = br.readLine().split(" ");
			this.graph.get(Integer.parseInt(fromTo[0])).add(Integer.parseInt(fromTo[1]));
			this.graph.get(Integer.parseInt(fromTo[1])).add(Integer.parseInt(fromTo[0]));
		}
	}
	
	private void initGraph(int neurons){
		this.graph = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i <= neurons; i++) this.graph.add(new ArrayList<Integer>());
	}
	
	private int getTreeMakeOpreationCount(){
		boolean[] isUnion = new boolean[this.neurons+1];
		Set<PathKey> isPassed = new HashSet<PathKey>();
		int operationCount = 0;
		int remainSynapseCount = 0;
		int[] result = new int[2];
		for(int i = 1; i <= this.neurons; i++){
			if(isUnion[i]) continue;
			result[0] = result[1] = 0;
			this.countNeuronAndSynapse(i, isUnion, isPassed, result);
			operationCount += this.deleteSynapseCount(result);
			remainSynapseCount += (result[0]-1);
		}
		return operationCount + this.connectSynapseCount(remainSynapseCount);
	}
	
	private void countNeuronAndSynapse(int from, boolean[] isUnion, Set<PathKey> isPassed, int[] result){
		if(isUnion[from]) return; 
		result[0]++;
		isUnion[from] = true;
		for(int i = 0; i < this.graph.get(from).size(); i++){
			int to = this.graph.get(from).get(i);
			if(isPassed.contains(PathKey.instance(from, to))) continue;
			isPassed.add(PathKey.instance(from, to));
			isPassed.add(PathKey.instance(to, from));
			result[1]++;
			this.countNeuronAndSynapse(to, isUnion, isPassed, result);
		}
	}
	
	private int deleteSynapseCount(int[] result){
		return Math.max(0, result[1] - (result[0]-1));
	}
	
	private int connectSynapseCount(int remainSynapseCount){
		return Math.max(0, (this.neurons-1) - remainSynapseCount);
	}
	
	private static class PathKey{
		
		int from, to;
		
		public static PathKey instance(int from, int to){
			return new PathKey(from, to);
		}
		
		private PathKey(int from, int to){
			this.from = from;
			this.to = to;
		}
		
		@Override
		public int hashCode(){
			return Objects.hash(from, to);
		}
		
		@Override
		public boolean equals(Object obj){
			if(!(obj instanceof PathKey)) return false;
			PathKey pathKey = (PathKey)obj;
			return this.from == pathKey.from && this.to == pathKey.to;
		}
		
	}
	
}
