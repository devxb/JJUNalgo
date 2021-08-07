import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main{
    
    public static void main(String[] args){
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    private static class Node{
        private int idx;
        private int dis;
        
        public Node(int idx, int dis){
            this.idx = idx;
            this.dis = dis;
        }
    }
    
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private int N, M;
    private String[] read;
    private int[] check;
    public void run(){
        try{
            ArrayList<ArrayList<Node> > tree = null;
            tree = input();
            for(int i = 1; i <= M; i++){
                read = br.readLine().split(" ");
                System.out.println(getDis(tree, Integer.parseInt(read[0]), Integer.parseInt(read[1]), 0, i));
            }
        } catch (IOException IOE){}
    }
    
    private ArrayList<ArrayList<Node>> input() throws IOException{
        ArrayList<ArrayList<Node> > ret = new ArrayList<ArrayList<Node>>();
        read = br.readLine().split(" ");
        this.N = Integer.parseInt(read[0]);
        this.M = Integer.parseInt(read[1]);
        check = new int[N+5];
        for(int i = 0; i <= N; i++) ret.add(new ArrayList<Node>());
        for(int i = 1; i < N; i++){
            read = br.readLine().split(" ");
            Node node = new Node(Integer.parseInt(read[1]), Integer.parseInt(read[2]));
            Node rev_node = new Node(Integer.parseInt(read[0]), Integer.parseInt(read[2]));
            ret.get(Integer.parseInt(read[0])).add(node);
            ret.get(Integer.parseInt(read[1])).add(rev_node);
        }
        return ret;
    } 
    
    private int getDis(ArrayList<ArrayList<Node>> tree, int nowIdx, int to, int nowDis, int checkCnt){
        int ret = 0;
        if(nowIdx == to) return nowDis;
        check[nowIdx] = checkCnt;
        for(int i = 0; i < tree.get(nowIdx).size(); i++){
            int nextIdx = tree.get(nowIdx).get(i).idx;
            int nextDis = nowDis + tree.get(nowIdx).get(i).dis;
            if(check[nextIdx] == checkCnt) continue;
            ret = Math.max(ret, getDis(tree, nextIdx, to, nextDis, checkCnt));
        }
        return ret;
    }
    
}
