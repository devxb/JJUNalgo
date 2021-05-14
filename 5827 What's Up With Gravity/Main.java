package algorithm;

import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args){
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Captain{
    
    public int y, x, cnt;
    public boolean gravity;
    
    public Captain(int cnt, boolean gravity, int y, int x){
        set(cnt, gravity, y, x);
    }
    
    public void set(int cnt, boolean gravity, int y, int x){
        this.y = y;
        this.x = x;
        this.gravity = gravity;
        this.cnt = cnt;
    }
    
}

class Solve{
    
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private int dp[][];
    private int N,M;
    private char board[][];
    private Captain Bovidian, Beefalo;
    private int dx[] = {-1,1};
    
    public void run(){
        if(!input()) System.out.println(-1);
        else{
            System.out.println(BFS());
        }
    }
    
    private boolean input(){
        try{
            String temp[] = br.readLine().split(" ");
            N = Integer.parseInt(temp[0]);
            M = Integer.parseInt(temp[1]);
            dp = new int[N+5][M+5];
            board = new char[N+5][M+5];
            for(int i = 1; i <= N; i++){
                String temp2 = br.readLine();
                for(int j = 0; j < M; j++) {
                    board[i][j+1] = temp2.charAt(j);
                    dp[i][j+1] = 987654321;
                    if(board[i][j+1] == 'C') Bovidian = new Captain(0, false, i, j+1);
                    if(board[i][j+1] == 'D') Beefalo = new Captain(0, false, i, j+1);
                }
            }
            if(Bovidian == null || Beefalo == null) return false;
            setBovidian(Bovidian);
            if(Bovidian.y == -1) return false; // 처음부터 빠져나간다면 false를 반환
            return true;
        } catch(IOException ioe) {}
        return false;
    }
    
    private void setBovidian(Captain captain){
        captain.set(0, false, fall(captain.y, captain.x, false), captain.x);
    }
    
    private int fall(int y, int x, boolean gravity){
        if(outOfIndex(y,x)) return -1;
        if(gravity == true){ // 역방향
            while(y > 0 && board[y-1][x] != '#' && board[y][x] != 'D') y--;
            // #을 만나거나 y가 범위를 이탈하면 종료 떨어지는중 Beefalo만나는경우 처리
            if(outOfIndex(y,x)) return -1;
            return y;
        }
        if(gravity == false){ // 정방향
            while(y <= N && board[y+1][x] != '#' && board[y][x] != 'D') y++; 
            // #을 만나거나 y가 범위를 이탈하면 종료 떨어지는중 Beefalo만나는경우 처리
            if(outOfIndex(y,x)) return -1;
            return y;
        }
        return -1;
    }
    
    private int BFS(){
        int ret = 987654321;
        Queue<Captain> q = new LinkedList<Captain>();
        q.add(Bovidian);
        dp[Bovidian.y][Bovidian.x] = 0;
        while(!q.isEmpty()){
            int nowCnt = q.peek().cnt;
            boolean nowGravity = q.peek().gravity;
            int nowY = q.peek().y;
            int nowX = q.peek().x;
            q.poll();
            if(dp[nowY][nowX] < nowCnt) continue; // 더 작은 반전횟수로 이동가능했다면 continue
            if(nowY == Beefalo.y && nowX == Beefalo.x){ // Beefalo를 만난다면 continue
                ret = Math.min(ret, nowCnt);
                continue;
            }
            // 중력 반전
            boolean reverseGravity = (nowGravity == false) ? true : false;
            int reverseY = fall(nowY, nowX, reverseGravity);
            if(reverseY != -1 && !outOfIndex(reverseY, nowX) && dp[reverseY][nowX] > nowCnt+1){
                dp[reverseY][nowX] = nowCnt+1;
                q.add(new Captain(nowCnt+1, reverseGravity, reverseY, nowX));
            }
            // 기본 이동
            for(int i = 0; i < 2; i++){
                int nextX = nowX + dx[i];
                if(outOfIndex(nowY, nextX) || board[nowY][nextX] == '#') continue;
                int nextY = fall(nowY, nextX, nowGravity);
                int nextCnt = nowCnt;
                if(nextY == -1 || outOfIndex(nextY, nextX) || dp[nextY][nextX] <= nextCnt || board[nextY][nextX] == '#') continue;
                dp[nextY][nextX] = nextCnt;
                q.add(new Captain(nextCnt, nowGravity, nextY, nextX));
            }
        }
        if(ret == 987654321) ret = -1;
        return ret;
    }
    
    private boolean outOfIndex(int y, int x){
        return y < 1 || y > N || x < 1 || x > M;
    }
}
