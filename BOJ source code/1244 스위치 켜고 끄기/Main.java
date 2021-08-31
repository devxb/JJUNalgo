// 1765
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
    
    private int N;
    private int[] swi;
    
    public void run(){
        input();
        for(int i = 1; i <= N; i++) {
            System.out.print(swi[i] + " ");
            if(i % 20 == 0) System.out.println();
        }
    }
    
    private void girl(int num){
        int left = num;
        int right = num;
        while(left >= 1 && right <= N){
            if(swi[left] != swi[right]) break;
            left--;
            right++;
        }
        right -= 1;
        left += 1;
        if(left > right){
            left = num;
            right = num;
        }
        for(int i = left; i <= right; i++) swi[i] = (swi[i] == 1) ? 0 : 1;
    }
    
    private void boy(int num){
        for(int i = num; i <= N; i+=num) swi[i] = (swi[i] == 1) ? 0 : 1;
    }
    
    private void input(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            N = Integer.parseInt(br.readLine());
            String[] read = br.readLine().split(" ");
            swi = new int[N+1];
            for(int i = 1; i <= N; i++) swi[i] = Integer.parseInt(read[i-1]);
            int M = Integer.parseInt(br.readLine());
            for(int i = 0; i < M; i++){
                read = br.readLine().split(" ");
                int a = Integer.parseInt(read[0]);
                int b = Integer.parseInt(read[1]);
                if(a == 1) boy(b);
                else girl(b);
            }
        }catch(IOException IOE){}
    }
    
}
