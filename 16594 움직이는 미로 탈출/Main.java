import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Position implements Comparable<Position>{
    
    public int y, x, cnt;
    
    public Position(int cnt, int y, int x){
        this.cnt = cnt;
        this.y = y;
        this.x = x;
    }
    
    @Override
    public int compareTo(Position pos){
        if(cnt > pos.cnt) return 1;
        if(cnt < pos.cnt) return -1;
        return 0;
    }
}

class Solve{
    
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String read;
    char[][][] arr = new char[10][10][10];
    boolean[][][] check = new boolean[10][10][10];
    int dy[] = {0, -1, 0, 1, 0, 1, 1, -1, -1};
    int dx[] = {0, 0, -1, 0, 1, 1, -1, 1, -1};
    
    public void run(){
        try{
            doInput();
        } catch (IOException ioe){}
        downArr();
        System.out.println(simulate());
    }
    
    private void doInput() throws IOException{
        
        for(int i = 1; i <= 8; i++){
            read = br.readLine();
            for(int j = 1; j <= 8; j++) arr[i][j][0] = read.charAt(j-1);
        }
        
    }
    
    private void downArr(){
        for(int i = 1; i <= 8; i++){
            for(int y = 1; y <= 8; y++){
                for(int x = 1; x <= 8; x++){
                    if(y-1 < 1) arr[y][x][i] = '.';
                    else arr[y][x][i] = arr[y-1][x][i-1];
                }
            }
        }
    }
    
    private int simulate(){
        
        PriorityQueue<Position> pq = new PriorityQueue<Position>();
        pq.add(new Position(0,8,1)); // cnt, y, x
        while(!pq.isEmpty()){
            int nowCnt = pq.peek().cnt;
            int nowY = pq.peek().y;
            int nowX = pq.peek().x;
            if(nowY == 1 && nowX == 8) return 1;
            check[nowY][nowX][nowCnt] = true;
            pq.poll();
            if(arr[nowY][nowX][nowCnt] == '#') continue;
            for(int i = 0; i < 9; i++){
                int nextY = nowY + dy[i];
                int nextX = nowX + dx[i];
                int nextCnt = Math.min(nowCnt + 1, 8);
                if(check[nextY][nextX][nextCnt] == true || arr[nextY][nextX][nowCnt] == '#' || nextY < 1 || nextY > 8 || nextX < 1 || nextX > 8) continue;
                pq.add(new Position(nextCnt, nextY, nextX));
            }
        }
        
        return 0;
    }
    
}
