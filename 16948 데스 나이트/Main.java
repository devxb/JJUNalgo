import java.io.*;

public class Main{

    private final static int INF = 987654321;
    private int startY, startX;
    private int endY, endX;
    private final int[] moveY = {-2, -2, 0, 0, 2, 2};
    private final int[] moveX = {-1, 1, -2, 2, -1, 1};
    private int[][] dp;

    public static void main(String[] args){
        (new Main()).solve();
    }

    private void solve(){
        this.input();
        this.moveDeathKnight(startY, startX, 0);
        System.out.println(dp[endY][endX] == Main.INF ? -1 : dp[endY][endX]);
    }

    private void input(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            this.inputChessBoard(br);
        }catch(IOException IOE){}
    }

    private void inputChessBoard(BufferedReader br) throws IOException{
        int boardSize = Integer.parseInt(br.readLine());
        this.dp = this.initDp(new int[boardSize][boardSize]);
        String[] read = br.readLine().split(" ");
        this.startY = Integer.parseInt(read[0]);
        this.startX = Integer.parseInt(read[1]);
        this.endY = Integer.parseInt(read[2]);
        this.endX = Integer.parseInt(read[3]);
    }

    private int[][] initDp(int[][] dp){
        for(int i = 0; i < dp.length; i++)
            for(int j = 0; j < dp[i].length; j++) dp[i][j] = Main.INF;
        return dp;
    }

    private void moveDeathKnight(int y, int x, int moveCount){
        if(this.dp[y][x] <= moveCount) return;
        this.dp[y][x] = moveCount;
        for(int i = 0; i < 6; i++) {
            int nextY = y + this.moveY[i];
            int nextX = x + this.moveX[i];
            if(this.isOutOfBounds(nextY, nextX)) continue;
            this.moveDeathKnight(nextY, nextX, moveCount + 1);
        }
    }

    private boolean isOutOfBounds(int y, int x){
        return y < 0 || x < 0 || y >= this.dp.length || x >= this.dp.length;
    }

}
