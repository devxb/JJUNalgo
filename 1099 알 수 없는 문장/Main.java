import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args){
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    private Scanner sc = new Scanner(System.in);
    private int N;
    private String word;
    private ArrayList<String> dictionary = new ArrayList<String>(55);
    private int[] dp;
    private int[] checkCharset = new int[30];
    
    public void run(){
        input();
        System.out.println(getDp(0));
    }
    
    private void input(){
        word = sc.next();
        N = sc.nextInt();
        dp = new int[word.length()+5];
        for(int i = 0; i <= word.length(); i++) dp[i] = 987654321;
        for(int i = 0; i < N; i++){
            String read = sc.next();
            dictionary.add(read);
        }
    }
    
    private ArrayList<String> partitionWord(String targetWord){ // 분할한 스트링 저장 인덱스순으로 i++
        ArrayList<String> ans = new ArrayList<String>(targetWord.length()+5);
        for(int i = 0; i < targetWord.length(); i++) ans.add(targetWord.substring(i,targetWord.length()));
        return ans;
    }
    
    private int getDp(int idx){
        if(idx == word.length()){
            if(dp[word.length()-1] == 987654321) return -1;
            return dp[word.length()-1];
        }
        ArrayList<String> partitionWordList = partitionWord(word.substring(0, idx+1));
        for(int i = 0; i < partitionWordList.size(); i++){
            String shuffleWord = partitionWordList.get(i);
            int from = i; // mainWord의 분할의 시작된 idx를 나타냄
            for(int j = 0; j < dictionary.size(); j++){
                String originWord = dictionary.get(j);
                if(!compareAlphabet(shuffleWord, originWord)) continue;
                int diffWordCnt = compareWord(shuffleWord, originWord);
                if(from-1 < 0){ 
                    dp[idx] = Math.min(dp[idx], diffWordCnt);
                    continue;
                }
                dp[idx] = Math.min(diffWordCnt + dp[from-1], dp[idx]);
            }
            
        }
        return getDp(idx+1);
    }
    
    private boolean compareAlphabet(String comp1, String comp2){
        if(comp1.length() != comp2.length()) return false;
        int[] check = new int[30];
        for(int i = 0; i < comp1.length(); i++){
            check[(int)comp1.charAt(i) - (int)'a']++;
            check[(int)comp2.charAt(i) - (int)'a']--;
        }
        for(int i = 0; i < (int)'z' - (int)'a'; i++){
            if(check[i] != 0) return false;
        }
        return true;
    }
    
    private int compareWord(String comp1, String comp2){
        int ans = 0;
        for(int i = 0; i < comp1.length(); i++){
            if(comp1.charAt(i) != comp2.charAt(i)) ans++;
        }
        return ans;
    }
    
}
