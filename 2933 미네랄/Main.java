import java.io.*;
import java.util.*;

public class Main{
    
    public static void main(String[] args) throws Exception{
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Pair{
    public int first, second;
    
    public Pair(int first, int second){
        this.first = first;
        this.second = second;
    }
}

class Solve{
    
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private int N, R, C;
    private char[][] mineral;
    private int[] dy = {-1, 0, 1, 0};
    private int[] dx = {0, -1, 0, 1};
    private int[] throwStick;
    
    public void run() throws Exception{
        input();
        boolean[][] check = findCluster();
        while(!checkCluster(check)){
            check = dropCluster(check); // 초기상태(하나도 부수지 않았을때)에서 클러스터 내리기
        }
        getMineral(1);
        stateOfMineral();
    }
    
    private void stateOfMineral(){
        for(int i = 1; i <= R; i++){
            for(int j = 1; j <= C; j++){
                System.out.print(mineral[i][j]);
            }
            System.out.println();
        }
    }
    
    private void getMineral(int turn){
        if(turn > N) return;
        crushMineral(R-throwStick[turn]+1, turn%2);
        boolean[][] check = findCluster();
        while(!checkCluster(check)){
            check = dropCluster(check);
        }
        getMineral(turn+1);
    }
    
    private void input() throws Exception{
        String[] temp = br.readLine().split(" ");
        R = Integer.parseInt(temp[0]);
        C = Integer.parseInt(temp[1]);
        mineral = new char[R+5][C+5];
        String temp2;
        for(int i = 1; i <= R; i++){
            temp2 = br.readLine();
            for(int j = 1; j <= C; j++){
                mineral[i][j] = temp2.charAt(j-1);
            }
        }
        temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        throwStick = new int[N+5];
        temp = br.readLine().split(" ");
        for(int i = 1; i <= N; i++){
            throwStick[i] = Integer.parseInt(temp[i-1]);
        }
    }
    
    private boolean[][] findCluster(){ // 분리되어있는 클러스터를 찾는다.
        boolean check[][] = new boolean[R+5][C+5];
        setCluster(check);
        return check;
    }
    
    private void crushMineral(int y, int direction){ // 미네랄 부수기
        if(direction == 1){
            for(int i = 1; i <= C; i++){
                if(mineral[y][i] == 'x'){
                    mineral[y][i] = '.';
                    break;
                }
            }
        }
        if(direction == 0){
            for(int i = C; i >= 1; i--){
                if(mineral[y][i] == 'x'){
                    mineral[y][i] = '.';
                    break;
                }
            }
        }
    } 
    
    private void setCluster(boolean[][] check){
        Queue<Pair> q = new LinkedList<Pair>();
        for(int x = 1; x <= C; x++){
            if(mineral[R][x] == 'x' && check[R][x] == false){
                q.add(new Pair(R,x));
            }
        }
        while(!q.isEmpty()){
            int nowY = q.peek().first;
            int nowX = q.peek().second;
            q.poll();
            if(check[nowY][nowX] == true) continue;
            check[nowY][nowX] = true;
            for(int i = 0; i < 4; i++){
                int nextY = nowY + dy[i];
                int nextX = nowX + dx[i];
                if(nextY < 1 || nextY > R || nextX < 1 || nextX > C || check[nextY][nextX] == true || mineral[nextY][nextX] == '.')
                    continue;
                q.add(new Pair(nextY, nextX));
            }
        }
    }
    
    private boolean checkCluster(boolean[][] check){
        for(int i = 1; i <= R; i++){
            for(int j = 1; j <= C; j++){
                if(check[i][j] == false && mineral[i][j] == 'x') return false;
            }
        }
        return true;
    }
    
    private boolean mergeCluster(boolean[][] check, boolean[][] mergeCheck, int y, int x){
        if(check[y+1][x] || y == R) return true;
        mergeCheck[y][x] = true;
        boolean ret = false;
        for(int i = 0; i < 4; i++){
            int _y = y + dy[i];
            int _x = x + dx[i];
            if(_y > R || _y < 1 || _x > C || _x < 1 || mergeCheck[_y][_x] == true || check[_y][_x] == true || mineral[_y][_x] == '.') continue;
            ret = booleanMax(mergeCluster(check, mergeCheck, _y,_x),ret);
        }
        return ret;
    }
    
    private boolean booleanMax(boolean a, boolean b){
        if(a == true) return a;
        if(b == true) return b;
        return a;
    }
    
    private boolean[][] dropCluster(boolean[][] check){ // 호출할때마다 한칸 내린다
        int firstY = 0, firstX = 0;
        for(int y = R; y >= 1; y--){
            for(int x = 1; x <= C; x++){
                if(check[y][x] == true || mineral[y][x] == '.') continue;
                if(firstY == 0 && firstX == 0){
                    firstY = y;
                    firstX = x;
                }
                mineral[y][x] = '.'; // 이동 준비
                mineral[y+1][x] = 'x'; // 이동 
            }
        }
        boolean[][] mergeCheck = new boolean[R+5][C+5];
        if(mergeCluster(check, mergeCheck, firstY, firstX)){
            check = findCluster();
        }
        return check;
    }
    
}
