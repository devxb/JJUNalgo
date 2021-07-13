import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args) throws Exception{
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    class frequency implements Comparable<frequency>{
        public String word;
        public int freq;
        
        public frequency(String word, int freq){
            this.word = word;
            this.freq = freq;
        }
        
        public void add(){
            this.freq++;
        }
        
        @Override
        public int compareTo(frequency f){
            if(this.freq > f.freq) return -1;
            if(this.freq < f.freq) return 1;
            if(this.freq == f.freq){
                if(this.word.length() > f.word.length()) return -1;
                if(this.word.length() < f.word.length()) return 1;
                else{
                    if(this.word.compareTo(f.word) > 0) return 1;
                    if(this.word.compareTo(f.word) < 0) return -1;
                }
            }
            return 0;
        }
    }
    
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private String[] read;
    private int N, M;
    private ArrayList<String> dictionary = new ArrayList<String>();
    private ArrayList<frequency> frqDictionary = new ArrayList<frequency>();
    
    public void run() throws IOException{
        input();
        setFrqDictionary();
        for(int i = 0; i < frqDictionary.size(); i++) {
            bw.write(frqDictionary.get(i).word);
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    private void input() throws IOException{
        read = br.readLine().split(" ");
        N = Integer.parseInt(read[0]);
        M = Integer.parseInt(read[1]);
        for(int i = 0; i < N; i++){
            String r = br.readLine();
            if(r.length() < M) continue;
            dictionary.add(r);
        }
        br.close();
        Collections.sort(dictionary);
    }
    
    public void setFrqDictionary(){
        frequency frq = new frequency(dictionary.get(0),1);
        for(int i = 1; i < dictionary.size(); i++){
            if(!frq.word.equals(dictionary.get(i))){
                frqDictionary.add(frq);
                frq = new frequency(dictionary.get(i),1);
            }
            else frq.add();
        }
        frqDictionary.add(frq); 
        Collections.sort(frqDictionary);
    }
    
}
