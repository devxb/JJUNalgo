import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args) throws Exception{
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Position{
    
    public int y;
    public int x;
    public Position(int y, int x){
        this.y = y;
        this.x = x;
    }
    
}

class Node implements Comparable<Node>{
    public int weight;
    public int y;
    public int x;
    
    public Node(int weight, int y, int x){
        this.weight = weight;
        this.y = y;
        this.x = x;
    }
    
    public int compareTo(Node node){
        if(this.weight > node.weight) return 1;
        if(this.weight < node.weight) return -1;
        return 0;
    }
    
}

class Solve{
    
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private int N, M;
    private String[] read;
    private char[][] arr;
    private boolean[][] check;
    private ArrayList<Position> pos = new ArrayList<Position>();
    private PriorityQueue<Node> pq = new PriorityQueue<Node>();
    private int[] dy = {-1, 0, 1, 0};
    private int[] dx = {0, -1, 0, 1};
    public void run() throws Exception{
        input();
        System.out.println(doMST());
    }
    
    private void input() throws Exception{
        read = br.readLine().split(" ");
        N = Integer.parseInt(read[0]);
        M = Integer.parseInt(read[1]);
        arr = new char[N+5][N+5];
        check = new boolean[N+5][N+5];
        for(int i = 0; i < N; i++){
            String line =  br.readLine();  
            for(int j = 0; j < N; j++){
                arr[i][j] =  line.charAt(j);
                if(arr[i][j] == 'K' || arr[i][j] == 'S') check[i][j] = true;
                if(arr[i][j] == 'S') pq.add(new Node(0, i, j));
            }
        }
    }
    
    private int doMST(){
        int ret = 0;
        int findCount = -1; // 출입구 제거하기위해 -1로 지정
        while(!pq.isEmpty()){
            int nowY = pq.peek().y;
            int nowX = pq.peek().x;
            int nowWeight = pq.peek().weight;
            pq.poll();
            if(!check[nowY][nowX]) continue;
            check[nowY][nowX] = false;
            ret += nowWeight;
            findCount++;
            setWeight(nowY, nowX);
        }
        if(findCount < M) return -1;
        return ret;
    }
    
    private void setWeight(int y, int x){
        boolean[][] check = new boolean[N+5][N+5];
        Queue<Node> q = new LinkedList<Node>();
        q.add(new Node(0, y, x));
        while(!q.isEmpty()){
            int nowY = q.peek().y;
            int nowX = q.peek().x;
            int nowWeight = q.peek().weight;
            q.poll();
            if(check[nowY][nowX]) continue;
            check[nowY][nowX] = true;
            if(arr[nowY][nowX] == 'K') pq.add(new Node(nowWeight, nowY, nowX));
            for(int i = 0; i < 4; i++){
                int nextWeight = nowWeight+1;
                int nextY = nowY + dy[i];
                int nextX = nowX + dx[i];
                if(arr[nextY][nextX] == '1' || check[nextY][nextX]) continue;
                q.add(new Node(nextWeight ,nextY, nextX));
            }
        }
    }
    
}
