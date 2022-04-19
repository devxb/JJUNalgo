import java.util.*;
import static java.lang.Math.min;

class Solution {
    
    public int solution(int[][] board) {
        Jordy jordy = new Jordy(0, 0);
        int[][][] moneyCheck = new int[board.length][board.length][4];
        this.initMoneyCheck(moneyCheck);
        return this.getMinimumMoney(jordy, board, moneyCheck);
    }
    
    private void initMoneyCheck(int[][][] moneyCheck){
        for(int i = 0; i < moneyCheck.length; i++)
            for(int j = 0; j < moneyCheck.length; j++) 
                for(int l = 0; l < 4; l++) moneyCheck[i][j][l] = 987654321;
    }
    
    private int getMinimumMoney(Jordy jordy, int[][] board, int[][][] moneyCheck){
        PriorityQueue<Jordy> jordies = new PriorityQueue<Jordy>();
        jordies.add(jordy);
        while(!jordies.isEmpty()){
            Jordy nowJordy = jordies.poll();
            if(nowJordy.isJordyOnWall(board) || nowJordy.isExpensiveCase(moneyCheck)) continue;
            for(int i = 0; i < 4; i++){
                Jordy nextJordy = nowJordy.move(i);
                if(nextJordy.isOutOfBounds(board.length)) continue;
                jordies.add(nextJordy);
            }
        }
        return min(
            min(moneyCheck[moneyCheck.length-1][moneyCheck.length-1][0], moneyCheck[moneyCheck.length-1][moneyCheck.length-1][1])
            , min(moneyCheck[moneyCheck.length-1][moneyCheck.length-1][2], moneyCheck[moneyCheck.length-1][moneyCheck.length-1][3])
               );
    }
    
}

class Jordy implements Comparable<Jordy>{
    
    private int y, x;
    private int dir;
    private int money;
    private static final int[] dy = {-1, 0, 1, 0};
    private static final int[] dx = {0, -1, 0, 1};
    private static final int corner = 500;
    private static final int straight = 100;
    
    public Jordy(int y, int x){
        this.y = y;
        this.x = x;
        this.dir = -1;
        this.money = 0;
    }
    
    public Jordy(int y, int x, int dir, int money){
        this(y, x);
        this.dir = dir;
        this.money = money;
    }
    
    public boolean isJordyOnWall(int[][] board){
        return board[this.y][this.x] == 1;
    }
    
    public boolean isExpensiveCase(int[][][] moneyCheck){
        if(this.dir == -1) return false;
        if(this.money > moneyCheck[this.y][this.x][this.dir]) return true;
        moneyCheck[this.y][this.x][this.dir] = this.money;
        return false;
    }
    
    public boolean isOutOfBounds(int bound){
        if(this.y >= bound || this.x >= bound || this.y < 0 || this.x < 0) return true;
        return false;
    }
    
    public Jordy move(int dir){
        return new Jordy(this.moveY(dir), this.moveX(dir), dir, this.addMoney(dir));
    }
    
    private int moveY(int dir){
        return this.y + this.dy[dir];
    }
    
    private int moveX(int dir){
        return this.x + this.dx[dir];
    }
    
    private int addMoney(int dir){
        if(this.dir == -1 || this.dir == dir) return this.money + this.straight;
        return this.money + this.corner + this.straight;
    }
    
    public int getY(){
        return this.y;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getMoney(){
        return this.money;
    }
    
    @Override
    public int compareTo(Jordy jordy){
        if(this.money > jordy.getMoney()) return 1;
        if(this.money < jordy.getMoney()) return -1;
        return 0;
    }
    
}
