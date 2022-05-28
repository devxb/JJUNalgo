import java.io.*;

public class Main{
	
	private int[][] snail;
	private int y, x;
	
	public static void main(String[] args){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			(new Main()).solve(br);
		}catch(IOException IOE){}
	}
	
	private void solve(BufferedReader br) throws IOException{
		int width = Integer.parseInt(br.readLine());
		int findNum = Integer.parseInt(br.readLine());
		this.fill(width);
		StringBuilder sb = new StringBuilder();
		for(int y = 0; y < width; y++){
			for(int x = 0; x < width; x++){ 
				if(snail[y][x] == findNum){
					this.y = y+1;
					this.x = x+1;
				}
				sb.append(snail[y][x]).append(" ");
			}
			sb.append("\n");
		}
		sb.append(this.y).append(" ").append(this.x);
		System.out.println(sb.toString());
	}
	
	private void fill(int snailSize){
		this.snail = new int[snailSize][snailSize];
		for(int y = 0, x = 0; snailSize > 0; y++, x++){
			fillSnail(y, x, snailSize);
			snailSize -= 2;
		}
	}
	
	private void fillSnail(int y, int x, int snailSize){
		int num = snailSize*snailSize;
		for(int i = 0; i < snailSize && num > 0; i++) this.snail[y+i][x] = num--;
		for(int i = 1; i < snailSize && num > 0; i++) this.snail[y+snailSize-1][x+i] = num--;
		for(int i = 1; i < snailSize && num > 0; i++) this.snail[y+(snailSize-1)-i][x+snailSize-1] = num--;
		for(int i = 1; i < snailSize-1 && num > 0; i++) this.snail[y][x+(snailSize-1)-i] = num--;
	}
	
}
