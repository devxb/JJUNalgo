import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main{
    
    public static void main(String[] args){
        Solve solve = new Solve();
		solve.run();
    }
    
}

class Solve{
	
	private static class BitBerry{
		
		private long bit;
		private long coin;
		private long berry;
		private long bitToCoin;
		private long coinToBit;
		private long berryToCoin;
		private long coinToBerry;
		
		private BitBerry(String[] read){
			this.bit = Long.parseLong(read[0]);
			this.berry = Long.parseLong(read[1]);
			this.bitToCoin = Long.parseLong(read[2]);
			this.coinToBit = Long.parseLong(read[3]);
			this.berryToCoin = Long.parseLong(read[4]);
			this.coinToBerry = Long.parseLong(read[5]);
		}
		
		private void init(){
			this.coin = (berry / berryToCoin) * coinToBerry;
		}
		
		private long getBitCoin(){
			init();
			long given = getMaximumBit();
			while(operBit(given+bitToCoin) >= 0 && (operBitCoin(given) < operBitCoin(given+bitToCoin))) given += bitToCoin;
			while(operBitCoin(given) < operBitCoin(given-bitToCoin)) given -= bitToCoin;
			return operBitCoin(given);
		}
		
		private long getMaximumBit(){
			long left = -1*this.coin, right = this.bit;
			while(left < right){
				long mid = (left + right) / 2;
				if(operCoin(mid) > operBit(mid)) right = mid-1;
				else left = mid+1;
			}
			return Math.max(left, right);
		}
		
        private long operBitCoin(long given){
			return Math.min(operCoin(given), operBit(given));
		}
        
		private long operCoin(long given){
			long coin = ((given / bitToCoin) * coinToBit) + this.coin;
			return coin;
		}
		
		private long operBit(long given){
			long bit = (this.bit - given) + given % bitToCoin;
			return bit;
		}
		
	}
	
	public void run(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			int T = Integer.parseInt(br.readLine());
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < T; i++) sb.append(getBitBerry(br).getBitCoin()).append("\n");
			System.out.println(sb.toString());
		}catch(IOException IOE){}
	}
	
	private BitBerry getBitBerry(BufferedReader br) throws IOException{
		BitBerry bitBerry = new BitBerry(br.readLine().split(" "));
		return bitBerry;
	}
	
}
