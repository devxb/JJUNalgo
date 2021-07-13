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
    private String[] read;
    private int N, K;
    private int[] remainNumber;
    private boolean[] check;
    private int[] relation;
    
    public void run() throws IOException{
        input();
        getRelation();
    }
    
    private void input() throws IOException{
        read = br.readLine().split(" ");
        N = Integer.parseInt(read[0]);
        K = Integer.parseInt(read[1]);
        remainNumber = new int[N+5];
        relation = new int[N+5];
        check = new boolean[N+5];
        for(int i = 1; i <= N; i++) remainNumber[i] = i-1;
    }
    
    private void getRelation(){
        for(int i = 0; i < N; i++){
            int relNum = findRelation(K);
            K -= remainNumber[relNum];
            setRemainNumber(relNum);
            System.out.print(relNum + " ");
        }
    }
    
    private void setRemainNumber(int num){
        for(int i = num+1; i <= N; i++) remainNumber[i]--;
    }
    
    private int findRelation(int max){
        for(int i = N; i >= 1; i--){
            if(check[i]) continue;
            if(max >= remainNumber[i]){
                check[i] = true;
                return i;
            }
        }
        return 1;
    }
    
}
