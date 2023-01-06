import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main{

    private int N, K;
    private List<ArrayList<Bridge>> edges;
    private List<ArrayList<Bridge>> tree;
    int treeStartIdx, treeCost;
    {
        edges = new ArrayList<>();
        tree = new ArrayList<>();
        treeCost = -1;
    }

    public static void main(String[] args){
        (new Main()).solve();
    }

    private void solve(){
        input();
        doSpanningTree();
        calculateMaxTreeSize();
    }

    private void input(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            String[] line = br.readLine().split(" ");
            N = Integer.parseInt(line[0]);
            for(int i = 0; i < N; i++) {
                edges.add(new ArrayList<>());
                tree.add(new ArrayList<>());
            }
            K = Integer.parseInt(line[1]);
            for(int i = 0; i < K; i++){
                line = br.readLine().split(" ");
                int from, to, cost;
                from = Integer.parseInt(line[0]);
                to = Integer.parseInt(line[1]);
                cost = Integer.parseInt(line[2]);
                edges.get(from).add(new Bridge(from, to, cost));
                edges.get(to).add(new Bridge(to, from, cost));
            }
        }catch(IOException ignored){}
    }

    private void doSpanningTree(){
        PriorityQueue<Bridge> bridges = initFirstBridges();
        boolean[] visited = new boolean[N];
        visited[0] = true;
        int cost = 0;
        while(!bridges.isEmpty()){
            Bridge bridge = bridges.poll();
            if(visited[bridge.to]) continue;
            visited[bridge.to] = true;
            tree.get(bridge.from).add(new Bridge(bridge.from, bridge.to, bridge.cost));
            tree.get(bridge.to).add(new Bridge(bridge.to, bridge.from, bridge.cost));
            bridges.addAll(edges.get(bridge.to));
            cost += bridge.cost;
        }
        System.out.println(cost);
    }

    private PriorityQueue<Bridge> initFirstBridges(){
        PriorityQueue<Bridge> bridges = new PriorityQueue<>();
        bridges.addAll(edges.get(0));
        return bridges;
    }

    private void calculateMaxTreeSize(){
        findStartIdx(-1, 0, 0);
        int maxTreeSize = getMaxTreeSize(-1, treeStartIdx, 0);
        System.out.println(maxTreeSize);
    }

    private void findStartIdx(int befIdx, int idx, int cost){
        if(befIdx == idx) return;
        if(tree.get(idx).size() == 1 && befIdx != -1){
            if(cost > treeCost){
                treeStartIdx = idx;
                treeCost = cost;
            }
            return;
        }
        for(int i = 0; i < tree.get(idx).size(); i++){
            int nextIdx = tree.get(idx).get(i).to;
            if(nextIdx == befIdx) continue;
            findStartIdx(idx, nextIdx, cost + tree.get(idx).get(i).cost);
        }
    }

    private int getMaxTreeSize(int befIdx, int idx, int cost){
        if(befIdx == idx) return 0;
        if(tree.get(idx).size() == 1 && befIdx != -1) return cost;
        int ans = 0;
        for(int i = 0; i < tree.get(idx).size(); i++){
            int nextIdx = tree.get(idx).get(i).to;
            if(nextIdx == befIdx) continue;
            ans = Math.max(ans, getMaxTreeSize(idx, nextIdx, cost + tree.get(idx).get(i).cost));
        }
        return ans;
    }

    private static class Bridge implements Comparable<Bridge>{

        private final int from, to, cost;

        public Bridge(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Bridge bridge){
            if(this.cost > bridge.cost) return 1;
            if(this.cost < bridge.cost) return -1;
            return 0;
        }

    }

}
