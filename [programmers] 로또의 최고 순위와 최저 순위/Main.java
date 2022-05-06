import java.util.*;

class Solution {
    
    public int[] solution(int[] lottos, int[] winNums) {
        int matchedCount = this.countMatched(lottos, winNums);
        int zeroLotto = this.countZeroLotto(lottos);
        return this.predictHigherAndLower(matchedCount, zeroLotto);
    }
    
    private int countMatched(int[] lottos, int[] winNums){
        int matchedCount = 0;
        for(int lotto : lottos){
            if(lotto == 0) continue;
            for(int winNum : winNums) if(lotto == winNum) matchedCount++;
        }
        return matchedCount;
    }
    
    private int countZeroLotto(int[] lottos){
        int zeroLotto = 0;
        for(int lotto : lottos)
            if(lotto == 0) zeroLotto++;
        return zeroLotto;
    }
    
    private int[] predictHigherAndLower(int matchedCount, int zeroLotto){
        int[] higherLower = new int[2];
        higherLower[1] = this.predictLower(matchedCount, zeroLotto);
        higherLower[0] = this.predictHigher(matchedCount, zeroLotto);
        return higherLower;
    }
    
    private int predictLower(int matchedCount, int zeroLotto){
        return Math.min(7-matchedCount, 6);
    }
    
    private int predictHigher(int matchedCount, int zeroLotto){
        return Math.min(7-(matchedCount+zeroLotto), 6);
    }
    
}
