import java.util.*;
import static java.lang.Math.pow;

class Solution {
    
    public int solution(String dartResult) {
        Map<Character, Integer> prize = new HashMap<Character, Integer>();
        this.initMap(prize);
        Deque<Integer> scoreDeq = new LinkedList<Integer>();
        int nowScore = 0, totalScore = 0;
        for(int i = 0; i < dartResult.length(); i++){
            char c = dartResult.charAt(i);
            if(this.isScore(c)){
                if(i+1 < dartResult.length() && c == '1' && dartResult.charAt(i+1) == '0') {
                    nowScore = 10;
                    i++;
                }
                else nowScore = Integer.parseInt(c+"");
                continue;
            }
            if(this.isStar(c)){
                this.doubleScoreDeq(scoreDeq);
                continue;
            }
            if(this.isAhcha(c)){
                this.minusScoreDeq(scoreDeq);
                continue;
            }
            nowScore = (int)pow(nowScore, prize.get(c));
            totalScore += this.setScoreDeq(scoreDeq, nowScore);
        }
        while(!scoreDeq.isEmpty()) totalScore += scoreDeq.pollFirst();
        return totalScore;
    }
    
    private void initMap(Map<Character, Integer> map){
        map.put('S', 1);
        map.put('D', 2);
        map.put('T', 3);
    }
    
    private int setScoreDeq(Deque<Integer> scoreDeq, int score){
        scoreDeq.addLast(score);
        int res = 0;
        while(scoreDeq.size() > 2) res += scoreDeq.pollFirst();
        return res;
    }
    
    private void doubleScoreDeq(Deque<Integer> scoreDeq){
        for(int i = 0; i < scoreDeq.size(); i++){
            int nowScore = scoreDeq.pollFirst();
            scoreDeq.addLast(nowScore*2);
        }
    }
    
    private void minusScoreDeq(Deque<Integer> scoreDeq){
        int nowScore = scoreDeq.pollLast();
        scoreDeq.add(-1*nowScore);
    }
    
    private boolean isScore(char c){
        try{
            Integer.parseInt(c+"");
            return true;
        }catch(NumberFormatException NFE){
            return false;
        }
    }
    
    private boolean isStar(char c){
        return c == '*';
    }
    
    private boolean isAhcha(char c){
        return c == '#';
    }
    
}
