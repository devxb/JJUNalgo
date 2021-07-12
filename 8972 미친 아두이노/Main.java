import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args) throws Exception{
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    class Position{
        private int y, x;
        private Queue<Integer> move = null;
        
        public Position(int y, int x){
            this.y = y;
            this.x = x;
            this.move = new LinkedList<Integer>();
        }
        
        public void setMove(int m){
            this.move.add(m);
        }
        
        public int getMove(){
            return move.poll();
        }
        
    }
    
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private String[] read;
    private int R, C;
    private boolean trig = true;
    private int[] dy = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
    private int[] dx = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};
    private char[][] arr;
    private char[][] temp;
    private Position aduino;
    private ArrayList<Position> crazyAduino;
    private Queue<Position> explode;
    private int ans;
    
    public void run() throws Exception{
        input();
        simulate();
        if(!trig) System.out.println("kraj " + ans);
        else{
            for(int i = 0; i < R; i++){
                for(int j = 0; j < C; j++){
                    System.out.print(arr[i][j]);
                }
                System.out.println();
            }
        }
    }
    
    private void input() throws Exception{
        read = br.readLine().split(" ");
        R = Integer.parseInt(read[0]);
        C = Integer.parseInt(read[1]);
        arr = new char[R+5][C+5];
        temp = new char[R+5][C+5];
        crazyAduino = new ArrayList<Position>();
        for(int i = 0; i < R; i++){
            read = br.readLine().split(" ");
            for(int j = 0; j < C; j++){
                arr[i][j] = read[0].charAt(j);
                if(arr[i][j] == 'I') this.aduino = new Position(i,j);
                else if(arr[i][j] == 'R') this.crazyAduino.add(new Position(i,j));
            }
        }
        read = br.readLine().split(" ");
        for(int i = 0; i < read[0].length(); i++) aduino.setMove((int)read[0].charAt(i) - (int)'0');
    }
    
    private void simulate(){
        while(!aduino.move.isEmpty() && trig){
            clear();
            move(aduino, 'I'); // 정상 아두이노 움직이기
            merge();
            ans++;
            if(!trig) return; // 만났다면 끝
            for(int i = 0; i < crazyAduino.size(); i++){ // crazy aduino 움직이기
                Position NCA = crazyAduino.get(i); // nowCrazyAduino
                if(NCA.y == -1) continue;
                NCA.setMove(calcMove(NCA)); // 다음위치 계산
                move(NCA, 'R'); // 움직이기
            }
            merge();
            //모두움직이고, 폭발
            doExplode();
        }
    }
    
    private void doExplode(){
        Queue<Position> explode = new LinkedList<Position>();
        boolean[][] check = new boolean[R+5][C+5];
        for(int i = 0; i < crazyAduino.size(); i++){
            Position NCA = crazyAduino.get(i); // nowCrazyAduino
            if(NCA.y == -1) continue;
            if(check[NCA.y][NCA.x]) explode.add(new Position(NCA.y, NCA.x));
            check[NCA.y][NCA.x] = true;
        }
        while(!explode.isEmpty()){
            Position exp = explode.poll();
            arr[exp.y][exp.x] = '.';
            for(int i = 0; i < crazyAduino.size(); i++){
                Position NCA = crazyAduino.get(i);
                if(NCA.y == exp.y && NCA.x == exp.x) NCA.y = -1;
            }
        }
    }
    
    private void merge(){
        for(int i = 0; i < R; i++)
            for(int j = 0; j < C; j++) arr[i][j] = temp[i][j];
    }
    
    private void clear(){
        for(int i = 0; i < R; i++)
            for(int j = 0; j < C; j++) temp[i][j] = '.';
    }
    
    private int calcMove(Position pos){
        int ret = 1;
        int dis = 987654321;
        for(int i = 1; i <= 9; i++){
            int distance = abs(aduino, new Position(pos.y + dy[i], pos.x + dx[i]));
            if(distance < dis){
                ret = i;
                dis = distance;
            }
        }
        return ret;
    }
    
    private void move(Position pos, char C){
        int beforeY = pos.y;
        int beforeX = pos.x;
        int moveDir = pos.getMove();
        int afterY = pos.y + dy[moveDir];
        int afterX = pos.x + dx[moveDir];
        arr[beforeY][beforeX] = '.';
        if(C == 'I' && temp[afterY][afterX] == 'R' || C == 'R' && temp[afterY][afterX] == 'I') trig = false;
        temp[afterY][afterX] = C;
        pos.y = afterY;
        pos.x = afterX;
    }
    
    private int abs(Position p1, Position p2){
        return Math.max(p1.y - p2.y, p2.y - p1.y) + Math.max(p1.x - p2.x, p2.x - p1.x);
    }
    
}
