package algorithm;

import java.io.*;
import java.util.*;

public class Main{
    
    public static void main(String[] args){
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Position implements Comparable<Position>{
    
    public int distance,y, x;
    
    public Position(int distance, int y, int x){
        set(distance, y, x);
    }
    
    public void set(int distance, int y, int x){
        this.distance = distance;
        this.y = y;
        this.x = x;
    }
    
    @Override
    public int compareTo(Position pair){
        if(distance > pair.distance) return 1;
        else if(distance < pair.distance) return -1;
        return 0;
    }
    
}


class Solve{
    
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private int R, C, mark = 1;
    private int outMark;
    private char[][] island;
    private int[][] marked;
    private int[][] dp = new int[1 << 17][20]; 
    private int[] dy = {-1, 1, 0, 0};
    private int[] dx = {0, 0, -1, 1};
    private int[][] road;

    public void run(){
        input(); // 입력받고
        setIsland(); // 섬들 이름지정
        setRoad(); // 섬들간 최소거리 구하기 이후에 이 값들을 갖고 탐색할것.
        setOutMark(mark);
        System.out.println(getSwim(0,0));
    }
    
    public void setOutMark(int mark){
        for(int i = 1; i <= mark; i++) outMark = (outMark | (1 << i));
    }
    
    private int getSwim(int visit, int idx){
        if(dp[visit][idx] != 0) return dp[visit][idx];
        if(outMark == visit) return dp[visit][idx] = 0;
        int ret = 987654321;
        for(int next = 1; next <= mark; next++){
            if(idx == next || (visit & (1 << next)) != 0) continue;
            ret = Math.min(ret, getSwim((visit | (1 << next)), next) + road[idx][next]);
        }
        return dp[visit][idx] = ret;
    }
    
    private void setRoad(){
        boolean[] alreadyMark = new boolean[mark+5];
        for(int i = 1; i <= R; i++){
            for(int j = 1; j <= C; j++){
                if(island[i][j] == 'X' && !alreadyMark[marked[i][j]]){
                    alreadyMark[marked[i][j]] = true;
                    makeRoad(marked[i][j],i,j);
                }
            }
        }
    }
    
    private void makeRoad(int island, int i, int j){
        int[][] check = new int[R+5][C+5];
        boolean[][] initialCheck = new boolean[R+5][C+5];
        PriorityQueue<Position> pq = new PriorityQueue<Position>();
        pq.add(new Position(0, i, j));
        while(!pq.isEmpty()){
            int nowDistance = pq.peek().distance;
            int nowY = pq.peek().y;
            int nowX = pq.peek().x;
            pq.poll();
            if(initialCheck[nowY][nowX] && check[nowY][nowX] <= nowDistance) continue;
            if(marked[nowY][nowX] > 0){
                road[island][marked[nowY][nowX]] = Math.min(nowDistance, road[island][marked[nowY][nowX]]);
            }
            initialCheck[nowY][nowX] = true;
            check[nowY][nowX] = nowDistance;
            for(int dir  = 0; dir < 4; dir++){
                int nextY = nowY + dy[dir];
                int nextX = nowX + dx[dir];
                int nextDistance = nowDistance;
                if(outOfBound(nextY, nextX)) continue;
                if(this.island[nextY][nextX] == 'S') nextDistance++;  
                pq.add(new Position(nextDistance, nextY, nextX));
            }
        }
    }
    
    private void input(){
        try{
            String[] temp = br.readLine().split(" ");
            R = Integer.parseInt(temp[0]);
            C = Integer.parseInt(temp[1]);
            island = new char[R+5][C+5];
            marked = new int[R+5][C+5];
            road = new int[20][20];
            for(int i = 1; i <= R; i++){
                String temp2 = br.readLine();
                for(int j = 0; j < C; j++){
                    island[i][j+1] = temp2.charAt(j);
                }
            }
            for(int i = 1; i <= 15; i++){
                for(int j = 1; j <= 15; j++){
                    road[i][j] = 987654321;
                }
            }
        } catch (IOException ioe){}
    }
    
    private void setIsland(){ // 섬들의 이름지정 초기 1회만실행
        for(int i = 1; i <= R; i++){
            for(int j = 1; j <= C; j++){
                if(marked[i][j] == 0 && island[i][j] == 'X'){
                    initialIsland(i,j,mark);
                    mark++;
                }
            }
        }
        mark--;
    }
    
    private void initialIsland(int y, int x, int marking){
        if(outOfBound(y,x) || island[y][x] != 'X' || marked[y][x] == marking) return;
        marked[y][x] = marking;
        for(int i = 0; i < 4; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(outOfBound(ny, nx) || island[ny][nx] != 'X' || marked[ny][nx] == marking) continue;
            initialIsland(ny, nx, marking);
        }
    }
    
    private boolean outOfBound(int y, int x){
        return y > R || x > C || y < 1 || x < 1 || island[y][x] == '.';
    }
    
}
