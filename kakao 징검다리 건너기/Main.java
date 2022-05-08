import java.util.*;
import static java.lang.Math.max;

class Solution {

    public int solution(int[] stones, int k) {
        int left = 0, right = 2000000000;
        int maximumCrossCount = 0;
        for(int i = 0; i < 64; i++){
            int crossCount = (left + right) / 2;
            if(this.isCrossable(k, crossCount, stones)){
                maximumCrossCount = max(maximumCrossCount, crossCount);
                left = crossCount+1;
            }
            else right = crossCount-1;
        }
        return maximumCrossCount;
    }

    private boolean isCrossable(int maximumJump, int crossCount, int[] stones){
        crossCount -= 1;
        int continuosBrokenStones = 0;
        for(int stone : stones){
            if(stone - crossCount <= 0) continuosBrokenStones++;
            else continuosBrokenStones = 0;
            if(continuosBrokenStones == maximumJump) return false;
        }
        return true;
    }

}
