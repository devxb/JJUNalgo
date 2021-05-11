import java.io.*;
import java.util.*;

public class Main{
    
    public static void main(String[] args){
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Pair implements Comparable<Pair>{
    
    public int first, second;
    
    public Pair(int first, int second){
        set(first, second);
    }
    
    public void set(int first, int second){
        this.first = first;
        this.second = second;
    }
    
    @Override
    public int compareTo(Pair pair){
        if(this.first > pair.first) return 1;
        else if(this.first < pair.first) return -1;
        return 0;
    }
    
}

class Solve{
    
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N, P, K;
    private ArrayList<ArrayList<Pair> > edge = new ArrayList<ArrayList<Pair> >();
    
    public void run(){
        input();
        System.out.println(solve());
    }
    
    private void input(){
        try{
            String temp[] = br.readLine().split(" ");
            N = Integer.parseInt(temp[0]);
            P = Integer.parseInt(temp[1]);
            K = Integer.parseInt(temp[2]);
            for(int i = 0; i < N+5; i++) edge.add(new ArrayList<Pair>());
            for(int i = 0; i < P; i++){
                int from, to, weight;
                temp = br.readLine().split(" ");
                from = Integer.parseInt(temp[0]);
                to = Integer.parseInt(temp[1]);
                weight = Integer.parseInt(temp[2]);
                edge.get(from).add(new Pair(weight, to));
                edge.get(to).add(new Pair(weight, from));
            }
        } catch (IOException ioe){}
    }
    
    private int solve(){
        int ret = -1;
        int low = 1, max = 1000000;
        for(int i = 1; i <= 21; i++){
            int mid = (low + max) / 2;
            if(connect(mid)){
                ret = mid;
                max = mid - 1;
            }
            else low = mid + 1;
        }
        return ret;
    }
    
    private boolean connect(int maximumCost){
        int[] check = new int[N+5];
        fill(check);
        check[1] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>(); // K사용횟수, 현재 노드
        pq.add(new Pair(0,1));
        while(!pq.isEmpty()){
            int restK = pq.peek().first;
            int node = pq.peek().second;
            pq.poll();
            if(check[node] < restK || restK > K) continue;
            if(node == N) return true;
            for(int i = 0; i < edge.get(node).size(); i++){
                int nextNode = edge.get(node).get(i).second;
                int nextK = restK;
                if(edge.get(node).get(i).first > maximumCost) nextK++;
                if(check[nextNode] <= nextK) continue;
                check[nextNode] = nextK;
                pq.add(new Pair(nextK, nextNode));
            }
        }
        return false;
    }
    
    private void fill(int[] target){
        for(int i = 0; i <= N+2; i++) target[i] = 987654321;
    }
    
}
