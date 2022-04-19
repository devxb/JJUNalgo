import java.util.*;
import static java.lang.Math.*;

class Solution {
    
    public int solution(String str1, String str2) {
        List<String> parsedList1 = this.parseString(str1);
        List<String> parsedList2 = this.parseString(str2);
        if(parsedList1.isEmpty() && parsedList2.isEmpty()) return 65536;
        int minSetCount = this.getMinSetCount(parsedList1, parsedList2);
        int maxSetCount = this.getMaxSetCount(parsedList1, parsedList2);
        return (int)((double)65536 * ((double)minSetCount / (double)maxSetCount));
    }
    
    private List<String> parseString(String str){
        List<String> parsedList = new ArrayList<String>();
        for(int i = 0; i < str.length()-1; i++){
            String parsedWord = this.parseWord(str.substring(i,i+2));
            if(!this.isCollectParsedWord(parsedWord)) continue;
            parsedList.add(parsedWord);
        }
        return parsedList;
    }
    
    private String parseWord(String word){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 2; i++){
            if(!this.isAlphabet(word.charAt(i))) return word;
            sb.append(this.setUpperCase(word.charAt(i)));
        }
        return sb.toString();
    }
    
    private boolean isCollectParsedWord(String word){
        for(int i = 0; i < 2; i++)
            if(!this.isAlphabet(word.charAt(i))) return false;
        return true;
    }
    
    private boolean isAlphabet(char c){
        return (this.isLowerCase(c) || this.isUpperCase(c));
    }
    
    private char setUpperCase(char c){
        if(this.isUpperCase(c)) return c;
        return (char)(((int)c - (int)'a') + (int)'A');
    }
    
    private boolean isLowerCase(char c){
        return ((int)c >= (int)'a' && (int)c <= (int)'z');
    }
    
    private boolean isUpperCase(char c){
        return ((int)c >= (int)'A' && (int)c <= (int)'Z');
    }
    
    private int getMinSetCount(List<String> list1, List<String> list2){
        Map<String, Integer> finded = new HashMap<String, Integer>();
        int count = 0;
        for(int i = 0; i < this.getMinList(list1, list2).size(); i++){
            String word = this.getMinList(list1, list2).get(i);
            if(finded.getOrDefault(word, 0) == 1) continue;
            finded.put(word, 1);
            int list1WordCount = this.countOfWord(list1, word);
            int list2WordCount = this.countOfWord(list2, word);
            System.out.println("min : " + word + " : " + list1WordCount + ", " + list2WordCount);
            count += min(list1WordCount, list2WordCount);
        }
        return count;
    }
    
    private int getMaxSetCount(List<String> list1, List<String> list2){
        Map<String, Integer> finded = new HashMap<String, Integer>();
        List<String> mergedList = this.mergeList(list1, list2);
        int count = 0;
        for(int i = 0; i < mergedList.size(); i++){
            String word = mergedList.get(i);
            if(finded.getOrDefault(word, 0) == 1) continue;
            finded.put(word, 1);
            int list1WordCount = this.countOfWord(list1, word);
            int list2WordCount = this.countOfWord(list2, word);
            count += max(list1WordCount, list2WordCount);
        }
        return count;
    }
    
    private List<String> getMinList(List<String> list1, List<String> list2){
        return list1.size() > list2.size() ? list2 : list1;
    }
    
    private int countOfWord(List<String> list, String word){
        int count = 0;
        for(String string : list) if(string.equals(word)) count++; 
        return count;
    }
    
    private List<String> mergeList(List<String> list1, List<String> list2){
        List<String> list = new ArrayList<String>();
        for(String str : list1) list.add(str);
        for(String str : list2) list.add(str);
        return list;
    }
    
}
