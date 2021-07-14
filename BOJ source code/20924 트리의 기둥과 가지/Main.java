import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args) throws Exception{
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    class Node{
        
        private int node, weight;
        
        public Node(int node, int weight){
            this.node = node;
            this.weight = weight;
        }
        
    }
    
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private String[] read;
    private boolean[] check;
    private int N, R, gigaNode;
    private ArrayList<ArrayList<Node> > edges;
    
    public void run() throws Exception{
        input();
        System.out.println(getPillar(R) + " " + getBranch(gigaNode));
    }
    
    private int getPillar(int idx){
        check[idx] = true;
        gigaNode = idx;
        if((idx == R && edges.get(idx).size() > 1) || (idx != R && edges.get(idx).size() != 2) || N == 1) 
            return 0;
        Node nextNode = edges.get(idx).get(0);
        nextNode = (check[nextNode.node] == true) ? edges.get(idx).get(1) : nextNode;
        return getPillar(nextNode.node) + nextNode.weight;
    }
    
    private int getBranch(int idx){
        check[idx] = true;
        int ret = 0;
        for(int i = 0; i < edges.get(idx).size(); i++){
            Node nextNode = edges.get(idx).get(i);
            if(check[nextNode.node]) continue;
            ret = Math.max(ret, getBranch(nextNode.node) + nextNode.weight);
        }
        return ret;
    }
    
    private void input() throws Exception{
        read = br.readLine().split(" ");
        N = Integer.parseInt(read[0]);
        R = Integer.parseInt(read[1]);
        edges = new ArrayList<ArrayList<Node> >(N+5);
        check = new boolean[N+5];
        for(int i = 0; i < N+5; i++) edges.add(new ArrayList<Node>());
        for(int i = 1; i < N; i++){
            read = br.readLine().split(" ");
            int a, b, d;
            a = Integer.parseInt(read[0]);
            b = Integer.parseInt(read[1]);
            d = Integer.parseInt(read[2]);
            edges.get(a).add(new Node(b,d));
            edges.get(b).add(new Node(a,d));
        }
    }
    
}
