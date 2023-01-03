import java.io.*;

public class Main{

    private int N;
    private String balls;

    public static void main(String[] args){
        (new Main()).solve();
    }

    private void solve(){
        input();
        System.out.println(Math.min(getBallMoveCount('B'), getBallMoveCount('R')));
    }

    private void input(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            N = Integer.parseInt(br.readLine());
            balls = br.readLine();
        }catch(IOException ignored){}
    }

    private int getBallMoveCount(char color){
        return Math.min(moveBallToFront(color), moveBallToBack(color));
    }

    private int moveBallToFront(char color){
        int idx = 0;
        int moveCount = 0;
        while(idx < balls.length() && balls.charAt(idx) == color) idx++;
        for(; idx < balls.length(); idx++)
            if(balls.charAt(idx) == color) moveCount++;
        return moveCount;
    }

    private int moveBallToBack(char color){
        int idx = balls.length()-1;
        int moveCount = 0;
        while(idx >= 0 && balls.charAt(idx) == color) idx--;
        for(; idx >= 0; idx--)
            if(balls.charAt(idx) == color) moveCount++;
        return moveCount;
    }

}
