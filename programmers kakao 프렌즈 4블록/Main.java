import java.util.*;

class Solution {
    
    public int solution(int m, int n, String[] boardString) {
        char[][] board = new char[m][n];
        this.makeBoard(board, boardString);
        while(true){
            List<Mark> marks = this.marking4Blocks(m, n, board);
            if(marks.size() == 0) break;
            this.eraseBlocks(board, marks);
            this.dropBlocks(m, n, board);
        }
        int eraseBlockCount = this.countEraseBlocks(m, n, board);
        return eraseBlockCount;
    }

    private void makeBoard(char[][] board, String[] boardString){
        for(int i = 0; i < boardString.length; i++){
            String row = boardString[i];
            for(int j = 0; j < row.length(); j++) board[i][j] = row.charAt(j);
        }
    }
    
    private List<Mark> marking4Blocks(int m, int n, char[][] board){
        List<Mark> marks = new ArrayList<Mark>();
        for(int i = 0; i < m-1; i++){
            for(int j = 0; j < n-1; j++){
                if(this.isMarkable(i, j, board)) marks.add(new Mark(i,j));
            }
        }
        return marks;
    }
        
    private boolean isMarkable(int y, int x, char[][] board){
        if(board[y][x] == '0') return false;
        return ((board[y][x] == board[y+1][x]) 
                && (board[y+1][x] == board[y+1][x+1])
                && (board[y+1][x+1] == board[y][x+1]));
    }
    
    private void eraseBlocks(char[][] board, List<Mark> marks){
        for(Mark mark : marks) this.erase(board, mark);
    }
    
    private void erase(char[][] board, Mark mark){
        board[mark.y][mark.x] = '0';
        board[mark.y+1][mark.x] = '0';
        board[mark.y][mark.x+1] = '0';
        board[mark.y+1][mark.x+1] = '0';
    }
    
    private void dropBlocks(int m, int n, char[][] board){
        for(int i = m-1; i >= 0; i--){
            for(int j = 0; j < n; j++){
                int dropY = i;
                while(dropY+1 < m && board[dropY+1][j] == '0'){
                    board[dropY+1][j] = board[dropY][j];
                    board[dropY][j] = '0';
                    dropY++;
                }
            }
        }
    }
    
    private int countEraseBlocks(int m, int n, char[][] board){
        int cnt = 0;
        for(int i = 0; i < m; i++)
            for(int j = 0; j < n; j++) if(board[i][j] == '0') cnt++;
        return cnt;
    }
    
    private static class Mark{
        
        private int y, x;
        
        public Mark(int y, int x){
            this.y = y;
            this.x = x;
        }
        
    }
    
}
