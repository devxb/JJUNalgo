// 2023 신기한 소수
import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class Main{
	
	private int digit;
	
	public static void main(String[] args){
		(new Main()).solve();
	}
	
	private void solve(){
		this.inputDigit();
		Queue<Integer> interestingPrimes = this.findInterestingPrimes();
		StringBuilder interestingPrimePrinter = this.buildInterestingPrimePrinter(interestingPrimes, new StringBuilder());
		System.out.print(interestingPrimePrinter.toString());
	}
	
	private void inputDigit(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			this.digit = Integer.parseInt(br.readLine());
		}catch(IOException IOE){}
	}
	
	private Queue<Integer> findInterestingPrimes(){
		Queue<Integer> interestingPrimes = this.initInterestingPrimes(new LinkedList<Integer>());
		for(int i = 1; i < this.digit; i++){
			int size = interestingPrimes.size();
			for(int j = 0; j < size; j++){
				int nowPrime = interestingPrimes.poll();
				for(int k = 1; k <= 9; k++){
					int nextPrime = (nowPrime*10)+k;
					if(this.isPrime(nextPrime)) interestingPrimes.add(nextPrime);
				}
			}
		}
		return interestingPrimes;
	}
 	
	private Queue<Integer> initInterestingPrimes(Queue<Integer> interestingPrimes){
		for(int i = 2; i < 10; i++) 
			if(this.isPrime(i)) interestingPrimes.add(i);
		return interestingPrimes;
	}
	
	private boolean isPrime(int prime){
		for(int i = 2; i <= sqrt(prime); i++)
			if(prime % i == 0) return false;
		return true;
	}
	
	private StringBuilder buildInterestingPrimePrinter(Queue<Integer> interestingPrimes, StringBuilder interestingPrimePrinter){
		for(int interestingPrime : interestingPrimes) interestingPrimePrinter.append(interestingPrime).append("\n");
		return interestingPrimePrinter;
	}
	
}
