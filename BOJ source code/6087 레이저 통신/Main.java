import java.util.Queue;
import java.util.LinkedList;
import java.util.Collections;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main{
    
    public static void main(String[] args){
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    private static class Pos{
        
        private int y, x;
        
        public Pos(int y, int x){
            this.y = y;
            this.x = x;
        }
        
    }
    
    private static class Node{
        
        private int turn;
        private int dir;
        private int y, x;
        
        public Node(int y, int x, int turn, int dir){
            this.y = y;
            this.x = x;
            this.turn = turn;
            this.dir = dir;
        }
        
    }
    
    private int[] dy = {-1, 0, 1, 0};
    private int[] dx = {0, -1, 0, 1};
    private int W, H;
    private char[][] arr;
    private int[][][] check;
    private Pos endPos;
    private Queue<Node> q = new LinkedList<Node>();
    
    public void run(){
        input();
        System.out.println(BFS());
    }
    
    private int BFS(){
        int ans = 987654321;
        while(!q.isEmpty()){
            int nowY = q.peek().y;
            int nowX = q.peek().x;
            int nowTurn = q.peek().turn;
            int nowDir = q.peek().dir;
            q.poll();
            if(check[nowY][nowX][nowDir] <= nowTurn || ans <= nowTurn) continue;
            check[nowY][nowX][nowDir] = nowTurn;
            if(nowY == endPos.y && nowX == endPos.x){
                ans = nowTurn;
                continue;
            }
            for(int i = 0; i < 4; i++){
                int nextY = nowY + dy[i];
                int nextX = nowX + dx[i];
                int nextDir = i;
                int nextTurn = nowTurn;
                if(nowDir != nextDir) nextTurn++;
                if(outOfBounds(nextY, nextX) || check[nextY][nextX][i] <= nextTurn || ans <= nowTurn || arr[nextY][nextX] == '*') continue;
                q.add(new Node(nextY, nextX, nextTurn, nextDir));
            }
        }
        return ans;
    }
    
    private boolean outOfBounds(int y, int x){
        return (y >= H || y < 0 || x >= W || x < 0);
    }
    
    private void input(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            String[] read = br.readLine().split(" ");
            W = Integer.parseInt(read[0]);
            H = Integer.parseInt(read[1]);
            arr = new char[H][W];
            check = new int[H][W][4];
            for(int i = 0; i < H; i++) for(int j = 0; j < W; j++) for(int d = 0; d < 4; d++) check[i][j][d] = 987654321;
            for(int i = 0; i < H; i++){
                read = br.readLine().split(" ");
                for(int j = 0; j < W; j++){
                    arr[i][j] = read[0].charAt(j);
                    if(arr[i][j] == 'C' && q.isEmpty()){
                        q.add(new Node(i,j,0,0));
                        q.add(new Node(i,j,0,1));
                        q.add(new Node(i,j,0,2));
                        q.add(new Node(i,j,0,3));
                    }
                    else if(arr[i][j] == 'C') endPos = new Pos(i,j);
                }
            }
        }catch(IOException IOE){}
    }
    
}
