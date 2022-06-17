// 15823 카드 팩 구매하기
import java.io.*;
import static java.lang.Math.max;

public class Main{
	
	private int packs;
	private int[] cards;
	
	public static void main(String[] args){
		(new Main()).solve();
	}
	
	private void solve(){
		this.input();
		int cardsInPack = this.getCardsInPack();
		System.out.println(cardsInPack);
	}
	
	private void input(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			this.inputCards(br);
		}catch(IOException IOE){}
	}
	
	private void inputCards(BufferedReader br) throws IOException{
		String[] read = br.readLine().split(" ");
		this.cards = new int[Integer.parseInt(read[0])];
		this.packs = Integer.parseInt(read[1]);
		read = br.readLine().split(" ");
		for(int i = 0; i < cards.length; i++) cards[i] = Integer.parseInt(read[i]);
	}
	
	private int getCardsInPack(){
		int left = 1, right = this.cards.length / this.packs, answer = 1;
		for(int i = 0; i < 17 && (left <= right); i++){
			int mid = (left + right) / 2;
			if(this.packable(mid)) {
				left = mid + 1;
				answer = max(answer, mid);
			}
			else right = mid - 1;
		}
		return answer;
	}
	
	private boolean packable(int cardsInPack){
		int madePack = 0, trig = 0;
		int[] picked = new int[500001];
		for(int start = 0; start < this.cards.length; start++){
			int left = start, right = start;
			trig++;
			picked[this.cards[left]] = trig;
			while(true){
				if(right - left + 1 == cardsInPack){
					madePack++;
					break;
				}
				if(left > right || right+1 == this.cards.length) break;
				if(picked[this.cards[right+1]] == trig){
					picked[this.cards[left]] = 0;
					left++;
					continue;
				}
				picked[this.cards[right+1]] = trig;
				right++;
			}
			start = right;
		}
		return madePack >= this.packs;
	}
	
}
