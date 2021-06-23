import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args) throws Exception{
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    class Edge implements Comparable<Edge>{
        
        public int node, cost;
        
        public Edge(int cost, int node){
            this.node = node;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Edge edge){
            if(this.cost > edge.cost) return 1;
            if(this.cost < edge.cost) return -1;
            return 0;
        }
        
    }
    
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private ArrayList<ArrayList<Edge> > edges;
    private ArrayList<ArrayList<Integer> > cand;
    private int n, m, k;
    private String[] read;
    private int[] costCheck;
    
    public void run() throws IOException{
        input();
        dijkstra();
        for(int i = 1; i <= n; i++){
            if(cand.get(i).size() < k) System.out.println(-1);
            else System.out.println(cand.get(i).get(k-1));
        }
    }
    
    private void input() throws IOException{
        read = br.readLine().split(" ");
        n = toInt(read[0]);
        m = toInt(read[1]);
        k = toInt(read[2]);
        costCheck = new int[n+5];
        edges = new ArrayList<ArrayList<Edge> >(n+5);
        cand = new ArrayList<ArrayList<Integer> >(n+5);
        for(int i = 0; i <= n+1; i++){
            costCheck[i] = 987654321;
            edges.add(new ArrayList<Edge>());
            cand.add(new ArrayList<Integer>());
        }
        for(int i = 0; i < m; i++){
            read = br.readLine().split(" ");
            int from, to, cost;
            from = toInt(read[0]);
            to = toInt(read[1]);
            cost = toInt(read[2]);
            edges.get(from).add(new Edge(cost, to));
        }
    }
    
    private void dijkstra(){
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        pq.add(new Edge(0, 1));
        costCheck[1] = 0;
        while(!pq.isEmpty()){
            int nowNode = pq.peek().node;
            int nowCost = pq.peek().cost;
            pq.poll();
            if(cand.get(nowNode).size() >= k) continue;
            cand.get(nowNode).add(nowCost);
            for(int i = 0; i < edges.get(nowNode).size(); i++){
                int nextNode = edges.get(nowNode).get(i).node;
                int nextCost = nowCost + edges.get(nowNode).get(i).cost;
                if(costCheck[nextNode] == nextCost || cand.get(nextNode).size() >= k) continue;
                pq.add(new Edge(nextCost, nextNode));
            }
        }
    }
    
    private int toInt(String s){
        return Integer.parseInt(s);
    }
    
}
