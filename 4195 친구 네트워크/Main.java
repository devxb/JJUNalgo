import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    private Scanner sc = new Scanner(System.in);
    private int F;
    private int[] disjointSet;
    private HashMap<String, Integer> friendMapping;
    private int[] friendsCount;
    
    public void run() throws IOException{
        int T = sc.nextInt();
        for(int i = 0; i < T; i++){
            input();
        }
    }
    
    private int findDisjointSet(int idx){
        if(disjointSet[idx] == idx) return idx;
        return disjointSet[idx] = findDisjointSet(disjointSet[idx]);
    }
    
    private int setDisjointSet(int par, int son){
        par = findDisjointSet(par);
        son = findDisjointSet(son);
        if(par != son){
            disjointSet[son] = par;
            friendsCount[par] += friendsCount[son];
        }
        return friendsCount[par];
    }
    
    private void input() throws IOException{
        F = sc.nextInt();
        friendMapping = new HashMap<String, Integer>(F*2+5);
        friendsCount = new int[F*2+5];
        disjointSet = new int[F*2+5];
        
        // 초기화
        for(int i = 0; i <= F*2+4; i++) {
            disjointSet[i] = i;
            friendsCount[i] = 1;
        }
        // 초기화 끝
        
        int friendsNum = 1;
        for(int i = 0; i < F; i++){
            String f1 = sc.next();
            String f2 = sc.next();
            Integer f1Num = friendMapping.get(f1);
            Integer f2Num = friendMapping.get(f2);
            if(f1Num == null) { // 처음 등장한 친구라면, hashmap에 저장
                friendMapping.put(f1, friendsNum);
                f1Num = friendsNum;
                friendsNum++;
            }
            if(f2Num == null) { // 처음 등장한 친구라면, hashmap에 저장
                friendMapping.put(f2, friendsNum);
                f2Num = friendsNum;
                friendsNum++;
            }
            int par = Math.min(f1Num, f2Num);
            int son = Math.max(f1Num, f2Num);
            System.out.println(setDisjointSet(par, son));
        }
    }
    
}
