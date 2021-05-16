package algorithm;

import java.io.*;
import java.util.*;

public class Main{
    
    public static void main(String[] args){
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Path implements Comparable<Path>{
    
    public int idx, cost;
    
    public Path(int cost, int idx){
        set(cost, idx);
    }
    
    public void set(int cost, int idx){
        this.cost = cost;
        this.idx = idx;
    }
    
    @Override
    public int compareTo(Path path){
        if(cost > path.cost) return 1;
        if(cost < path.cost) return -1;
        return 0;
    }
}

class Solve{
    
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private int[] markets = new int[10];
    private boolean[] isMarket;
    private boolean[] selected = new boolean[10];
    private int[] marketSelect = new int[10];
    private int[][] dijk;
    private int N, M, K;
    private int INF = 987654321;
    private ArrayList<ArrayList<Path> > road = new ArrayList<ArrayList<Path> >();
    
    public void run(){
        input();
        for(int i = 1; i <= K; i++) setDijk(i);
        int ans = getDistance(0);
        if(ans == INF) ans = 0;
        System.out.println(ans);
    }
    
    private int getDistance(int select){
        int ret = INF;
        if(select == K){
            int ans = 0;
            for(int i = 1; i < K; i++){
                ans += dijk[marketSelect[i]][markets[marketSelect[i+1]]]; // i번째 마켓에서 i+1번째 마켓의 번호로 가는 경로값 계산
            }
            for(int i = 1; i <= N; i++){
                if(isMarket[i]) continue;
                ret = Math.min(ret, ans + dijk[marketSelect[1]][i] + dijk[marketSelect[K]][i]);
            }
            return ret;
        }
        for(int i = 1; i <= K; i++){
            if(selected[i]) continue; // i번째 마켓이 선택되었다면 continue;
            selected[i] = true; // i번째 마켓 선택
            marketSelect[select+1] = i; // marketSelect에는 i번째 마켓을 넣어줌. 나중에 번호로 바꿔서 계산해야함
            ret = Math.min(ret, getDistance(select+1));
            selected[i] = false;
        }
        return ret;
    }
    
    private void setDijk(int startIdx){
        PriorityQueue<Path> pq = new PriorityQueue<Path>();
        pq.add(new Path(0,markets[startIdx]));
        dijk[startIdx][markets[startIdx]] = 0;
        while(!pq.isEmpty()){
            int nowCost = pq.peek().cost;
            int nowIdx = pq.peek().idx;
            pq.poll(); 
            if(dijk[startIdx][nowIdx] < nowCost) continue;
            for(int i = 0; i < road.get(nowIdx).size(); i++){
                int nextIdx = road.get(nowIdx).get(i).idx;
                int nextCost = nowCost + road.get(nowIdx).get(i).cost;
                if(dijk[startIdx][nextIdx] <= nextCost) continue;
                dijk[startIdx][nextIdx] = nextCost;
                pq.add(new Path(nextCost, nextIdx));
            }
        }
    }
    
    private void initial(){
        dijk = new int[10][N+5];
        isMarket = new boolean[N+5];
        for(int i = 0; i <= N+5; i++) road.add(new ArrayList<Path>());
        for(int i = 1; i <= K; i++){
            for(int j = 0; j <= N+1; j++) dijk[i][j] = INF;
        }
    }
    
    private void input(){
        try{
            String[] read = br.readLine().split(" ");
            N = Integer.parseInt(read[0]);
            M = Integer.parseInt(read[1]);
            K = Integer.parseInt(read[2]);
            initial();
            for(int i = 1; i <= K; i++){
                read = br.readLine().split(" ");
                markets[i] = Integer.parseInt(read[0]);
                isMarket[markets[i]] = true;
            }
            for(int i = 1; i <= M; i++){
                read = br.readLine().split(" ");
                int from, to, cost;
                from = Integer.parseInt(read[0]);
                to = Integer.parseInt(read[1]);
                cost = Integer.parseInt(read[2]);
                road.get(from).add(new Path(cost, to));
                road.get(to).add(new Path(cost, from));
            }
        } catch (IOException ioe){}
    }
    
}
