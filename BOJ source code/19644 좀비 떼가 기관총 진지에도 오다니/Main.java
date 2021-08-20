import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args) throws Exception{
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
   
   private int L, D, K, C;
   private int[] zombies;
   private Queue<Integer> q = new LinkedList<Integer>();
   
   public void run(){
       input();
       System.out.println(defense(1, 1));
   }
   
   private void input(){
       try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
           L = Integer.parseInt(br.readLine());
           String[] read = br.readLine().split(" ");
           D = Integer.parseInt(read[0]);
           K = Integer.parseInt(read[1]);
           C = Integer.parseInt(br.readLine());
           zombies = new int[L+5];
           for(int meter = 1; meter <= L; meter++) zombies[meter] = Integer.parseInt(br.readLine());
       }catch(IOException IOE){}
   }
   
   private String defense(int left, int right){
       if(right > L) return "YES";
       delQue(left);
       int damage = Math.max(((right-left+1)*K-q.size()*K), K);
       if(zombies[right] > damage){
           q.add(right);
           if(C > 0) C--;
           else return "NO";
       }
       if(right-left+1 < D) return defense(left, right+1);
       return defense(left+1, right+1);
   } 
   
   private void delQue(int left){
       while(!q.isEmpty() && q.peek() < left) q.poll();
   }
   
}
