import java.util.*;
import static java.lang.Math.min;

class Solution {
    
    public int solution(String s) {
        int compressWordCnt = s.length();
        for(int i = 1; i <= s.length()/2; i++) 
            compressWordCnt = min(compressWordCnt, this.compressWord(i, s).length());
        return compressWordCnt;
    }
    
    private String compressWord(int compress, String word){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < word.length(); i += compress){
            int compressedCount = 0;
            String compressedWord = "";
            int temp = i;
            if(temp+compress <= word.length()) compressedWord = word.substring(temp, temp+compress);
            while(temp+compress <= word.length() 
                  && compressedWord.equals(word.substring(temp, temp+compress))){
                temp = temp+compress;
                compressedCount++;
            }
            if(compressedCount > 1){
                sb.append(compressedCount).append(compressedWord);
                i = temp-compress;
            }
            else sb.append(compressedWord);
        }
        sb.append(word.substring(word.length()-word.length()%compress, word.length()));
        return sb.toString();
    }

}
