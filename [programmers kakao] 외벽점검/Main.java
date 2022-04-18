import java.util.*;

class Solution {
    
    public int solution(int n, int[] weak, int[] dist) {
        Arrays.sort(dist);
        List<Integer> workers = new ArrayList<Integer>();
        int[] repaired = new int[weak.length];
        for(int i = 0; i < dist.length; i++){
            workers.add(dist[dist.length-(i+1)]);
            if(this.isRepairable(n, 0, repaired, weak, workers)) return workers.size();
        }
        return -1;
    }
    
    private boolean isRepairable(int size, int idx, int[] repaired, int[] weak, List<Integer> workers){
        if(idx == workers.size()) return this.isAllRepaired(repaired);
        boolean repairResult = false;
        for(int i = 0; i < weak.length; i++){
            if(repaired[i] > 0) continue;
            this.repair(size, workers.get(idx), i, repaired, weak);
            repairResult = this.isRepairable(size, idx+1, repaired, weak, workers);
            if(repairResult) return repairResult;
            this.unRepair(size, workers.get(idx), i, repaired, weak);
        }
        return repairResult;
    }
    
    private boolean isAllRepaired(int[] repaired){
        for(int repair : repaired) if(repair == 0) return false;
        return true;
    }
    
    private void repair(int size, int worker, int repairIdx, int[] repaired, int[] weak){
        this.doRepair(size, worker, repairIdx, 1, repaired, weak);
    }
    
    private void unRepair(int size, int worker, int repairIdx, int[] repaired, int[] weak){
        this.doRepair(size, worker, repairIdx, -1, repaired, weak);
    }
    
    private void doRepair(int size, int worker, int repairIdx, int repairTrig, int[] repaired, int[] weak){
        repaired[repairIdx] += repairTrig;
        int repairLength = 0;
        for(int i = this.afterIdx(repairIdx, weak.length); i != repairIdx; i = this.afterIdx(i, weak.length)){
            repairLength += this.calcLength(weak[i], weak[this.beforeIdx(i, weak.length)], size);
            if(repairLength > worker) break;
            repaired[i] += repairTrig; 
        }
    }
    
    private int afterIdx(int idx, int size){
        return (idx+1) % size;
    }
    
    private int beforeIdx(int idx, int size){
        return (idx-1) < 0 ? size-1 : idx-1;
    }
    
    private int calcLength(int front, int back, int size){
        if(back > front) return (size-back)+front;
        return front - back;
    }
    
}
