import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args) throws Exception{
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    class Position implements Comparable<Position>{
        public int cost, idx;
        public int parIdx; 
        
        public Position(int cost, int idx){
            this.cost = cost;
            this.idx = idx;
        }
        
        public Position(int cost, int idx, int parIdx){
            this.cost = cost;
            this.idx = idx;
            this.parIdx = parIdx;
        }
        
        @Override
        public int compareTo(Position pos){
            if(this.cost > pos.cost) return 1;
            if(this.cost < pos.cost) return -1;
            return 0;
        }
    }
    
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private ArrayList<ArrayList<Position> > edge;
    private ArrayList<ArrayList<Integer> > par;
    private String[] read;
    private int N, M, S, D;
    private boolean[][] path;
    
    public void run() throws IOException{
        while(input()){
            dijkstra();
            setPath(D);
            System.out.println(dijkstra());
        }
    }
    
    private boolean input() throws IOException{ 
        read = br.readLine().split(" ");
        N = Integer.parseInt(read[0]);
        M = Integer.parseInt(read[1]);
        if(N == 0 && M == 0) return false;
        read = br.readLine().split(" ");
        S = Integer.parseInt(read[0]);
        D = Integer.parseInt(read[1]);
        edge = new ArrayList<ArrayList<Position> >(N+5);
        par = new ArrayList<ArrayList<Integer> >(N+5);
        path = new boolean[N+5][N+5];
        for(int i = 0; i <= N; i++){
            par.add(new ArrayList<Integer>());
            edge.add(new ArrayList<Position>());
        }
        for(int i = 0; i < M; i++){
            read = br.readLine().split(" ");
            int from = Integer.parseInt(read[0]);
            int to = Integer.parseInt(read[1]);
            int cost = Integer.parseInt(read[2]);
            edge.get(from).add(new Position(cost, to));
        }
        return true;
    }
    
    private int dijkstra(){
        int ans = 987654321;
        PriorityQueue<Position> pq = new PriorityQueue<Position>();
        pq.add(new Position(0, S, S));
        int[] check = new int[N+5];
        boolean[][] del = new boolean[N+5][N+5];
        for(int i = 0; i <= N; i++) check[i] = 987654321;
        check[S] = 0;
        while(!pq.isEmpty()){
            int nowIdx = pq.peek().idx;
            int nowCost = pq.peek().cost;
            int parIdx = pq.peek().parIdx;
            pq.poll();
            if(nowCost > ans) break; // 최적화
            if(check[nowIdx] < nowCost) continue;
            if(!del[nowIdx][parIdx]) par.get(nowIdx).add(parIdx); // 최적화
            del[nowIdx][parIdx] = true;
            if(nowIdx == D){
                ans = nowCost;
                continue;
            }
            for(int i = 0; i < edge.get(nowIdx).size(); i++){
                Position p = edge.get(nowIdx).get(i);
                int nextIdx = p.idx;
                int nextCost = nowCost + p.cost;
                if(nextCost > check[nextIdx] || path[nowIdx][nextIdx] == true) continue;
                if(nextCost == check[nextIdx]){ // 최적화
                    if(!del[nextIdx][nowIdx]) par.get(nextIdx).add(nowIdx);
                    del[nextIdx][nowIdx] = true;
                    continue;
                }
                check[nextIdx] = nextCost;
                pq.add(new Position(nextCost, nextIdx, nowIdx));
            }
        }
        if(ans == 987654321) return -1;
        return ans;
    }
    
    private void setPath(int idx){
        if(idx == S) return;
        for(int i = 0; i < par.get(idx).size(); i++){
            int nextIdx = par.get(idx).get(i);
            if(path[nextIdx][idx]) continue; // 최적화 
            path[nextIdx][idx] = true;
            setPath(nextIdx);
        }
    }
    
}
