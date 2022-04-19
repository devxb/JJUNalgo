import java.util.*;

class Solution {
    
    public boolean solution(int[][] key, int[][] lock) {
        if(this.isEmptyKey(key)){
            if(this.isFullLock(lock)) return true;
            return false;
        }
        for(int i = 0; i < 4; i++){
            if(this.isOpen(key, lock)) return true;
            key = this.turn(key);
        }
        return false;
    }
    
    private boolean isEmptyKey(int[][] key){
        for(int i = 0; i < key.length; i++)
            for(int j = 0; j < key.length; j++) if(key[i][j] == 1) return false;
        return true;
    }
    
    private boolean isFullLock(int[][] lock){
        for(int i = 0; i < lock.length; i++)
            for(int j = 0; j < lock.length; j++) if(lock[i][j] == 0) return false;
        return true;
    }
    
    private boolean isOpen(int[][] key, int[][] lock){
        for(int relY = -1*lock.length; relY < lock.length; relY++){
            for(int relX = -1*lock.length; relX < lock.length; relX++){
                boolean[][] locked = new boolean[lock.length][lock.length];
                if(this.lock(relY, relX, key, lock, locked)) 
                    if(this.isAllOpened(lock, locked)) return true;
            }
        }
        return false;
    }
    
    private boolean lock(int relY, int relX, int[][] key, int[][] lock, boolean[][] locked){
        for(int i = 0; i < key.length; i++){
            for(int j = 0; j < key.length; j++){
                if(i+relY < 0 || i+relY >= lock.length
                  || j+relX < 0 || j+relX >= lock.length
                  || key[i][j] == 0) continue;
                if(lock[i+relY][j+relX] == 1) return false; 
                if(lock[i+relY][j+relX] == 0) locked[i+relY][j+relX] = true;
            }
        }
        return true;
    }
    
    private boolean isAllOpened(int[][] lock, boolean[][] locked){
        for(int i = 0; i < locked.length; i++)
            for(int j = 0; j < locked.length; j++) if(lock[i][j] == 0 && !locked[i][j]) return false;
        return true;
    }
    
    private int[][] turn(int[][] array){
        int[][] turned = new int[array.length][array.length];
        for(int i = 0; i < array.length; i++){
            int turnedPos = array.length-1;
            for(int j = 0; j < array.length; j++) turned[i][j] = array[turnedPos-j][i];
        }
        return turned;
    }
    
}
