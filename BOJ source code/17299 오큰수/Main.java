import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args) throws Exception{
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    private static class Node{
        private int idx;
        private int num;
        
        public Node(int idx, int num){
            this.idx = idx;
            this.num = num;
        }
        
    }
    
    private int N;
    private int[] ans;
    private Stack<Node> stack = new Stack<Node>();
    
    public void run(){
        input();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) sb.append(ans[i] + " ");
        System.out.print(sb.toString());
    }
    
    private void input(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            this.N = Integer.parseInt(br.readLine());
            ans = new int[N+5];
            for(int idx = 0; idx <= N; idx++) ans[idx] = -1;
            String[] read = br.readLine().split(" ");
            for(int idx = 0; idx < N; idx++) operateStack(new Node(idx, Integer.parseInt(read[idx])));
        }catch(IOException IOE){}
    }
    
    private void operateStack(Node node){
        while(!stack.isEmpty() && (stack.peek().num < node.num)) ans[stack.pop().idx] = node.num;
        stack.add(node);
    }
    
}
