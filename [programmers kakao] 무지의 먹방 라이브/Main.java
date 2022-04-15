import java.util.*;

class Solution {
    
    public int solution(int[] foodTimes, long k) {
        if(!this.isEatAble(foodTimes, k)) return -1;
        Queue<Integer> foodCycle = this.makeFoodcycle(foodTimes);
        return this.findKIdxFood(foodTimes, foodCycle, k);
    }
    
    private boolean isEatAble(int[] foodTimes, long k){
        long eatTime = 0L;
        for(int foodTime : foodTimes) eatTime = eatTime + foodTime;
        return eatTime > k ? true : false;
    }
    
    private Queue<Integer> makeFoodcycle(int[] foodTimes){
        int[] sortedFoodTimes = Arrays.copyOf(foodTimes, foodTimes.length);
        Arrays.sort(sortedFoodTimes);
        Queue<Integer> foodCycle = new LinkedList<Integer>();
        for(int foodTime : sortedFoodTimes) foodCycle.add(foodTime); 
        return foodCycle;
    }
    
    private int findKIdxFood(int[] foodTimes, Queue<Integer> foodCycle, long k){
        k = this.optimizeK(foodTimes, foodCycle, k);
        int skip = 0;
        while(k > 0){
            while(foodTimes[skip] <= 0) skip = this.cyclePlus(foodTimes, skip);
            k--;
            skip = this.cyclePlus(foodTimes, skip);
        }
        while(foodTimes[skip] <= 0) skip = this.cyclePlus(foodTimes, skip);
        return (skip+1 > foodTimes.length) ? 1 : skip+1;
    }
    
    private long optimizeK(int[] foodTimes, Queue<Integer> foodCycle, long k){
        int cycleCnt = 0;
        while(k >= foodCycle.size()){
            k -= foodCycle.size();
            cycleCnt++;
            this.deleteFoodCycle(foodCycle, cycleCnt);
        }
        this.deleteFoodTimes(foodTimes, cycleCnt);
        return k;
    }
    
    private void deleteFoodCycle(Queue<Integer> foodCycle, int cycleCnt){
        while(foodCycle.peek() <= cycleCnt) foodCycle.poll();
    }
    
    private void deleteFoodTimes(int[] foodTimes, int cycleCnt){
        for(int i = 0; i < foodTimes.length; i++) foodTimes[i] -= cycleCnt;
    }
    
    private int cyclePlus(int[] foodTimes, int skip){
        return (skip+1 == foodTimes.length) ? 0 : skip+1;
    }
    
}
