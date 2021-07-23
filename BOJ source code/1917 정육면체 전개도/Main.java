import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args) throws Exception{
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    private static class Cube{
        
        private int[] side;
        private int[] temp;
        private int foldCount;
         
        public Cube(){
            temp = new int[7];
            side = new int[7];
        }
        
        public boolean cubeCheck(){
            if(foldCount > 6) return false;
            for(int i = 1; i <= 6; i++) if(side[i] == 0) return false;
            return true;
        }
        
        public void setCube(int side){
            foldCount++;
            this.side[1] = side;
        }
        
        public void changeSide(int dir){
            if(dir == 1) doLeft();
            else if(dir == 2) doRight();
            else if(dir == 3) doDown();
            else if(dir == 4) doTop();
            tempToSide();
        }
        
        // 모든 변경 위치 초기화
        
        private void sideToTemp(){
            for(int i = 0; i <= 6; i++) temp[i] = side[i];
        }
        
        private void tempToSide(){
            for(int i = 0; i <= 6; i++) side[i] = temp[i];
        }
        
        private void doDown(){
            temp[1] = side[4];
            temp[4] = side[6];
            temp[5] = side[1];
            temp[6] = side[5];
            temp[2] = side[2];
            temp[3] = side[3];
        }
        
        private void doTop(){
            temp[1] = side[5];
            temp[4] = side[1];
            temp[5] = side[6];
            temp[6] = side[4];
            temp[2] = side[2];
            temp[3] = side[3];
        }
        
        private void doRight(){
            temp[1] = side[2];
            temp[2] = side[6];
            temp[3] = side[1];
            temp[6] = side[3];
            temp[4] = side[4];
            temp[5] = side[5];
        }
        
        private void doLeft(){
            temp[1] = side[3];
            temp[2] = side[1];
            temp[3] = side[6];
            temp[6] = side[2];
            temp[4] = side[4];
            temp[5] = side[5];
        }
        
    }
    
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private String[] read;
    private int[] dy = {0, 0, 0, 1, -1};
    private int[] dx = {0, 1, -1, 0, 0};
    private int[] reverseDir = {0, 2, 1, 4, 3};
    private boolean[][] check;
    private int side;
        
    public void run(){
        for(int i = 0; i < 3; i++) System.out.println(validateCube(getArr()));
    }
    
    private String validateCube(int[][] arr){
        int sy = 0, sx = 0;
        for(int y = 0; y < 6; y++){
            for(int x = 0; x < 6; x++){
                if(arr[y][x] == 1){
                    sy = y;
                    sx = x;
                    break;
                }
            }
        }
        Solve.Cube cube = new Solve.Cube();
        validateCubeOperate(arr, cube, sy, sx);
        if(!cube.cubeCheck()) return "no";
        return "yes";
    }
    
    private void validateCubeOperate(int[][] arr, Cube cube, int y, int x){
        side++;
        cube.setCube(side);
        check[y][x] = true;
        for(int i = 1; i <= 4; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(outOfBounds(ny, nx) || check[ny][nx] || arr[ny][nx] == 0) continue;
            cube.changeSide(i); // 정방향
            validateCubeOperate(arr, cube, ny, nx);
            cube.changeSide(reverseDir[i]); // 역방향으로 되돌림
        }
    }
    
    private boolean outOfBounds(int y, int x){
        return (y >= 6 || x >= 6 || y < 0 || x < 0);
    }
    
    private int[][] getArr(){
        int[][] ret = new int[10][10];
        check = new boolean[10][10];
        for(int i = 0; i < 6; i++){
            try{
                read = br.readLine().split(" ");
            } catch (IOException IOE) {}
            for(int j = 0; j < 6; j++) ret[i][j] = Integer.parseInt(read[j]);
        }
        return ret;
    }
    
}
