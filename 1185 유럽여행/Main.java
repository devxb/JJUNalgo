// Created on iPad.

import java.util.*;
import java.io.*;

public class Main {
    
    private static final int INF = 987654321;
    private int country;
    private int[] countryCost;
    private int visitCost;
    private int edgeCount;
    private List<ArrayList<Edge>> edges;

    public static void main(String[] args){
        (new Main()).solve();
    }

    private void solve(){
        this.input();
        int connectedEdgeCost = this.connectEdge(this.getStartNode());
        System.out.println(connectedEdgeCost);
    }

    private void input(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            this.input(br);
        }catch(IOException IOE){}
    }

    private void input(BufferedReader br) throws IOException{
        String[] read = br.readLine().split(" ");
        this.country = Integer.parseInt(read[0]);
        this.countryCost = new int[country+1];
        int edgeCount = Integer.parseInt(read[1]);
        this.inputCountryCost(br);
        this.initEdgeSize(edgeCount);
        for(int i = 0; i < edgeCount; i++){
            read = br.readLine().split(" ");
            int from = Integer.parseInt(read[0]);
            int to = Integer.parseInt(read[1]);
            int cost = Integer.parseInt(read[2]);
            this.edges.get(from).add(new Edge(to, 2*cost+countryCost[to]+countryCost[from]));
            this.edges.get(to).add(new Edge(from, 2*cost+countryCost[to]+countryCost[from]));
        }
    }

    private void inputCountryCost(BufferedReader br) throws IOException{
        for(int i = 1; i <= this.country; i++) 
            countryCost[i] = Integer.parseInt(br.readLine());
    }

    private void initEdgeSize(int edgeCount){
        this.edges = new ArrayList<ArrayList<Edge>>();
        for(int i = 0; i <= edgeCount+1; i++) this.edges.add(new ArrayList<Edge>());
    }

    private int getStartNode(){
        int visitCost = this.INF, idx = 0;
        for(int i = 1; i <= this.country; i++){
            if(visitCost > this.countryCost[i]){
                visitCost = this.countryCost[i];
                idx = i;
            }
        }
        return idx;
    }

    private int connectEdge(int startNode){
        PriorityQueue<Edge> minHeap = this.initMinHeap(new PriorityQueue<Edge>(), startNode);
        boolean[] visited = new boolean[this.country+1];
        visited[startNode] = true;
        int connectedEdgeCost = this.countryCost[startNode];
        while(!minHeap.isEmpty()){
            Edge nowEdge = minHeap.poll();
            if(visited[nowEdge.dest]) continue;
            visited[nowEdge.dest] = true;
            connectedEdgeCost += nowEdge.cost;
            int nowDest = nowEdge.dest;
            for(int i = 0; i < edges.get(nowDest).size(); i++){
                Edge nextEdge = edges.get(nowDest).get(i);
                if(visited[nextEdge.dest]) continue;
                minHeap.add(nextEdge);
            }
        }
        return connectedEdgeCost;
    }

    private PriorityQueue<Edge> initMinHeap(PriorityQueue minHeap, int startNode){
        for(int i = 0; i < this.edges.get(startNode).size(); i++) 
            minHeap.add(this.edges.get(startNode).get(i));
        return minHeap;
    }

    private static class Edge implements Comparable<Edge>{
        
        int dest;
        int cost;

        public Edge(int dest, int cost){
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge edge){
            if(this.cost > edge.cost) return 1;
            if(this.cost < edge.cost) return -1;
            return 0;
        }

    }

}
