package algorithm;

import java.io.*;
import java.util.*;

public class Main{
    
    public static void main(String[] args){
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Pair{
    public int first, second;
    
    public Pair(int first, int second){
        set(first, second);
    }
    
    public void set(int first, int second){
        this.first = first;
        this.second = second;
    }
    
}

class Solve{
    
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private boolean[][][] path; // y,x에서 4방향으로 가는 경로를 체크해준다. 있다면 true
    private int N, K, R;
    private final int[] dy = {0, 0, 1, -1};
    private final int[] dx=  {1, -1, 0, 0};
    private final int[] rev = {1,0,3,2};
    private boolean[][] cows;
    private Queue<Pair> cowNumber = new LinkedList<Pair>();
    
    public void run(){
        input();
        System.out.println(sumPairCow());
    }
    
    private int getAllPair(int cnt){
        int ret = 0;
        for(int i = 1; i <= cnt; i++) ret += (i-1);
        return ret;
    }
    
    private int sumPairCow(){
        int ret = 0;
        while(!cowNumber.isEmpty()){
            Pair cow = cowNumber.poll();
            ret += getPairCow(cow.first, cow.second);
        }
        return getAllPair(K) - (ret/2);
    }
    
    private int getPairCow(int y, int x){
        boolean[][] check = new boolean[N+5][N+5];
        int ret = 0;
        Queue<Pair> cow = new LinkedList<Pair>();
        cow.add(new Pair(y,x));
        while(!cow.isEmpty()){
            int nowY = cow.peek().first;
            int nowX = cow.peek().second;
            cow.poll();
            if(check[nowY][nowX]) continue;
            if(cows[nowY][nowX] && (nowY != y || nowX != x)) ret++;
            check[nowY][nowX] = true;
            for(int dir = 0; dir < 4; dir++){
                int nextY = nowY + dy[dir];
                int nextX = nowX + dx[dir];
                if(nextY < 1 || nextY > N || nextX < 1 || nextX > N || path[nowY][nowX][dir] || check[nextY][nextX]) continue;
                cow.add(new Pair(nextY, nextX));
            }
        }
        return ret;
    }
    
    private void input(){
        try{
            String[] temp = br.readLine().split(" ");
            N = Integer.parseInt(temp[0]);
            K = Integer.parseInt(temp[1]);
            R = Integer.parseInt(temp[2]);
            path = new boolean[N+5][N+5][5];
            cows = new boolean[N+5][N+5];
            for(int i = 0; i < R; i++){
                temp = br.readLine().split(" ");
                int y1 = Integer.parseInt(temp[0]);
                int x1 = Integer.parseInt(temp[1]);
                int y2 = Integer.parseInt(temp[2]);
                int x2 = Integer.parseInt(temp[3]);
                for(int dir = 0; dir < 4; dir++){
                    if(y1+dy[dir] != y2 || x1+dx[dir] != x2) continue;
                    path[y1][x1][dir] = true; // 두 경로를 연결
                    path[y2][x2][rev[dir]] = true; // 두 경로 연결
                    break;
                }
            }
            for(int i = 0; i < K; i++){
                temp = br.readLine().split(" ");
                int y = Integer.parseInt(temp[0]);
                int x = Integer.parseInt(temp[1]);
                cows[y][x] = true;
                cowNumber.add(new Pair(y,x));
            }
        } catch(IOException ioe){}
    }
    
}
