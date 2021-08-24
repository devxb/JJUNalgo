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
    
    private int N, K;
    private int[][] arr;
    private boolean[] dupCheck;
    private int capBit;
    
    public void run(){
        input();
        floyd();
        for(int i = 0; i <= N; i++) this.capBit |= (1 << i);
        System.out.println(find(K, (1<<(N))|(1<<(K))));
    }
    
    private int find(int K, int bit){
        if(bit == capBit) return 0;
        int ans = 987654321;
        for(int i = 0; i < N; i++){
            int nBit = bit | (1 << i);
            if(nBit == bit) continue; 
            ans = Math.min(ans, find(i, nBit)+arr[K][i]);
        }
        return ans;
    }
    
    private void floyd(){
        for(int mid = 0; mid < N; mid++)
            for(int from = 0; from < N; from++)
                for(int to = 0; to < N; to++) arr[from][to] = Math.min(arr[from][mid] + arr[mid][to], arr[from][to]);
    }
    
    private void input(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            String[] read = br.readLine().split(" ");
            this.N = Integer.parseInt(read[0]);
            this.K = Integer.parseInt(read[1]);
            arr = new int[N][N];
            dupCheck = new boolean[N];
            for(int i = 0; i < N; i++){
                read = br.readLine().split(" ");
                for(int j = 0; j < N; j++) this.arr[i][j] = Integer.parseInt(read[j]);
            }
        }catch(IOException IOE){}
    }
    
}
