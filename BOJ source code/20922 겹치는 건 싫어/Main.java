import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args) throws Exception{
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private String[] read;
    private int N, K;
    private int[] arr;
    private int[] check;
    public void run() throws Exception{
        input();
        System.out.println(getLongestArr());
    }
    
    private void input() throws Exception{
        read = br.readLine().split(" ");
        N = Integer.parseInt(read[0]);
        K = Integer.parseInt(read[1]);
        arr = new int[N+5];
        check = new int[100005];
        read = br.readLine().split(" ");
        for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(read[i]);
    }
    
    private int getLongestArr(){
        int ret = 0, right = 0, left = 0;
        while(right < N){
            if(!increase(right++)) while(!decrease(left++) && left < right);
            ret = Math.max(ret, right - left);
        }
        return Math.max(ret, right - left);
    }
    
    private boolean increase(int idx){
        int increaseNum = arr[idx];
        check[increaseNum]++;
        if(check[increaseNum] > K) return false;
        return true;
    }
    
    private boolean decrease(int idx){
        int decreaseNum = arr[idx];
        if(check[decreaseNum] > K) {
            check[decreaseNum] -= 1;
            return true;
        }
        check[decreaseNum] = Math.max(0, check[decreaseNum]-1);
        return false;
    } 
    
}
