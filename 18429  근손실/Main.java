import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main{
    
    public static void main(String[] args){
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    private int N, K;
    private int[] arr;
    private boolean[] check;
    
    public void run(){
        input();
        System.out.println(getComb(1, 500));
    }
    
    private int getComb(int day, int weight){
        if(weight < 500) return 0;
        if(day == N) return 1;
        int ret = 0;
        for(int i = 0; i < N; i++){
            if(check[i]) continue;
            check[i] = true;
            ret += getComb(day+1, weight-K+arr[i]);
            check[i] = false;
        }
        return ret;
    } 
    
    private void input(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            String[] read = br.readLine().split(" ");
            this.N = Integer.parseInt(read[0]);
            this.K = Integer.parseInt(read[1]);
            arr = new int[N];
            check = new boolean[N];
            read = br.readLine().split(" ");
            for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(read[i]);
        }catch(IOException IOE){}
    }
    
}
