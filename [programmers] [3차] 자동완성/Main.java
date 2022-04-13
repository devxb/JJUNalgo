import java.util.*;

class Solution {
   
    public int solution(String[] words) {
        Trie rootTrie = this.makeTrie(words);
        return this.calcTypingCount(words, rootTrie);
    }
   
    private Trie makeTrie(String[] words){
        Trie rootTrie = new Trie('0');
        rootTrie.plusCandidateWordCount();
        for(int i = 0; i < words.length; i++){
            String word = words[i];
            Trie trieChain = rootTrie;
            for(int j = 0; j < word.length(); j++){
                char alphabet = word.charAt(j);
                Trie trie = trieChain.getConnectedTrie(alphabet);
                if(this.isNull(trie)) trieChain.connectTrie(new Trie(alphabet));
                else trie.plusCandidateWordCount();
                trieChain = trieChain.getConnectedTrie(alphabet);
            }
        }
        return rootTrie;
    }    
   
    private boolean isNull(Object obj){
        return obj == null ? true : false;
    }
   
    private int calcTypingCount(String[] words, Trie rootTrie){
        int typeCount = 0;
        for(int i = 0; i < words.length; i++){
            String word = words[i];
            Trie trieChain = rootTrie;
            for(int j = 0; j < word.length(); j++){
                if(trieChain.isOneCandidate()) break;
                trieChain = trieChain.getConnectedTrie(word.charAt(j));
                typeCount++;
            }
        }
        return typeCount;
    }
   
    private static class Trie{
       
        private final char alphabet;
        private int candidateWordCount;
        private Trie[] connectedTrie;
       
        public void connectTrie(Trie trie){
            this.connectedTrie[(int)trie.getAlphabet()-(int)'a'] = trie;
        }
       
        public char getAlphabet(){
            return this.alphabet;
        }
       
        public void plusCandidateWordCount(){
            this.candidateWordCount++;
        }
       
        public boolean isOneCandidate(){
            return this.candidateWordCount == 1 ? true : false;
        }
        
        public Trie getConnectedTrie(char alphabet){
            return this.connectedTrie[(int)alphabet-(int)'a'];
        }
       
        public Trie(char alphabet){
            this.alphabet = alphabet;
            this.connectedTrie = new Trie[26];
            this.candidateWordCount = 1;
        }
       
    }
   
}
