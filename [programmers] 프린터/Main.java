import java.util.*;

class Solution {
    
    public int solution(int[] priorities, int location) {
        Queue<Integer> papers = this.getPapers(priorities);
        return this.findLocationOnPapers(papers, location);
    }
    
    private Queue<Integer> getPapers(int[] priorities){
        Queue<Integer> papers = new LinkedList<Integer>();
        for(int i = 0; i < priorities.length; i++) papers.add(priorities[i]);
        return papers;
    }
    
    private int findLocationOnPapers(Queue<Integer> papers, int location){
        int[] paperPriority = this.getPaperPriority(papers);
        int printedNum = 1;
        while(!papers.isEmpty()){
            Integer thisPaperPriority = papers.poll();
            if(this.isExistHighPriorityPaper(paperPriority, thisPaperPriority)){
                papers.add(thisPaperPriority);
                if(this.isTargetPaper(location)) location = papers.size()-1; 
                else location--;
                continue;
            }
            if(this.isTargetPaper(location)) return printedNum;
            printedNum++;
            location--;
            paperPriority[thisPaperPriority]--;
        }
        return -1;
    }
    
    private int[] getPaperPriority(Queue<Integer> papers){
        int[] paperPriority = new int[10]; 
        for(int i = 0; i < papers.size(); i++){
            Integer thisPaperPriority = papers.poll();
            paperPriority[thisPaperPriority]++;
            papers.add(thisPaperPriority);
        }
        return paperPriority;
    }
    
    private boolean isExistHighPriorityPaper(int[] paperPriority, int thisPaperPriority){
        for(int i = thisPaperPriority+1; i < 10; i++) if(paperPriority[i] > 0) return true;
        return false;
    }
    
    private boolean isTargetPaper(int location){
        return location == 0 ? true : false;
    }
    
}
