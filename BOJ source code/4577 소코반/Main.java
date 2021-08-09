import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args) throws Exception{
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private String[] read;
    private int[] dy;
    private int[] dx;
    private int N, M, pY, pX;
    
    private void initial(){
        dy = new int[(int)'Z' - (int)'A' + 5];
        dx = new int[(int)'Z' - (int)'A' + 5];
        dy[(int)'U' - (int)'A'] = -1;
        dy[(int)'D' - (int)'A'] = 1;
        dx[(int)'L' - (int)'A'] = -1;
        dx[(int)'R' - (int)'A'] = 1;
    }
    
    public void run(){
        initial();
        int cnt = 1;
        while(true){
            char[][] arr = getArr();
            if(N == 0 && M == 0) break;
            String command = getCommand();
            sokobanPrinter(cnt, doSokoban(arr, pY, pX, command, 0));
            cnt++;
        }
    }
    
    private char[][] getArr(){
        try{
            read = br.readLine().split(" ");
            N = Integer.parseInt(read[0]);
            M = Integer.parseInt(read[1]);
            char[][] arr = new char[N+5][M+5];
            for(int i = 0; i < N; i++){
                read = br.readLine().split(" ");
                for(int j = 0; j < M; j++) {
                    arr[i][j] = read[0].charAt(j);
                    if(arr[i][j] == 'w' || arr[i][j] == 'W') {
                        pY = i;
                        pX = j;
                    }
                }
            }
            return arr;
        } catch (IOException IOE){}
        return null;
    }
    
    private String getCommand(){
        try{
            read = br.readLine().split(" ");
        } catch (IOException IOE){}
        return read[0];
    }
    
    private char[][] doSokoban(char[][] arr, int y, int x, String command, int commandIdx){
        if(isFinish(arr) || commandIdx >= command.length()) return arr;
        if(!moveChecker(arr, y, x, command.charAt(commandIdx))) return doSokoban(arr, y, x, command, commandIdx+1);
        move(arr, y, x, command.charAt(commandIdx));
        y += dy[command.charAt(commandIdx) - (int)'A'];
        x += dx[command.charAt(commandIdx) - (int)'A'];
        return doSokoban(arr, y, x, command, commandIdx+1);
    }
    
    private char[][] move(char[][] arr, int y, int x, char dir){
        int _y = y + dy[(int)dir - (int)'A'];
        int _x = x + dx[(int)dir - (int)'A'];
        if(arr[_y][_x] == '.' || arr[_y][_x] == '+'){
            if(targetCheck(arr, y, x)) arr[y][x] = '+';
            else arr[y][x] = '.';
            if(targetCheck(arr, _y, _x)) arr[_y][_x] = 'W';
            else arr[_y][_x] = 'w';
        }
        if(arr[_y][_x] == 'b' || arr[_y][_x] == 'B'){
            if(targetCheck(arr, y, x)) arr[y][x] = '+';
            else arr[y][x] = '.';
            if(targetCheck(arr, _y, _x)) arr[_y][_x] = 'W';
            else arr[_y][_x] = 'w';
            int __y = _y + dy[(int)dir - (int)'A'];
            int __x = _x + dx[(int)dir - (int)'A'];
            if(targetCheck(arr, __y, __x)) arr[__y][__x] = 'B';
            else arr[__y][__x] = 'b';
        }
        return arr;
    }
    
    private boolean targetCheck(char[][] arr, int y, int x){
        return (arr[y][x] >= (int)'A' && arr[y][x] <= (int)'Z') || arr[y][x] == '+';
    }
    
    private boolean isFinish(char[][] arr){
        for(int i = 0; i < N; i++)
            for(int j = 0; j < M; j++) 
                if(arr[i][j] == 'b') return false;
        return true;
    }
    
    private boolean moveChecker(char[][] arr, int y, int x, char dir){
        int _y = y + dy[(int)dir - (int)'A'];
        int _x = x + dx[(int)dir - (int)'A'];
        if(!outOfIndex(_y, _x) && arr[_y][_x] != 'b' && arr[_y][_x] != 'B' && arr[_y][_x] != '#') return true;
        if(!outOfIndex(_y, _x) && arr[_y][_x] == 'b' || arr[_y][_x] == 'B'){ // 다음 위치가 박스인경우
            int __y = _y + dy[(int)dir - (int)'A'];
            int __x = _x + dx[(int)dir - (int)'A'];
            if(outOfIndex(__y, __x) || arr[__y][__x] == 'b' || arr[__y][__x] == 'B' || arr[__y][__x] == '#') return false; // 이동위치가 박스거나 벽이라면 false
            return true;
        }
        return false;
    }
    
    private boolean outOfIndex(int y, int x){
        return (y < 0 || x < 0 || y >= N || x >= M);
    }
    
    private void sokobanPrinter(int game, char[][] result){
        if(isFinish(result)) System.out.println("Game" + " " + game + ": complete");
        else System.out.println("Game" + " " + game + ": incomplete");
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++) System.out.print(result[i][j]);
            System.out.println();
        }
    }
    
}
