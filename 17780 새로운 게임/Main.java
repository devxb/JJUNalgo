import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception{
        Solve solve = new Solve();
        solve.run();
    }
}

class Chess{ // 체스 말
    public int y, x, dir, serial;
    public Chess(int serial, int y, int x, int dir){
        set(y, x, dir);
        this.serial = serial;
    }
    
    public void set(int y, int x, int dir){
        this.y = y;
        this.x = x;
        this.dir = dir;
    }
    
    public void reverseDir(){
        dir = Solve.dir[dir];
    }
    
}

class ChessMen{ // 쌓여있는 체스말
    public ArrayList<Chess> chessMen = new ArrayList<Chess>();
    
    public boolean add(Chess chess){
        if(chessMen.size() >= 3) return false; // 이미 3개이상 쌓여있으면 이번에 쌓았을때 4개가 쌓이는것이므로 바로 종료해줌
        chessMen.add(chess);
        return true;
    }
    
    private void reverse(){
        Chess temp = new Chess(chessMen.get(0).serial, chessMen.get(0).y, chessMen.get(0).x, chessMen.get(0).dir);
        chessMen.set(0,chessMen.get(chessMen.size()-1));
        chessMen.set(chessMen.size()-1, temp);
    }
    
    public int findSerial(int serial){
        for(int i = 0; i < chessMen.size(); i++){
            if(chessMen.get(i).serial == serial) return i;
        }
        return -1;
    }
    
    public int move(){ // 삭제 요청 = -1, 종료 요청 = -2
        Chess chess = chessMen.get(0);
        int nextY = chess.y + Solve.dy[chess.dir];
        int nextX = chess.x + Solve.dx[chess.dir];
        if(Solve.arr[nextY][nextX] == 1){ // 다음 위치가 빨강
            reverse();
            Solve.chessBoard[chess.y][chess.x] = null;
            if(Solve.chessBoard[nextY][nextX] != null){ // 이미 해당위치에 있다면
                update(nextY, nextX); // 먼저 업데이트하고 붙여주자.
                Solve.chessBoard[nextY][nextX].chessMen.addAll(this.chessMen); // 해당위치에 있는 ChessMen에 이 chessMen을 붙인다.
                if(Solve.chessBoard[nextY][nextX].chessMen.size() > 3) return -2;
                return -1;
            }
            else Solve.chessBoard[nextY][nextX] = this;
            update(nextY, nextX);
        }
        else if(Solve.arr[nextY][nextX] == 2 || nextY > Solve.N || nextY < 1 || nextX > Solve.N || nextX < 1){ // 다음 위치가 파랑
            chess.reverseDir(); // 방향 변경
            nextY = chess.y + Solve.dy[chess.dir];
            nextX = chess.x + Solve.dx[chess.dir]; // 다음위치 계산
            if(Solve.arr[nextY][nextX] == 2 || nextY > Solve.N || nextY < 1 || nextX > Solve.N || nextX < 1){ // 방향 변경후 이동위치가 다시 파랑색이라면,
                return 0; // 이동하지않고, 방향만 바꾼다. 이동하지않았으니 현재위치가 빨간색이더라도 reverse()해주면 안된다.
            }
            else if(Solve.arr[nextY][nextX] == 1){ // 방향 변경후 이동위치라 빨간색이라면,
                reverse(); // 순서를 바꾼다.
                Solve.chessBoard[chess.y][chess.x] = null; // 현재위치를 null로 변경한다
                if(Solve.chessBoard[nextY][nextX] != null){ // 이미 해당위치에 있다면
                    update(nextY,nextX); // 먼저 업데이트하고 붙여주자.
                    Solve.chessBoard[nextY][nextX].chessMen.addAll(this.chessMen); // 해당위치에 있는 ChessMen에 이 chessMen을 붙인다.
                    if(Solve.chessBoard[nextY][nextX].chessMen.size() > 3) return -2;
                    return -1;
                }
                else Solve.chessBoard[nextY][nextX] = this;
                update(nextY,nextX);
            }
            else{ // 방향 변경후 이동위치가 흰색이라면,
                Solve.chessBoard[chess.y][chess.x] = null;
                if(Solve.chessBoard[nextY][nextX] != null){ // 이미 해당위치에 있다면
                    update(nextY,nextX); // 먼저 업데이트하고 붙여주자.
                    Solve.chessBoard[nextY][nextX].chessMen.addAll(this.chessMen); // 해당위치에 있는 ChessMen에 이 chessMen을 붙인다.
                    if(Solve.chessBoard[nextY][nextX].chessMen.size() > 3) return -2;
                    return -1;
                }
                else Solve.chessBoard[nextY][nextX] = this;
                update(nextY,nextX);
            }
        }
        else{ // 다음 위치가 흰색
            Solve.chessBoard[chess.y][chess.x] = null;
            if(Solve.chessBoard[nextY][nextX] != null){ // 이미 해당위치에 쌓여있는 체스말이 있다면
                update(nextY,nextX); // 먼저 업데이트하고 붙여주자.
                Solve.chessBoard[nextY][nextX].chessMen.addAll(this.chessMen); // 해당위치에 있는 ChessMen에 이 chessMen을 붙인다.
                if(Solve.chessBoard[nextY][nextX].chessMen.size() > 3) return -2;
                return -1;
            }
            else Solve.chessBoard[nextY][nextX] = this;
            update(nextY,nextX);
        }
        return 0;
    }
    
