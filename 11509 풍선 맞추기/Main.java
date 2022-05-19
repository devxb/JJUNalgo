import java.io.*;

public class Main{
	
	public static void main(String[] args){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			(new Main()).solve(br);	
		}catch(IOException IOE){}
	}
	
	private void solve(BufferedReader br) throws IOException{
		int[] balloons = this.input(br);
		int neededArrows = this.countNeededArrows(balloons);
		System.out.println(neededArrows);
	}
	
	private int[] input(BufferedReader br) throws IOException{
		br.readLine();
		String[] line = br.readLine().split(" ");
		int[] balloons = new int[line.length];
		for(int i = 0; i < line.length; i++) balloons[i] = Integer.parseInt(line[i]);
		return balloons;
	}
	
	private int countNeededArrows(int[] balloons){
		int[] arrows = new int[this.getBalloonsMaximumHeights(balloons)+1];
		int neededArrows = 0;
		for(int balloon : balloons){
			if(this.isAlreadyThrowArrow(balloon, arrows)) this.downArrow(balloon, arrows);
			else{
				arrows[balloon-1]++;
				neededArrows++;
			}
		}
		return neededArrows;
	}
	
	private int getBalloonsMaximumHeights(int[] balloons){
		int maximumHeight = 0;
		for(int balloon : balloons) maximumHeight = Math.max(balloon, maximumHeight);
		return maximumHeight;
	}
	
	private boolean isAlreadyThrowArrow(int balloon, int[] arrows){
		return arrows[balloon] > 0;
	}
	
	private void downArrow(int balloon, int[] arrows){
		arrows[balloon]--;
		if(balloon == 0) return;
		arrows[balloon-1]++;
	}
	
}
