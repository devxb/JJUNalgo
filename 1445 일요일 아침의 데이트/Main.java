import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        algorithm algorithm = new algorithm(br);
        int[] answer = algorithm.run();
        System.out.println(answer[0] + " " + answer[1]);
    }
    
}

class Pair{
    public int y;
    public int x;
    
    public Pair(int y, int x){
        set(y,x);
    }
    
    public void set(int y, int x){
        this.y = y;
        this.x = x;
    }
    
}

class Position implements Comparable<Position>{
    public int garbageCnt, sideCnt, y, x;
    public Position(int garbageCnt, int sideCnt, int y, int x){
        this.garbageCnt = garbageCnt;
        this.sideCnt = sideCnt;
        this.y = y;
        this.x = x;
    }
    
    @Override
    public int compareTo(Position pos){
        if(this.garbageCnt > pos.garbageCnt) return 1;
        if(this.garbageCnt < pos.garbageCnt) return -1;
        if(this.garbageCnt == pos.garbageCnt) {
            if(this.sideCnt > pos.sideCnt) return 1;
            if(this.sideCnt < pos.sideCnt) return -1;
        }
        return 0;
    }
    
}

class algorithm{
    private int n,m;
    private BufferedReader br;
    private int[][] forest;
    private Pair[][] check;
    private Pair startPoint, endPoint;
    private int[] dy = {-1, 0, 1, 0};
    private int[] dx = {0, -1, 0, 1};
    
    public algorithm(BufferedReader br){
        this.br = br;
    }
    
    public int[] run(){
        input();
        return getPassForest();
    }
    
    private void input(){
        try{
            String[] parser = br.readLine().split(" ");
            this.n = Integer.parseInt(parser[0]);
            this.m = Integer.parseInt(parser[1]);
            forest = new int[n+5][m+5];
            check = new Pair[n+5][m+5];
            for(int i = 1; i <= n; i++){
                String parserIn = br.readLine();
                for(int j = 0; j < m; j++) {
                    check[i][j+1] = new Pair(987654321, 987654321);
                    if(parserIn.charAt(j) == 'S') startPoint = new Pair(i,j+1);
                    else if(parserIn.charAt(j) == 'F') endPoint = new Pair(i,j+1);
                    else if(parserIn.charAt(j) == 'g'){
                        forest[i][j+1] = 2;
                        for(int l = 0; l < 4; l++){
                            forest[i+dy[l]][j+1+dx[l]] = Math.max(1,forest[i+dy[l]][j+1+dx[l]]);
                        }
                    }
                }
            }
        }catch(IOException ioe){
            System.exit(1);
        }
    }
    
    private int[] getPassForest(){
        Position pos = new Position(0, 0 ,startPoint.y,startPoint.x);
        check[startPoint.y][startPoint.x].set(pos.garbageCnt, pos.sideCnt);
        PriorityQueue<Position> pq = new PriorityQueue<Position>();
        pq.add(pos);
        while(!pq.isEmpty()){
            int garbageCnt = pq.peek().garbageCnt;
            int sideCnt = pq.peek().sideCnt;
            int y = pq.peek().y;
            int x = pq.peek().x;
            pq.poll();
            if(check[y][x].y > garbageCnt) continue;
            if(check[y][x].y == garbageCnt && check[y][x].x > sideCnt) continue;
            if(y == endPoint.y && x == endPoint.x) {
                int[] ret = {garbageCnt, sideCnt};
                return ret;
            
            }
            for(int i = 0; i < 4; i++){
                int _y = y+dy[i];
                int _x = x+dx[i];
                int _garbageCnt = garbageCnt;
                int _sideCnt = sideCnt;
                if(forest[_y][_x] == 2) _garbageCnt++;
                else if(forest[_y][_x] == 1 && (_y != endPoint.y || _x != endPoint.x)) _sideCnt++;
                if(_y < 1 || _y > this.n || _x < 1 || _x > this.m || check[_y][_x].y < _garbageCnt) continue;
                if(check[_y][_x].y == _garbageCnt){
                    if(check[_y][_x].x <= _sideCnt) continue;
                }
                check[_y][_x].set(_garbageCnt,_sideCnt);
                pq.add(new Position(_garbageCnt, _sideCnt, _y, _x));
            }
        }
        return new int[2];
    }
    
}
