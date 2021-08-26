import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main{
    
    public static void main(String[] args){
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    private int T;
    
    public void run(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            T = Integer.parseInt(br.readLine());
            for(int i = 0; i < T; i++){
                HashMap<String, Integer> ans = input(br);
                System.out.println(calc(mapToArray(ans), ans.size()));
            }
        }
        catch(IOException IOE){}
    }
    
    private int[] mapToArray(HashMap<String, Integer> hm){
        int[] arr = new int[hm.size()];
        int idx = 0;
        for(String key : hm.keySet()) {
            arr[idx] = hm.get(key)+1;
            idx++;
        }
        return arr;
    }
    
    private long calc(int[] arr, int size){
        long ret = 1;
        for(int i = 0; i < size; i++) ret *= arr[i];
        return ret-1;
    }
    
    private HashMap<String, Integer> input(BufferedReader br) throws IOException{
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++){
            String[] read = br.readLine().split(" ");
            Integer key = hm.get(read[1]);
            if(key != null) hm.put(read[1], key+1);
            else hm.put(read[1], 1);
        }
        return hm;
    }
    
}
