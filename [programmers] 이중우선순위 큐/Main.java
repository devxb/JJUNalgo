import java.util.*;

class Solution {
    
    private static class Num implements Comparable<Num>{
        
        private final int initIdx;
        private final int number;
        
        public Num(int initIdx, int number){
            this.initIdx = initIdx;
            this.number = number;
        }
        
        @Override
        public int compareTo(Num num){
            if(this.number < num.getNumber()) return -1;
            if(this.number > num.getNumber()) return 1;
            return 0;
        }
        
        int getNumber(){
            return this.number;
        }
        
    }
    
    public int[] solution(String[] operations) {
        PriorityQueue<Num> minHeap = new PriorityQueue<Num>();
        PriorityQueue<Num> maxHeap = new PriorityQueue<Num>(Comparator.reverseOrder());
        boolean[] deletedNum = new boolean[operations.length];
        for(int i = 0; i < operations.length; i++){
            String[] orders = operations[i].split(" ");
            String operation = orders[0];
            if(operation.equals("I")){
                Num num = new Num(i, Integer.parseInt(orders[1]));
                minHeap.add(num);
                maxHeap.add(num);
                continue;
            }
            if(operation.equals("D")){
                if(orders[1].equals("1") && !maxHeap.isEmpty()) this.deleteNum(deletedNum, maxHeap);
                else if(orders[1].equals("-1") && !minHeap.isEmpty()) this.deleteNum(deletedNum, minHeap);
            }
        }
        this.pollDeletedNum(deletedNum, minHeap);
        this.pollDeletedNum(deletedNum, maxHeap);
        int[] ans = {this.pollOrElseZero(maxHeap), this.pollOrElseZero(minHeap)};
        return ans;
    }
    
    private void deleteNum(boolean[] deletedNum, PriorityQueue<Num> heap){
         this.pollDeletedNum(deletedNum, heap);
         if(heap.isEmpty()) return;
         Num num = heap.poll();
         deletedNum[num.initIdx] = true;
    }
    
    private void pollDeletedNum(boolean[] deletedNum, PriorityQueue<Num> heap){
        while(!heap.isEmpty() && deletedNum[heap.peek().initIdx]) heap.poll();
    }
    
    private int pollOrElseZero(PriorityQueue<Num> heap){
        return (heap.isEmpty()) ? 0 : heap.poll().number;
    }
    
}
