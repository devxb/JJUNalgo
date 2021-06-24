import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    class Edge implements Comparable<Edge>{
        
        int node, cost, time;
        
        public Edge(int node, int cost, int time){
            this.node = node;
            this.cost = cost;
            this.time = time;
        }
        
        @Override
        public int compareTo(Edge edge){
            if(time > edge.time) return 1;
            if(time < edge.time) return -1;
            if(time == edge.time){
                if(cost > edge.cost) return 1;
                if(cost < edge.cost) return -1;
            }
            return 0;
        }
        
    }
    
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private String[] read;
    
    public void run() throws IOException{
        int T;
        T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++) System.out.println(input());
    }
    
    private String input() throws IOException{
        int N, M, K;
        read = br.readLine().split(" ");
        N = Integer.parseInt(read[0]);
        M = Integer.parseInt(read[1]);
        K = Integer.parseInt(read[2]);
        ArrayList<ArrayList<Edge> > edges = new ArrayList<ArrayList<Edge> >(N+5);
        for(int i = 0; i <= N+1; i++) edges.add(new ArrayList<Edge>());
        for(int i = 0; i < K; i++){
            int from, to, cost, time;
            read = br.readLine().split(" ");
            from = Integer.parseInt(read[0]);
            to = Integer.parseInt(read[1]);
            cost = Integer.parseInt(read[2]);
            time = Integer.parseInt(read[3]);
            edges.get(from).add(new Edge(to, cost, time));
        }
        return dijkstra(N, M, K, edges);
    }
    
    private String dijkstra(int N, int M, int K, ArrayList<ArrayList<Edge> > edges){
        int[][] check = new int[N+5][M+5]; // node,cost = min time 
        for(int i = 0; i <= N+1; i++) for(int j = 0; j <= M+1; j++) check[i][j] = 987654321;
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        pq.add(new Edge(1, 0, 0));
        for(int i = 0; i <= M; i++) check[1][i] = Math.min(0, check[1][i]); // update
        while(!pq.isEmpty()){
            int nowCost = pq.peek().cost;
            int nowTime = pq.peek().time;
            int nowNode = pq.peek().node;
            pq.poll();
            if(nowCost > M || check[nowNode][nowCost] < nowTime) continue;
            if(nowNode == N) return ""+nowTime;
            for(int i = 0; i < edges.get(nowNode).size(); i++){
                Edge nextEdge = edges.get(nowNode).get(i);
                int nextCost = nowCost + nextEdge.cost;
                int nextTime = nowTime + nextEdge.time;
                int nextNode = nextEdge.node;
                if(nextCost > M || check[nextNode][nextCost] <= nextTime) continue;
                for(int j = nextCost; j <= M; j++) check[nextNode][j] = Math.min(nextTime, check[nextNode][j]); // update
                pq.add(new Edge(nextNode, nextCost, nextTime));
            }
        }
        return "Poor KCM";
    }
    
}
