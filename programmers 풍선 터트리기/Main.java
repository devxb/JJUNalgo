import java.util.*;

class Solution {
    
    public int solution(int[] balloonNums) {
        PriorityQueue<Integer> survivedBalloons = this.makeBalloonPriorityQue(balloonNums);
        int survivedBalloonNum = this.getSurvivableBalloonNum(balloonNums, survivedBalloons);
        return survivedBalloonNum;
    }
    
    private PriorityQueue<Integer> makeBalloonPriorityQue(int[] balloonNums){
        PriorityQueue<Integer> survivedBalloons = new PriorityQueue<Integer>();
        for(int balloonNum : balloonNums) survivedBalloons.add(balloonNum);
        return survivedBalloons;
    }
    
    private int getSurvivableBalloonNum(int[] balloonNums, PriorityQueue<Integer> survivedBalloons){
        int minBalloonIdx = this.getMinBalloonIdx(balloonNums, survivedBalloons);
        Set<Integer> alreadyCountedBalloons = new HashSet<Integer>();
        return this.killLeftToRight(minBalloonIdx, balloonNums, alreadyCountedBalloons, new PriorityQueue<Integer>(survivedBalloons)) + 
            this.killRightToLeft(minBalloonIdx, balloonNums, alreadyCountedBalloons, new PriorityQueue<Integer>(survivedBalloons));
    }
    
    private int getMinBalloonIdx(int[] balloonNums, PriorityQueue<Integer> survivedBalloons){
        int minBalloonIdx = 0;
        while(balloonNums[minBalloonIdx] != survivedBalloons.peek()) minBalloonIdx++;
        return minBalloonIdx;
    }
    
    private int killLeftToRight(int minBalloonIdx, int[] balloonNums, Set<Integer> alreadyCountedBalloons, PriorityQueue<Integer> survivedBalloons){
        Set<Integer> deadBalloons = new HashSet<Integer>();
        int count = 0;
        for(int i = minBalloonIdx; i >= 0; i--)
            count += this.kill(balloonNums[i], deadBalloons, alreadyCountedBalloons, survivedBalloons);
        for(int i = minBalloonIdx; i < balloonNums.length; i++)
            count += this.kill(balloonNums[i], deadBalloons, alreadyCountedBalloons, survivedBalloons);
        return count;
    }
    
    private int killRightToLeft(int minBalloonIdx, int[] balloonNums, Set<Integer> alreadyCountedBalloons, PriorityQueue<Integer> survivedBalloons){
        Set<Integer> deadBalloons = new HashSet<Integer>();
        int count = 0;
        for(int i = minBalloonIdx; i < balloonNums.length; i++)
            count += this.kill(balloonNums[i], deadBalloons, alreadyCountedBalloons, survivedBalloons);
        for(int i = minBalloonIdx; i >= 0; i--) 
            count += this.kill(balloonNums[i], deadBalloons, alreadyCountedBalloons, survivedBalloons);
        return count;
    }
    
    private int kill(int balloonNum, Set<Integer> deadBalloons, Set<Integer> alreadyCountedBalloons, PriorityQueue<Integer> survivedBalloons){
        deadBalloons.add(balloonNum);
        this.killBalloons(deadBalloons, survivedBalloons);
        if(alreadyCountedBalloons.contains(survivedBalloons.peek())) return 0;
        alreadyCountedBalloons.add(survivedBalloons.peek());
        return 1;
    }
    
    private void killBalloons(Set<Integer> deadBalloons, PriorityQueue<Integer> survivedBalloons){
        while(deadBalloons.contains(survivedBalloons.peek())) survivedBalloons.poll();
    }
    
}
