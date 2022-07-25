import java.io.*;
import java.util.*;

public class Main {

    private int height, width;
    private int[][] area;
    private int[][] check;
    private static final int[] dy = {-1, 0, 1, 0};
    private static final int[] dx = {0, -1, 0, 1};

    public static void main(String[] args){
        (new Main()).solve();
    }

    private void solve(){
        this.input();
        int cnt = 0, trig = 1;
        for(int y = 0; y < this.height; y++)
            for (int x = 0; x < this.width; x++)
                if(check[y][x] == 0) {
                    cnt += this.checkCycle(y, x, trig);
                    trig++;
                }
        System.out.println(cnt);
    }

    private void input(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            this.inputArea(br);
        }catch(IOException IOE){}
    }

    private void inputArea(BufferedReader br) throws IOException{
        String[] read = br.readLine().split(" ");
        this.height = Integer.parseInt(read[0]);
        this.width = Integer.parseInt(read[1]);
        this.area = new int[height][width];
        this.check = new int[height][width];
        for(int y = 0; y < this.height; y++) {
            String line = br.readLine();
            for(int x = 0; x < this.width; x++) this.area[y][x] = this.convertCharToInt(line.charAt(x));
        }
    }

    private int convertCharToInt(char c){
        if(c == 'U') return 0;
        if(c == 'L') return 1;
        if(c == 'D') return 2;
        return 3;
    }

    private int checkCycle(int y, int x, int trig){
        if(check[y][x] == trig) return 1;
        if(check[y][x] > 0) return 0;
        this.check[y][x] = trig;
        int nextY = y + this.dy[area[y][x]];
        int nextX = x + this.dx[area[y][x]];
        return this.checkCycle(nextY, nextX, trig);
    }

}
