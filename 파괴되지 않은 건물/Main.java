class Solution {
    
    private int[][] imos;
    
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        this.initImos(board);
        this.checkImos(skill);
        this.sweepImos();
        return this.countSurvivedBuilding(board);
    }
    
    private void initImos(int[][] board){
        this.imos = new int[board.length+1][board[0].length+1];
    }
    
    private void checkImos(int[][] skill){
        for(int[] row : skill){
            boolean isAttack = (row[0] == 1 ? true : false);
            if(isAttack) row[5] *= -1;
            int startY = row[1], startX = row[2];
            int endY = row[3], endX = row[4];
            this.imos[startY][startX] += row[5];
            this.imos[startY][endX+1] += -1*row[5];
            this.imos[endY+1][startX] += -1*row[5];
            this.imos[endY+1][endX+1] += row[5];
        }
    }
    
    private void sweepImos(){
        this.sweepImosRow();
        this.sweepImosColumn();
    }
    
    private void sweepImosRow(){
        for(int y = 0; y < this.imos.length; y++)
            for(int x = 1; x < this.imos[0].length; x++) this.imos[y][x] += this.imos[y][x-1];
    }
    
    private void sweepImosColumn(){
        for(int y = 1; y < this.imos.length; y++)
            for(int x = 0; x < this.imos[0].length; x++) this.imos[y][x] += this.imos[y-1][x];
    }
    
    private int countSurvivedBuilding(int[][] board){
        board = this.sumImosOnBoard(board);
        int ans = 0;
        for(int y = 0; y < board.length; y++){
            for(int x = 0; x < board[0].length; x++){
                if(board[y][x] > 0) ans++;
            }
        }
        return ans;
    }
    
    private int[][] sumImosOnBoard(int[][] board){
        for(int y = 0; y < board.length; y++)
            for(int x = 0; x < board[0].length; x++)
                board[y][x] += imos[y][x];
        return board;
    }
    
}
