import java.io.*;
import static java.lang.Math.max;

public class Main{
	
	public static void main(String[] args){
		(new Main()).solve();
	}
	
	private void solve(){
		int[][] trees = this.inputTrees();
		int fruit = this.harvest(trees);
		System.out.println(fruit);
	}
	
	private int[][] inputTrees(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			return this.input(br);
		}catch(IOException IOE){}
		return null;
	}
	
	private int[][] input(BufferedReader br) throws IOException{
		int width = Integer.parseInt(br.readLine());
		int[][] trees = new int[width][width];
		for(int y = 0; y < width; y++){
			String[] line = br.readLine().split(" ");
			for(int x = 0; x < width; x++){
				if(y == 0) trees[y][x] = Integer.parseInt(line[x]);
				else trees[y][x] = Integer.parseInt(line[x]) + trees[y-1][x];
			}
		}
		return trees;
	}
	
	private int harvest(int[][] trees){
		int width = trees.length, fruit = -987654321;
		for(int r = 0; r < width; r++)
			for(int y = 0; y < width-r; y++)
				for(int x = 0; x < width-r; x++)
					fruit = max(fruit, this.getHarvestableFruits(y, x, r, trees));
		return fruit;
	}
	
	private int getHarvestableFruits(int y, int x, int range, int[][] trees){
		int fruits = 0;
		for(int nowX = x; nowX <= x+range; nowX++){
			if(y > 0) fruits += trees[y+range][nowX] - trees[y-1][nowX];
			else fruits += trees[y+range][nowX];
		}
		return fruits;
	}
	
}
