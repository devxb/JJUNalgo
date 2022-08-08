import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Main{

    private static final int INF = 987654321;
    private int doors;
    private int[] firstOpendDoors = new int[2];
    private int[][][] dp;
    private int[] queries;

    public static void main(String[] args){
        (new Main()).solve();
    }

    private void solve(){
        this.input();
        this.initDp();
        this.openDoors(1);
        System.out.println(this.getMinOpenCount());
    }

    private void input(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            this.inputDoor(br);
            this.inputQueries(br);
        }catch(IOException IOE){}
    }

    private void inputDoor(BufferedReader br) throws IOException{
        this.doors = Integer.parseInt(br.readLine());
        String[] line = br.readLine().split(" ");
        this.firstOpendDoors[0] = Integer.parseInt(line[0]);
        this.firstOpendDoors[1] = Integer.parseInt(line[1]);
    }

    private void inputQueries(BufferedReader br) throws IOException{
        int queryCount = Integer.parseInt(br.readLine());
        this.queries = new int[queryCount+1];
        this.dp = new int[doors+1][doors+1][21];
        for(int i = 1; i <= queryCount; i++) this.queries[i] = Integer.parseInt(br.readLine());
    }

    private void initDp() {
        for (int door1 = 1; door1 <= this.doors; door1++)
            for (int door2 = 1; door2 <= this.doors; door2++)
                for (int idx = 0; idx <= 20; idx++) dp[door1][door2][idx] = this.INF;
        dp[this.firstOpendDoors[0]][this.firstOpendDoors[1]][0] = 0;
    }


    private void openDoors(int queryIdx){
        if(queryIdx >= queries.length) return;
        int needToOpen = this.queries[queryIdx];
        for (int door1 = 1; door1 <= this.doors; door1++) {
            for (int door2 = door1+1; door2 <= this.doors; door2++) {
                dp[needToOpen][door2][queryIdx] = min(
                        dp[needToOpen][door2][queryIdx],
                        dp[door1][door2][queryIdx-1] + abs(needToOpen - door1)
                );
                dp[door1][needToOpen][queryIdx] = min(
                        dp[door1][needToOpen][queryIdx],
                        dp[door1][door2][queryIdx-1] + abs(needToOpen - door2)
                );
            }
        }
        openDoors(queryIdx+1);
    }

    private int getMinOpenCount(){
        int openCount = this.INF;
        for (int door1 = 1; door1 <= this.doors; door1++)
            for (int door2 = door1+1; door2 <= this.doors; door2++)
                openCount = min(openCount, dp[door1][door2][queries.length-1]);
        return openCount;
    }

}
