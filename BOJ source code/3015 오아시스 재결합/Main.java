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
        private int num;
        private int dup;
        
        public Num(int num, int dup){
            this.num = num;
            this.dup = dup;
        }
    }

    private int N;
    private Stack<Num> headerStack = new Stack<Num>();
    
    public void run(){
        System.out.println(sol());
    }
    
    private long sol(){
        long ret = 0;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            N = Integer.parseInt(br.readLine());
            for(int i = 0; i < N; i++) ret += stackOperation(Integer.parseInt(br.readLine()));
        }catch(IOException IOE){}
        return ret;
    }
    
    private long stackOperation(int last){
        long ret = 0;
        if(headerStack.isEmpty()){
            headerStack.add(new Num(last, 1));
            return 0;
        }
        Num num = new Num(last, 1);
        while(!headerStack.isEmpty() && headerStack.peek().num <= last) {
            if(headerStack.peek().num == num.num) num.dup += headerStack.peek().dup;
            ret += headerStack.pop().dup;
        }
        if(!headerStack.isEmpty()) ret++;
        headerStack.add(num);
        return ret;
    }
    
}
