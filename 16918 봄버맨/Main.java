import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args){
        Solve solve = new Solve();
        solve.run();
    }
    
}


class Solve{
    
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private int[][] bomb;
    private int R, C, N;
    
    public void run(){
        input();
        simulator(2);
        for(int i = 1; i <= R; i++){
            for(int j = 1; j <= C; j++){
                if(bomb[i][j] == -1) System.out.print('.');
                else System.out.print('O');
            }
            System.out.println();
        }
    }
    
    private void input(){
        try{
            String[] read = br.readLine().split(" ");
            R = Integer.parseInt(read[0]);
            C = Integer.parseInt(read[1]);
            N = Integer.parseInt(read[2]);
            bomb = new int[R+5][C+5];
            for(int i = 1; i <= R; i++){
                read = br.readLine().split(" ");
                for(int j = 1; j <= C; j++) {
                    if(read[0].charAt(j-1) == '.') bomb[i][j] = -1;
                    else bomb[i][j] = 0;
                }
            }
        } catch (IOException ioe){}
    }
    
    private void simulator(int sec){
        if(sec > N) return;
        setBomb(sec);
        explode(sec);
        if(sec == N) return;
        simulator(sec+1);
    }
    
    private void setBomb(int sec){
        for(int i = 1; i <= R; i++){
            for(int j = 1; j <= C; j++){
                if(bomb[i][j] == -1) bomb[i][j] = sec;
            }
        }
    }
    
    private void explode(int sec){
        int temp[][] = new int[R+5][C+5];
        for(int i = 1; i <= R; i++){
            for(int j =1; j <= C; j++){
                if(bomb[i][j] != -1 && bomb[i][j]+3 == sec) {
                    temp[i][j] = -1;
                    temp[i-1][j] = -1;
                    temp[i+1][j] = -1;
                    temp[i][j-1]= -1;
                    temp[i][j+1] = -1;
                }
            }
        }
        for(int i = 1; i <= R; i++){
            for(int j =1; j <= C; j++){
                if(temp[i][j] == -1) bomb[i][j] = -1;
            }
        }
    }
    
}
