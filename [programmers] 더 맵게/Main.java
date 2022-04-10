import java.util.*;

class Solution {
    
    public int solution(int[] scoville, int targetScoville) {
        PriorityQueue<Integer> scovilles = this.convertToScovilleQueue(scoville);
        int shakeCount = 0;
        while(!this.isSatiesfiedTargetScoville(scovilles.peek(), targetScoville)){
            if(isImpossibleToMakeTargetScoville(scovilles.size())) return -1;
            int shakedScoville = this.shakeScoville(scovilles.poll(), scovilles.poll());
            scovilles.add(shakedScoville);
            shakeCount++;
        }
        return shakeCount;
    }
    
    private PriorityQueue convertToScovilleQueue(int[] scoville){
        PriorityQueue<Integer> scovilleQueue = new PriorityQueue<Integer>();
        for(int i = 0; i < scoville.length; i++) scovilleQueue.add(scoville[i]);
        return scovilleQueue;
    }
    
    private boolean isSatiesfiedTargetScoville(int scoville, int targetScoville){
        return scoville >= targetScoville ? true : false;
    }
    
    private boolean isImpossibleToMakeTargetScoville(int scovilleSize){
        return scovilleSize <= 1 ? true : false;
    }
    
    private int shakeScoville(int lowestScoville, int lowScoville){
        return lowestScoville + (lowScoville*2);
    }
    
}