    public void update(int nextY, int nextX){
        chessMen.get(0).y = nextY;
        chessMen.get(0).x = nextX;
        for(int i = 1; i < chessMen.size(); i++){ // 업데이트
            chessMen.get(i).y = chessMen.get(0).y;
            chessMen.get(i).x = chessMen.get(0).x;
        }
    }
    
}


class Solve{
    
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private ArrayList<ChessMen> chessMen = new ArrayList<ChessMen>();
    public static int N, K;
    public static int[][] arr = new int[15][15];
    public static ChessMen[][] chessBoard = new ChessMen[15][15];
    public static int[] dy = {0, 0, 0, -1, 1};
    public static int[] dx = {0, 1, -1, 0, 0};
    public static int[] dir = {0, 2, 1, 4, 3};
    
    public void run() throws Exception{
        input();
        System.out.println(game(1));
    }
    
    private void input() throws Exception{
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        K = Integer.parseInt(temp[1]);
        for(int i = 1; i <= N; i++){
            temp = br.readLine().split(" ");
            for(int j = 0; j < N; j++) arr[i][j+1] = Integer.parseInt(temp[j]);
        }
        ChessMen trashChessMen = new ChessMen();
        chessMen.add(trashChessMen);
        for(int i = 1; i <= K; i++){
            temp = br.readLine().split(" ");
            int y = Integer.parseInt(temp[0]);
            int x = Integer.parseInt(temp[1]);
            int dir = Integer.parseInt(temp[2]);
            Chess chess = new Chess(i,y,x,dir);
            ChessMen chessMen = new ChessMen();
            chessMen.add(chess);
            this.chessMen.add(chessMen);
            chessBoard[y][x] = chessMen;
        }
    }
    private int game(int turn){
        if(turn > 1000) return -1;
        for(int i = 1; i <= K; i++){
            ChessMen ans = null;
            for(int j = 1; j <= chessMen.size()-1; j++){
                if(chessMen.get(j).findSerial(i) == 0) ans = this.chessMen.get(j);
            }
            if(ans != null){ // 가장 아래에있다면
                int move = ans.move();
                if(move == -1){ // 움직였는데 삭제요청이 들어오면,
                    chessMen.remove(ans); // 삭제
                }
                if(move == -2) return turn; // 종료 요청이 들어오면 종료
            }
        }
        return game(turn+1);
    }
    
}
