// 18808 스티커 붙이기
import java.io.*;
import java.util.*;

public class Main{
	
	private int notebookWidth, notebookHeight;
	private int stickerCount;
	private int[][] notebook;
	private List<int[][]> stickers;
	
	public static void main(String[] args){
		(new Main()).solve();
	}
	
	private void solve(){
		this.input();
		this.stickStickers(0);
		int sticked = this.countSticked();
		System.out.println(sticked);
	}
	
	private void input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			this.inputNotebookAndSticker(br);
			this.inputStickers(br);
		}catch(IOException IOE){}
	}
	
	private void inputNotebookAndSticker(BufferedReader br) throws IOException{
		String[] read = br.readLine().split(" ");
		this.notebookHeight = Integer.parseInt(read[0]);
		this.notebookWidth = Integer.parseInt(read[1]);
		this.stickerCount = Integer.parseInt(read[2]);
		this.notebook = new int[notebookHeight][notebookWidth];
	}
	
	private void inputStickers(BufferedReader br) throws IOException{
		this.stickers = new ArrayList<int[][]>();
		for(int i = 0; i < this.stickerCount; i++){
			String[] read = br.readLine().split(" ");
			int stickerHeight = Integer.parseInt(read[0]);
			int stickerWidth = Integer.parseInt(read[1]);
			int[][] sticker = new int[stickerHeight][stickerWidth];
			for(int y = 0; y < stickerHeight; y++){
				read = br.readLine().split(" ");
				for(int x = 0; x < stickerWidth; x++) sticker[y][x] = Integer.parseInt(read[x]);
			}
			this.stickers.add(sticker);
		}
	}
	
	private void stickStickers(int idx){
		if(idx == this.stickers.size()) return;
		int[][] sticker = this.stickers.get(idx);
		boolean sticked = false;
		for(int t = 0; t < 4; t++){
			if(sticked) break;
			if(t > 0) sticker = this.turnSticker(sticker);
			for(int y = 0; y <= this.notebook.length - sticker.length; y++){
				if(sticked) break;
				for(int x = 0; x <= this.notebook[y].length - sticker[0].length; x++){
					sticked = this.stick(y, x, sticker);
					if(sticked) break;
				}
			}
		}
		this.stickStickers(idx+1);
	}
	
	private int[][] turnSticker(int[][] sticker){
		int[][] turnedSticked = new int[sticker[0].length][sticker.length];
		for(int y = 0; y < turnedSticked.length; y++)
			for(int x = 0; x < turnedSticked[y].length; x++) turnedSticked[y][x] = sticker[sticker.length-1-x][y];
		return turnedSticked;
	}
	
	private boolean stick(int startY, int startX, int[][] sticker){
		if(!this.isStickable(startY, startX, sticker)) return false;
		for(int y = startY; y < startY + sticker.length; y++)
			for(int x = startX; x < startX + sticker[0].length; x++)
				this.notebook[y][x] = (sticker[y-startY][x-startX] == 1) ? 1 : this.notebook[y][x];
		return true;
	}
	
	private boolean isStickable(int startY, int startX, int[][] sticker){
		for(int y = startY; y < startY + sticker.length; y++)
			for(int x = startX; x < startX + sticker[0].length; x++)
				if(this.notebook[y][x] == 1 && sticker[y-startY][x-startX] == 1) return false;
		return true;
	}
	
	private int countSticked(){
		int sticked = 0;
		for(int[] row : this.notebook)
			for(int element : row) if(element == 1) sticked++;
		return sticked;
	}
	
}
