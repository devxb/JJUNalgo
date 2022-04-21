import java.util.*;

class Solution {
    
    public int[] solution(String msg) {
        Trie rootTrie = this.initTrie();
        int[] printedIdx = this.editDictionary(msg, rootTrie);
        return printedIdx;
    }
    
    private Trie initTrie(){
        Trie rootTrie = new Trie('0', 0);
        int idx = 1;
        for(int i = (int)'A'; i <= (int)'Z'; i++){
            rootTrie.setNext((char)i, idx);
            idx++;
        }
        return rootTrie;
    }
    
    private int[] editDictionary(String msg, Trie rootTrie){
        List<Integer> result = new ArrayList<Integer>();
        int idx = 27;
        for(int i = 0; i < msg.length(); i++){
            char word = msg.charAt(i);
            Trie trieChain = rootTrie;
            while(trieChain.hasNext(word)){
                trieChain = trieChain.next(word);
                i++;
                if(i == msg.length()) break;
                word = msg.charAt(i);
            }
            trieChain.setNext(word, idx);
            result.add(trieChain.getIdx());
            idx++;
            i--;
        }
        return this.convertToArray(result);
    }
    
    private int[] convertToArray(List<Integer> list){
        int[] resultArray = new int[list.size()];
        for(int i = 0; i < list.size(); i++) resultArray[i] = list.get(i);
        return resultArray;
    }
    
}

class Trie{
    
    private char word;
    private int idx;
    private Trie[] nextTries;
    
    public Trie(char word, int idx){
        this.word = word;
        this.idx = idx;
        this.nextTries = new Trie[26];
    }
    
    public char getWord(){
        return this.word;
    }
    
    public void setNext(char c, int idx){
        if(this.hasNext(c)) return;
        this.nextTries[(int)c-(int)'A'] = new Trie(c, idx);
    }
    
    public Trie next(char c){
        return this.nextTries[(int)c-(int)'A'];
    }
    
    public boolean hasNext(char c){
        return this.nextTries[(int)c-(int)'A'] != null;
    }
    
    public int getIdx(){
        return this.idx;
    }
    
}
