import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws Exception{
        Solve solve = new Solve();
        solve.run();
    }
}

class Pair{
    
    public int first;
    public int second;
    
    public Pair(int first, int second){
        set(first, second);
    }
    
    public void set(int first, int second){
        this.first = first;
        this.second = second;
    }
    
    public int getDistance(Pair p){
        return (int)Math.pow(first - p.first,2) + (int)Math.pow(second - p.second,2);
    }
    
}

class DistanceWithIndex implements Comparable<DistanceWithIndex>{
    public int distance;
    public int idx1;
    public int idx2;
    
    public DistanceWithIndex(int distance, int idx1, int idx2){
        this.distance = distance;
        this.idx1 = idx1;
        this.idx2 = idx2;
    }
    
    @Override
    public int compareTo(DistanceWithIndex dwi){
        if(distance > dwi.distance) return 1;
        if(distance < dwi.distance) return -1;
        return 0;
    }
}

class Solve{
    
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private ArrayList<Pair> position = new ArrayList<Pair>();
    private PriorityQueue<DistanceWithIndex> pq = new PriorityQueue<DistanceWithIndex>();
    private int N, C;
    private int[] union;
    
    public void run() throws Exception{
        input();
        set();
        System.out.println(solve());
    }
    
    private int unionFind(int idx){
        if(union[idx] == idx) return idx;
        return union[idx] = unionFind(union[idx]);
    }
    
    private void mergeUnion(int idx1, int idx2){
        int par = Math.min(unionFind(idx1), unionFind(idx2));
        int son = Math.max(unionFind(idx1), unionFind(idx2));
        union[par] = unionFind(son);
    } 
    
    private boolean checkUnionFind(int idx1, int idx2){
        return unionFind(idx1) == unionFind(idx2);
    }
    
    private void input() throws IOException{
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        C = Integer.parseInt(temp[1]);
        for(int i = 0; i < N; i++){
            temp = br.readLine().split(" ");
            position.add(new Pair(Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));
        }
    }
    
    private void set(){
        union = new int[N+5];
        for(int i = 0; i < N; i++){
            union[i] = i;
            Pair idx1 = position.get(i);
            for(int j = 0; j < N; j++){
                if(i == j) continue;
                Pair idx2 = position.get(j);
                int distance = idx1.getDistance(idx2);
                if(distance < C) continue;
                pq.add(new DistanceWithIndex(distance,i,j));
            }
        }
    }
    
    private long solve(){
        long ret = 0;
        int connect = 0;
        boolean[] check = new boolean[N+5];
        while(!pq.isEmpty()){
            int nowDistance = pq.peek().distance;
            int idx1 = pq.peek().idx1;
            int idx2 = pq.peek().idx2;
            pq.poll();
            if(connect == N) break;
            if(checkUnionFind(idx1, idx2)) continue;
            mergeUnion(idx1, idx2);
            if(!check[idx1]){
                check[idx1] = true; 
                connect++;
            }
            if(!check[idx2]){
                check[idx2] = true;
                connect++;
            }
            ret += nowDistance;
        }
        if(connect < N) return -1;
        return ret;
    }
    
}
