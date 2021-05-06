import java.io.*;
import java.util.*;

public class Main{
    
    public static void main(String[] args){
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Print{
    public int num, a, b;
    
    public Print(int num, int a, int b){
        set(num, a, b);
    }
    
    public void set(int num, int a, int b){
        this.num = num;
        this.a = a;
        this.b = b;
    }
    
    @Override
    public String toString(){
        if(a > b){
            int temp = a;
            a = b;
            b = temp;
        }
        return a + " " + b;
    }
}

class Solve{
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private ArrayList<Integer> integer = new ArrayList<>();
    
    public void run(){
        input();
        System.out.println(getSolution());
    }
    
    private void input(){
        try{
            String[] trash = br.readLine().split(" ");
            String[] temp = br.readLine().split(" ");
            for(int i = 0; i < temp.length; i++) integer.add(Integer.parseInt(temp[i]));
        } catch (IOException ioe){}
    }
    
    private int abs(int num){
        return Math.max(-1*num, num);
    }
    
    private Print getSolution(){
        Print print = new Print(2100000000, 1, 2);
        int left = 0, right = integer.size()-1;
        while(left < right){
            int solution = integer.get(left) + integer.get(right);
            if(abs(solution) < print.num){
                print.set(abs(solution), integer.get(left), integer.get(right));
            }
            if(solution < 0) left++;
            if(solution > 0) right--;
            if(solution == 0) return print;
        }
        return print;
    }
    
}
