import java.io.*;

public class Main{

    private int[][] triangle;

    public static void main(String[] args){
        (new Main()).solve();
    }

    private void solve(){
        input();
        sweepImos();
        System.out.println(countCatchedTriangle());
    }

    private void input(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            checkImos(br);
        }catch(IOException IOE){}
    }

    private void checkImos(BufferedReader br) throws IOException{
        String[] read = br.readLine().split(" ");
        int triangleSize = Integer.parseInt(read[0]);
        triangle = new int[triangleSize+3][triangleSize+3];
        int queryCount = Integer.parseInt(read[1]);
        for(int i = 0; i < queryCount; i++){
            read = br.readLine().split(" ");
            int y = Integer.parseInt(read[0]);
            int x = Integer.parseInt(read[1]);
            int width = Integer.parseInt(read[2]);
            checkImos(y, x, width);
        }
    }

    private void checkImos(int y, int x, int width){
        triangle[y][x] += 1;
        triangle[y][x+1] += -1;
        triangle[y+width+1][x] += -1;
        triangle[y+width+1][x+width+2] += 1;
        triangle[y+width+2][x+1] += 1;
        triangle[y+width+2][x+width+2] += -1;
    }

    private void sweepImos(){
        sweepLeft();
        sweepDown();
        sweepDiagonal();
    }

    private void sweepLeft(){
        sweep(0, 1);
    }

    private void sweepDown(){
        sweep(1, 0);
    }

    private void sweepDiagonal(){
        sweep(1, 1);
    }

    private void sweep(int yDiss, int xDiss){
        for(int i = 1; i < triangle.length; i++)
            for(int j = 1; j < triangle.length; j++) triangle[i][j] += triangle[i-yDiss][j-xDiss];
    }

    private int countCatchedTriangle(){
        int catched = 0;
        for(int i = 1; i < triangle.length; i++)
            for(int j = 1; j < triangle.length; j++)
                if(triangle[i][j] >= 1) catched++;
        return catched;
    }

}
