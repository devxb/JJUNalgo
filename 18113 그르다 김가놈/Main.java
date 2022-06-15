// 18113 그르다 김가놈
import java.io.*;
import java.util.*;

public class Main{
	
	private int kimbapCount, tail, needKimbapPiece;
	private List<Integer> kimbaps;
	
	public static void main(String[] args){
		(new Main()).solve();
	}
	
	private void solve(){
		this.input();
		int kimbapPieceLength = this.getKimbapPieceLength();
		System.out.println(kimbapPieceLength);
	}
	
	private void input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			this.inputKimbaps(br);
		}catch(IOException IOE){}
	}
	
	private void inputKimbaps(BufferedReader br) throws IOException{
		String[] read = br.readLine().split(" ");
		this.kimbapCount = Integer.parseInt(read[0]);
		this.tail = Integer.parseInt(read[1]);
		this.needKimbapPiece = Integer.parseInt(read[2]);
		this.kimbaps = new ArrayList<Integer>();
		for(int i = 0; i < kimbapCount; i++){
			int kimbap = Integer.parseInt(br.readLine()) - (tail * 2);
			if(kimbap < 0) kimbap += tail;
			if(kimbap <= 0) continue;
			this.kimbaps.add(kimbap);
		}
	}
	
	private int getKimbapPieceLength(){
		int left = 0, right = 2000000000;
		for(int i = 0; i < 32; i++){
			int mid = (left + right) / 2;
			if(this.getKimbapPiece(mid) >= this.needKimbapPiece) left = mid;
			else right = mid;
		}
		return left == 0 ? -1 : left;
	}
	
	private int getKimbapPiece(int cutLength){
		if(cutLength == 0) return 0;
		int piece = 0;
		for(int kimbap : this.kimbaps) piece += kimbap / cutLength;
		return piece;
	}
	
}
