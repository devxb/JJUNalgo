// 22341 사각형 면적

import java.io.*;
import java.util.*;

public class Main{
	
	private int height, width;
	private List<Point> cuttingPoints;
	
	public static void main(String[] args){
		(new Main()).solve();
	}
	
	private void solve(){
		this.input();
		this.cut();
		System.out.println(height * width);
	}
	
	private void input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			this.inputCuttingPoints(br);
		}catch(IOException IOE){}
	}
	
	private void inputCuttingPoints(BufferedReader br) throws IOException{
		String[] read = br.readLine().split(" ");
		this.height = this.width = Integer.parseInt(read[0]);
		int pointCnt = Integer.parseInt(read[1]);
		this.cuttingPoints = new ArrayList<Point>(pointCnt);
		for(int i = 0; i < pointCnt; i++){
			read = br.readLine().split(" ");
			cuttingPoints.add(new Point(Integer.parseInt(read[0]), Integer.parseInt(read[1])));
		}
	}
	
	private void cut(){
		for(Point point : this.cuttingPoints) {
			if(this.isOutOfBound(point)) continue;
			int verticalCuttedSize = this.cutVertical(point);
			int horizontalCuttedSize = this.cutHorizontal(point);
			if(verticalCuttedSize > horizontalCuttedSize) this.width = point.x;
			else this.height = point.y;
		}
	}
	
	private boolean isOutOfBound(Point point){
		return point.x >= width || point.y >= height || point.x < 0 || point.y < 0;
	}
	
	private int cutVertical(Point point){
		return this.height * point.x;
	}
	
	private int cutHorizontal(Point point){
		return this.width * point.y;
	}
	
	private static class Point{
		
		public final int y, x;
		
		public Point(int y, int x){
			this.y = y;
			this.x = x;
		}
		
	}
	
}
