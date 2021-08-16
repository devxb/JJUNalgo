import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args) throws Exception{
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    private static class Node implements Comparable<Node>{
        
        private int y, x, time, bridge, check;
        
        public Node(int y, int x, int time, int bridge, int check){
            this.y = y;
            this.x = x;
            this.time = time;
            this.bridge = bridge;
            this.check = check;
        }
        
        public int compareTo(Node node){
            if(this.time > node.time) return 1;
            if(this.time < node.time) return -1;
            if(this.time == node.time){
                if(this.bridge > node.bridge) return -1;
                if(this.bridge < node.bridge) return 1;
                return 0;
            }
            return 0;
        }
        
    }
    
    int N, M;
    int[] dy = {-1, 0, 1, 0, 1, 1, -1, -1};
    int[] dx = {0, -1, 0, 1, -1, 1, -1, 1};
    int[][][] check;
    int[][] cross;
    
    public void run(){
        System.out.println(getSol(getArr()));
    }
    
    /*
    true : cross
    */
    private boolean checkCross(int[][] arr, int y, int x){
        if((!outOfBound(y-1, x) && arr[y-1][x] != 1) || (!outOfBound(y+1, x) && arr[y+1][x] != 1)){
            if((!outOfBound(y, x-1) && arr[y][x-1] != 1) || (!outOfBound(y, x+1) && arr[y][x+1] != 1)) return true;
        }
        if((!outOfBound(y, x-1) && arr[y][x-1] != 1) || (!outOfBound(y, x+1) && arr[y][x+1] != 1)){
            if((!outOfBound(y-1, x) && arr[y-1][x] != 1) || (!outOfBound(y+1, x) && arr[y+1][x] != 1)) return true;
        }
        return false;
    }
    
    private int getSol(int[][] arr){
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(0, 0, 0, 0, 0));
        while(!pq.isEmpty()){
            int nowY = pq.peek().y;
            int nowX = pq.peek().x;
            int nowTime = pq.peek().time;
            int bridge = pq.peek().bridge;
            int nowCheck = pq.peek().check;
            pq.poll();
            if(check[nowY][nowX][bridge] <= nowTime) continue; 
            if(nowY == N-1 && nowX == N-1) return nowTime;
            check[nowY][nowX][bridge] = nowTime;
            for(int i = 0; i < 4; i++){
                int nextY = nowY + dy[i];
                int nextX = nowX + dx[i];
                int nextTime = 0;
                int nextCheck = 0;
                int nextBridge = bridge;
                if(outOfBound(nextY, nextX)) continue;
                /*start set NextTime*/
                if(arr[nextY][nextX] == 1) nextTime = nowTime + 1;
                else if(arr[nextY][nextX] == 0 && nextBridge == 0 && !checkCross(arr, nextY, nextX)){
                    nextTime = getNextTime(nowTime, M);
                    nextBridge = 1;
                }
                else if(arr[nextY][nextX] > 1 && nowCheck == 0){
                    nextCheck = 1;
                    nextTime = getNextTime(nowTime, arr[nextY][nextX]);
                }
                /*end set NextTime*/
                if(check[nextY][nextX][bridge] <= nextTime || nextTime == 0) continue;
                pq.add(new Node(nextY, nextX, nextTime, nextBridge, nextCheck));
            }
        }
        return 0;
    }
    
    private int getNextTime(int time, int period){
        return period * ((time / period) + 1);
    }
    
    /* true = 범위벗어남 */
    private boolean outOfBound(int y, int x){
        return y >= N || x >= N || y < 0 || x < 0;
    }
    
    private int[][] getArr(){
        int[][] ret = null;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            String[] read = br.readLine().split(" ");
            this.N = Integer.parseInt(read[0]);
            this.M = Integer.parseInt(read[1]);
            ret = new int[N+5][N+5];
            check = new int[N+5][N+5][5];
            cross = new int[N+5][N+5];
            for(int i = 0; i <= N; i++) for(int j = 0; j <= N; j++) for(int l = 0; l <= 3; l++) check[i][j][l] = 987654321;
            for(int i = 0; i < N; i++){
                read = br.readLine().split(" ");
                for(int j = 0; j < N; j++) ret[i][j] = Integer.parseInt(read[j]);
            }
        }
        catch(IOException IOE){}
        return ret;
    }
}
