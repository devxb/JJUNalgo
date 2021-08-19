import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args) throws Exception{
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    private static class Num{
        int num, idx;
        
        public Num(int num, int idx){
            this.num = num;
            this.idx = idx;
        }
        
    }
    
    private int N;
    private int[] ans;
    Stack<Num> stack = new Stack<Num>();
    
    public void run(){
        input();
        for(int i = 1; i <= N; i++) System.out.print(ans[i] + " ");
    }
    
    private void input(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            N = Integer.parseInt(br.readLine());
            int[] arr = new int[N+5];
            ans = new int[N+5];
            String[] read = br.readLine().split(" ");
            for(int i = 1; i <= N; i++) arr[i] = Integer.parseInt(read[i-1]);
            for(int i = N; i >= 1; i--) shootLaser(arr[i], i);
        }catch(IOException IOE){}
    }
    
    private void shootLaser(int num, int idx){
        while(!stack.isEmpty() && stack.peek().num < num) ans[stack.pop().idx] = idx;
        stack.add(new Num(num, idx));
    }
    
}
