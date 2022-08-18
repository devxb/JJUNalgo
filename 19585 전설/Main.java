import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Main{

    private int colorCount, nickNameCount;

    private final Trie colorNameEnterCursor = new Trie('S', false);
    private final Set<String> nickNameSet = new HashSet<>();

    public static void main(String[] args){
        (new Main()).solve();
    }

    private void solve(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            String[] read = br.readLine().split(" ");
            this.colorCount = Integer.parseInt(read[0]);
            this.nickNameCount = Integer.parseInt(read[1]);
            this.inputColors(br);
            this.inputNickNames(br);
            System.out.print(this.findWinableTeams(br));
        }catch(IOException IOE){}
    }

    private void inputColors(BufferedReader br) throws IOException{
        for(int i = 0; i < colorCount; i++) {
            String read = br.readLine();
            this.addStringOnTrie(read, this.colorNameEnterCursor);
        }
    }

    private void inputNickNames(BufferedReader br) throws IOException{
        for(int i = 0; i < nickNameCount; i++){
            String read = br.readLine();
            this.nickNameSet.add(read);
        }
    }

    private void addStringOnTrie(String string, Trie cursor){
        for(int i = 0; i < string.length(); i++){
            Trie trie = new Trie(string.charAt(i), (i == string.length()-1));
            cursor.addNextTrie(trie);
            cursor = cursor.nextTrie(string.charAt(i));
        }
    }

    private String findWinableTeams(BufferedReader br) throws IOException{
        int teamCount = Integer.parseInt(br.readLine());
        StringBuilder resultBuilder = new StringBuilder();
        for(int i = 0; i < teamCount; i++){
            String read = br.readLine();
            List<Integer> matchedPoints = new ArrayList<>();
            this.markColorPoint(read, this.colorNameEnterCursor, matchedPoints);
            resultBuilder.append(this.matchNickNamePoint(read, matchedPoints)).append("\n");
        }
        return resultBuilder.toString();
    }

    private void markColorPoint(String string, Trie cursor, List<Integer> matchedPoints){
        for(int i = 0; i < string.length(); i++){
            cursor = cursor.nextTrie(string.charAt(i));
            if(cursor == null) return;
            if(cursor.isEnd && string.length() - (i+1) <= 1000) matchedPoints.add(i);
        }
    }

    private String matchNickNamePoint(String string, List<Integer> matchedPoints){
        for (Integer matchedPoint : matchedPoints)
            if (this.nickNameSet.contains(string.substring(matchedPoint+1))) return "Yes";
        return "No";
    }

    private static class Trie{
        char name;
        Trie[] nextNames = new Trie[(int)'z'-(int)'a' + 1];
        boolean isEnd;

        public Trie(char name, boolean isEnd){
            this.name = name;
            this.isEnd = isEnd;
        }

        public void addNextTrie(Trie trie){
            int nextNameIdx = (int)trie.name - (int)'a';
            if(nextNames[nextNameIdx] == null) nextNames[nextNameIdx] = trie;
            nextNames[nextNameIdx].isEnd = nextNames[nextNameIdx].isEnd == true ? true : trie.isEnd;
        }

        public Trie nextTrie(char nextName){
            int nextNameIdx = (int)nextName - (int)'a';
            return nextNames[nextNameIdx];
        }

    }

}
