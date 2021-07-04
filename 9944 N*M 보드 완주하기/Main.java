import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    class Pos{
        public int y, x, dir;
        
        public Pos(int y, int x, int dir){
            this.y = y;
            this.x = x;
            this.dir = dir;
        }
    }
    
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private String[] read;
    private char[][] arr;
    private int N, M, cnt;
    private int[] dy = {-1, 0, 1, 0};
    private int[] dx = {0, -1, 0, 1};
    private boolean[][] check;
    private int ans = 987654321;
    
    public void run() throws IOException{
        int i = 1;
        String tempRead = null;
        while((tempRead = br.readLine()) != null){
            this.ans = 987654321;
            cnt = 0;
            input(tempRead.split(" "));
            simulator();
            if(ans == 987654321) ans = -1;
            if(cnt == 1) ans = 0;
            System.out.println("Case " + i + ": " + ans);
            i++;
        }
    }
    
    private void input(String[] read) throws IOException{
        N = Integer.parseInt(read[0]);
        M = Integer.parseInt(read[1]);
        arr = new char[N+5][M+5];
        check = new boolean[N+5][M+5];
        for(int i = 0; i < N; i++){
            read = br.readLine().split(" ");
            for(int j = 0; j < read[0].length(); j++){
                arr[i][j] = read[0].charAt(j);
                if(arr[i][j] == '.') cnt++;
            }
        }
    }
    
    private void simulator(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(arr[i][j] == '*') continue;
                for(int l = 0; l < 4; l++){
                    check[i][j] = true;
                    ans = Math.min(ans,move(new Pos(i, j, l), 1, 1));
                    check[i][j] = false;
                }
            }
        }
    }
    
    private int move(Pos pos, int cnt, int moveNum){
        if(pos.y < 0 || pos.y >= N || pos.x < 0 || pos.x >= M) return 987654321;
        if(cnt == this.cnt || moveNum >= ans) return moveNum;
        int ret = ans;
        int headY = pos.y+dy[pos.dir];
        int headX = pos.x+dx[pos.dir];
        if(headY < 0 || headY >= N || headX < 0 || headX >= M || check[headY][headX] || arr[headY][headX] == '*'){
            for(int i = 0; i < 4; i++){
                if(pos.dir == i) continue;
                int nextY = pos.y + dy[i];
                int nextX = pos.x + dx[i];
                int nextMoveNum = moveNum+1;
                if(nextY < 0 || nextY >= N || nextX < 0 || nextX >= M || check[nextY][nextX] || arr[nextY][nextX] == '*')
                    continue;
                check[nextY][nextX] = true;
                ret = Math.min(ret, move(new Pos(nextY, nextX, i), cnt+1, nextMoveNum));
                check[nextY][nextX] = false;
            }
        }
        else if(headY >= 0 && headY < N && headX >= 0 && headX < M ){
            check[headY][headX] = true;
            ret = Math.min(ret, move(new Pos(headY, headX, pos.dir), cnt+1, moveNum));
            check[headY][headX] = false;
        }
        return ret;
    }
    
}
