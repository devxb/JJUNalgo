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
    private int N;
    private long[] nums;
    private ArrayList<ArrayList<Integer>> edge = new ArrayList<ArrayList<Integer>>();
    
    public void run() throws Exception{
        input();
        setNums();
        for(int i = 0; i < N; i++) System.out.print(nums[i] + " ");
    }
    
    private void setNums(){
        long gcd = getGCD(nums[0], nums[1]);
        while(gcd > 1){
            for(int i = 0; i < N; i++) gcd = getGCD(gcd, nums[i]);
            for(int i = 0; i < N; i++) nums[i] /= gcd;
        }
    }
    
    private void input() throws Exception{
        N = Integer.parseInt(br.readLine());
        nums = new long[N+5];
        for(int i = 0; i < N; i++) {
            edge.add(new ArrayList<Integer>());
            nums[i] = 1;
        }
        for(int i = 0; i < N-1; i++){
            int a, b;
            long p, q;
            String[] read = br.readLine().split(" ");
            a = Integer.parseInt(read[0]);
            b = Integer.parseInt(read[1]);
            p = Long.parseLong(read[2]);
            q = Long.parseLong(read[3]);
            long gcd = getGCD(p, q);
            calc(a, b, p / gcd, q / gcd);
        }
    }
    
    private long getGCD(long a, long b){
        while(b > 0){
            long temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }
    
    private void calc(int a, int b, long p, long q){
        Boolean[] check = new Boolean[N+5];
        long tempA = nums[a];
        long tempB = nums[b];
        update(a, tempB*p, check);
        update(b, tempA*q, check);
        edge.get(a).add(b);
        edge.get(b).add(a);
    }
    
    private void update(int target, long num, Boolean[] check){
        nums[target] *= num;
        check[target] = true;
        for(int i = 0; i < edge.get(target).size(); i++){
            if(check[edge.get(target).get(i)] != null) continue;
            update(edge.get(target).get(i), num, check);
        }
    }
    
}
