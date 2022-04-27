// boj 3028 창영마을

import java.io.*;
import java.util.*;

public class Main{
	
	private List<Shake> shakes;
	
	public static void main(String[] args){
		Main main = new Main();
		main.solve();
	}
	
	public void solve(){
		this.initShakes();
		String shakeSequence = this.input();
		System.out.println(this.shake(1, 0, shakeSequence));
	}
	
	private void initShakes(){
		this.shakes = new ArrayList<Shake>();
		this.shakes.add(new Shake('A', 1, 2));
		this.shakes.add(new Shake('B', 2, 3));
		this.shakes.add(new Shake('C', 1, 3));
	}
	
	private String input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			return br.readLine();
		}catch(IOException IOE){
			IOE.printStackTrace();
		}
		return "";
	}
	
	private int shake(int ballIdx, int shakeIdx, String shakeSequence){
		if(shakeIdx >= shakeSequence.length()) return ballIdx;
		Shake shakeWay = this.getShake(shakeSequence.charAt(shakeIdx));
		return this.shake(shakeWay.shake(ballIdx), shakeIdx+1, shakeSequence);
	}
	
	private Shake getShake(char shakeWay){
		for(Shake shake : shakes) if(shake.isMe(shakeWay)) return shake;
		return null;
	}
	
	private static class Shake{
		private char shakeWay;
		private int from, to;
		
		public Shake(char shakeWay, int from, int to){
			this.shakeWay = shakeWay;
			this.from = from;
			this.to = to;
		}
		
		public int shake(int idx){
			if(idx == this.from) return this.to;
			if(idx == this.to) return this.from;
			return idx;
		}
		
		public boolean isMe(char shakeWay){
			return this.shakeWay == shakeWay;
		}
		
	}
	
}
