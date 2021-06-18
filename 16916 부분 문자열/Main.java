import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args) throws Exception{
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    private Scanner sc = new Scanner(System.in);
    private String S, P;
    private int[] pi;
    
    public void run(){
        input();
        pi = setPi(P);
        System.out.println(doKmp(S,P));
    }
    
    private void input(){
        S = sc.next();
        P = sc.next();
    }
    
    private int doKmp(String target, String str){
        int strIdx = 0;
        for(int targetIdx = 0; targetIdx < target.length(); targetIdx++){
            while(target.charAt(targetIdx) != str.charAt(strIdx) && strIdx > 0) strIdx = pi[strIdx-1];
            if(str.charAt(strIdx) == target.charAt(targetIdx)) strIdx++;
            if(strIdx >= str.length()) return 1;
        }
        return 0;
    }
    
    private int[] setPi(String str){
        int[] ret = new int[str.length()+5];
        int pIdx = 0;
        for(int i = 1; i < str.length(); i++){
            while(str.charAt(i) != str.charAt(pIdx) && pIdx > 0) pIdx = ret[pIdx-1];
            if(str.charAt(i) == str.charAt(pIdx)){
                pIdx++;
                ret[i] = pIdx;
            }
        }
        return ret;
    }
    
}
