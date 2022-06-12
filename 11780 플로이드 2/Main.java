// 11780 플로이드 2
import java.io.*;
import static java.lang.Math.*;

public class Main{
	
	private static final int INF = 987654321;
	private int city, bus;
	private int[][] costs, routes;
	
	public static void main(String[] args){
		(new Main()).solve();
	}
	
	private void solve(){
		this.input();
		this.setMinimumCosts();
		StringBuilder resultPrinter = this.buildResultPrinter(new StringBuilder());
		System.out.print(resultPrinter.toString());
	}
	
	private void input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			this.inputCosts(br);
		}catch(IOException IOE){}
	}
	
	private void inputCosts(BufferedReader br) throws IOException{
		this.city = Integer.parseInt(br.readLine());
		this.bus = Integer.parseInt(br.readLine());
		this.costs = this.initCost(new int[city+1][city+1]);
		for(int i = 0; i < bus; i++){
			String[] read = br.readLine().split(" ");
			int from = Integer.parseInt(read[0]);
			int to = Integer.parseInt(read[1]);
			int cost = Integer.parseInt(read[2]);
			this.costs[from][to] = min(this.costs[from][to], cost);
		}
	}

	private int[][] initCost(int[][] costs){
		for(int from = 0; from < costs.length; from++)
			for(int to = 0; to < costs[from].length; to++){
				if(from == to) continue;
				costs[from][to] = this.INF;
			}
		return costs;
	}
	
	private void setMinimumCosts(){
		this.routes = new int[this.city+1][this.city+1];
		for(int mid = 1; mid <= this.city; mid++){
			for(int from = 1; from <= this.city; from++){
				for(int to = 1; to <= this.city; to++){
					if(this.costs[from][to] <= this.costs[from][mid] + this.costs[mid][to]) continue;
					this.costs[from][to] = this.costs[from][mid] + this.costs[mid][to];
					this.routes[from][to] = mid;
				}
			}
		}
	}
	
	private StringBuilder buildResultPrinter(StringBuilder sb){
		this.buildCostPrinter(sb);
		this.buildRoutePrinter(sb);
		return sb;
	}

	private void buildCostPrinter(StringBuilder sb){
		for(int i = 1; i <= this.city; i++){
			for(int j = 1; j <= this.city; j++) sb.append(this.costs[i][j] == this.INF ? 0 : this.costs[i][j]).append(" ");
			sb.append("\n");
		}
	}
	
	private void buildRoutePrinter(StringBuilder sb){
		StringBuilder tempBuilder = new StringBuilder();
		for(int i = 1; i <= this.city; i++){
			for(int j = 1; j <= this.city; j++){
				if(this.costs[i][j] == 0 || this.costs[i][j] == this.INF) sb.append(0);
				else {
					tempBuilder.setLength(0);
					int routeCount = this.buildRoutePrinter(i, j, tempBuilder) + 1;
					sb.append(routeCount).append(" ").append(tempBuilder).append(j);
				}
				sb.append("\n");
			}
		}
	}

	private int buildRoutePrinter(int from, int to, StringBuilder sb){
		if(this.routes[from][to] == 0){
			sb.append(from).append(" ");
			return 1;
		}
		return this.buildRoutePrinter(from, this.routes[from][to], sb) + this.buildRoutePrinter(this.routes[from][to], to, sb);
	}
	
}
