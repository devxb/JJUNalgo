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
    private int[][] player;
    private int[] playerSet;
    private boolean[] check;
    private int N, score;
    
    public void run() throws Exception{
        input();
        makePlayerSet(1);
        System.out.println(score);
    }
    
    private void input() throws Exception{
        read = br.readLine().split(" ");
        N = Integer.parseInt(read[0]);
        player = new int[N+5][15];
        playerSet = new int[15];
        check = new boolean[15];
        for(int i = 1; i <= N; i++){
            read = br.readLine().split(" ");
            for(int j = 1; j <= 9; j++) player[i][j] = Integer.parseInt(read[j-1]);
        }
    }
    
    private void makePlayerSet(int idx){
        if(idx > 9){
            score = Math.max(score, getScore(1, 0));
            return;
        }
        if(idx == 4){
            playerSet[4] = 1;
            makePlayerSet(idx+1);
            return;
        }
        for(int i = 2; i <= 9; i++){
            if(check[i]) continue;
            check[i] = true;
            playerSet[idx] = i;
            makePlayerSet(idx+1);
            check[i] = false;
        }
    }
    
    private int getScore(int inning, int playerIdx){
        if(inning > N) return 0;
        int score = 0, outStack = 0;
        Queue<Integer> scoreStack = new LinkedList<Integer>();
        while(outStack < 3){
            playerIdx++;
            if(playerIdx > 9) playerIdx = 1;
            if(player[inning][playerSet[playerIdx]] == 0){
                outStack++;
                continue;
            }
            if(player[inning][playerSet[playerIdx]] == 4){
                score += setScore(scoreStack, 4) + 1;
                continue;
            }
            score += setScore(scoreStack, player[inning][playerSet[playerIdx]]);
            scoreStack.add(player[inning][playerSet[playerIdx]]);
        }
        return score+getScore(inning+1, playerIdx);
    }
    
    private int setScore(Queue<Integer> scoreStack, int move){
        int score = 0, size = scoreStack.size();
        for(int i = 0; i < size; i++){
            Integer nowIdx = scoreStack.poll();
            if(nowIdx + move > 3) score++;
            else scoreStack.add(nowIdx+move);
        }
        return score;
    }
    
}
