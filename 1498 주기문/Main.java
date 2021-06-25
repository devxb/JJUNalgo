import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private String str;
    
    public void run() throws IOException{
        str = br.readLine();
        kmp();
    }
    
    private void kmp(){
        int[] pi = new int[str.length()+5];
        int pIdx = 0;
        for(int idx = 1; idx < str.length(); idx++){
            while(str.charAt(idx) != str.charAt(pIdx) && pIdx > 0) pIdx = pi[pIdx-1];
            if(str.charAt(idx) == str.charAt(pIdx)) pi[idx] = ++pIdx;
        }
        for(int i = 1; i < str.length(); i++){
            int sufCnt = pi[i];
            int preCnt = (i+1)-pi[i];
            if(sufCnt % preCnt == 0 && sufCnt/preCnt > 0) 
                System.out.println((i+1) + " " + ((sufCnt/preCnt)+1));
        }
    }
    
} 
