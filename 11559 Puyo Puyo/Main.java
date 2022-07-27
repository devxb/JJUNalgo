import java.io.*;
import java.nio.Buffer;
import java.util.*;

import static java.lang.Math.*;

public class Main {

    private char[][] field = new char[12][6];
    private static int[] dy = {-1, 1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args){
        (new Main()).solve();
    }

    private void solve(){
        this.input();
        System.out.println(this.getBombedCount());
    }

    private void input(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            this.inputField(br);
        }catch(IOException IOE){}
    }

    private void inputField(BufferedReader br) throws IOException{
        for(int y = 0; y < 12; y++){
            String read = br.readLine();
            for(int x = 0; x < 6; x++) this.field[y][x] = read.charAt(x);
        }
    }

    private int getBombedCount(){
        int bombedCount = 0;
        while(this.bomb()) {
            bombedCount++;
            this.drop();
        }
        return bombedCount;
    }

    private boolean bomb(){
        int[][] bombCheck = new int[12][6]; // bombCheck : 0 = 미확인, 1 = 확인
        boolean isBombable = false;
        for(int y = 0; y < 12; y++){
            for(int x = 0; x < 6; x++){
                if(bombCheck[y][x] != 0 || this.field[y][x] == '.') continue;
                if(this.checkChaine(y, x, this.field[y][x], bombCheck) <= 3) continue;
                isBombable = true;
                this.doBomb(y, x, this.field[y][x]);
            }
        }
        return isBombable;
    }

    private int checkChaine(int y, int x, char color, int[][] bombCheck){
        bombCheck[y][x] = 1;
        int cnt = 1;
        for(int d = 0; d < 4; d++){
            int nextY = y + dy[d];
            int nextX = x + dx[d];
            if(this.isOutOfBound(nextY, nextX) || bombCheck[nextY][nextX] != 0 || this.field[nextY][nextX] != color) continue;
            cnt += this.checkChaine(nextY, nextX, color, bombCheck);
        }
        return cnt;
    }

    private void doBomb(int y, int x, char color){
        this.field[y][x] = '.';
        for(int d = 0; d < 4; d++){
            int nextY = y + dy[d];
            int nextX = x + dx[d];
            if(this.isOutOfBound(nextY, nextX) || this.field[nextY][nextX] != color) continue;
            this.doBomb(nextY, nextX, color);
        }
    }

    private void drop(){
        for(int y = 11; y >= 0; y--){
            for(int x = 0; x < 6; x++){
                if(field[y][x] == '.') continue;
                int dropY = y+1, dropX = x;
                while(!this.isOutOfBound(dropY, dropX) && field[dropY][dropX] == '.'){
                    field[dropY][x] = field[dropY-1][x];
                    field[dropY-1][x] = '.';
                    dropY++;
                }
            }
        }
    }

    private boolean isOutOfBound(int y, int x){
        return y < 0 || x < 0 || y > 11 || x > 5;
    }

}
