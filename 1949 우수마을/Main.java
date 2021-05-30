// 7
import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args){
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private int N;
    private int[] citizen;
    private ArrayList<ArrayList<Integer> > node = new ArrayList<ArrayList<Integer> >();
    private PriorityQueue<Village> visitOrder = new PriorityQueue<Village>(Collections.reverseOrder());
    private boolean visitCheck[];
    private int[][] dp;
    
    public void run(){
        input();
        getVisitOrder(0, 1);
        System.out.println(getDp());
    }
    
    private int getDp(){
        int lastIdx = 1;
        while(!visitOrder.isEmpty()){
            int nowIdx = visitOrder.peek().num;
            visitOrder.poll();
            lastIdx = nowIdx; // 마지막 방문 마을 저장 1번마을부터 시작했으니 항상 1이긴할듯?
            // 자식노드들이 선택되지 않은경우 와 선택된 경우를 구해줌 
            dp[2][nowIdx] = citizen[nowIdx]; // 자식들이 선택되지않는다면 자신은 선택할수있으므로 2에 저장할것임.
            dp[1][nowIdx] = 0; // 마찬가지로 자식들이 선택되었다면 자신은 선택할수없으므로 0 으로 초기화
            for(int i = 0; i < node.get(nowIdx).size(); i++){
                int sonIdx = node.get(nowIdx).get(i);
                if(dp[1][sonIdx] != -1) dp[2][nowIdx] += dp[1][sonIdx]; // dp값이 -1인 경우, 초기화가 안되어있기때문에 자신의 부모노드임
                if(dp[2][sonIdx] != -1) dp[1][nowIdx] += Math.max(dp[1][sonIdx],dp[2][sonIdx]); // 선택 되지않은경우, 이전값이 선택된지 선택되지않았는지 알필요없음
            }
            
        }
        return Math.max(dp[1][lastIdx],dp[2][lastIdx]);
    }
    
    private void getVisitOrder(int cnt, int idx){
        visitCheck[idx] = true;
        if(node.get(idx).size() == 1 && visitCheck[node.get(idx).get(0)]){
            Village v = new Village(cnt, idx);
            visitOrder.add(v);
            return;
        }
        for(int i = 0; i < node.get(idx).size(); i++){
            int nextIdx = node.get(idx).get(i);
            int nextCnt = cnt+1;
            if(visitCheck[nextIdx]) continue;
            getVisitOrder(nextCnt, nextIdx);
        }
        Village v = new Village(cnt, idx);
        visitOrder.add(v);
        return;
    }
    
    private void input(){
        try{
            String[] read = br.readLine().split(" ");
            N = Integer.parseInt(read[0]);
        
            citizen = new int[N+5];
            visitCheck = new boolean[N+5];
            dp = new int[5][N+5];
            
            read = br.readLine().split(" ");
            for(int i = 1; i <= N; i++){
                node.add(new ArrayList<Integer>());
                citizen[i] = Integer.parseInt(read[i-1]);
                dp[1][i] = -1;
                dp[2][i] = -1;
            }
            
            node.add(new ArrayList<Integer>()); // 여분 노드 2개 생성
            node.add(new ArrayList<Integer>());
            
            for(int i = 1; i < N; i++){
                read = br.readLine().split(" ");
                int from = Integer.parseInt(read[0]);
                int to = Integer.parseInt(read[1]);
                node.get(from).add(to);
                node.get(to).add(from);
            }
            
        } catch (IOException ioe){ }
    }
    
}

class Village implements Comparable<Village>{
    
    public int order;
    public int num;
    
    public Village(int order, int num){
        this.order = order;
        this.num = num;
    }
    
    @Override
    public int compareTo(Village v){
        if(this.order > v.order) return 1;
        if(this.order < v.order) return -1;
        if(this.order == v.order){
            if(this.num > v.num) return 1;
            if(this.num < v.num) return -1;
        }
        return 0;
    }
    
}
