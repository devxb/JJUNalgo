import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args) throws Exception{
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    class Computer implements Comparable<Computer>{
        public int cableLength;
        public int computer;
        
        public Computer(int cableLength, int computer){
            this.cableLength = cableLength;
            this.computer = computer;
        }
        
        @Override
        public int compareTo(Computer com){
            if(this.cableLength > com.cableLength) return 1;
            if(this.cableLength < com.cableLength) return -1;
            return 0;
        }
    }
    
    private Scanner sc = new Scanner(System.in);
    private int N, INF = 987654321, totalCableLength;
    private int[][] cable;
    
    public void run(){
        input();
        int ans = -1;
        for(int i = 0; i < N; i++){
            ans = Math.max(setCables(i),ans);
        }
        System.out.println(ans);
    }
    
    private void input(){
        N = sc.nextInt();
        cable = new int[N+5][N+5];
        for(int i = 0; i < N; i++){
            String read = sc.next();
            for(int j = 0; j < N; j++){
                char c = read.charAt(j);
                int num = 0;
                if(c == '0'){
                    cable[i][j] = INF;
                    continue;
                }
                if((int)c >= (int)'a' && (int)c <= (int)'z') num = (int)c - (int)'a' + 1;
                else num = (int)c - (int)'A' + 27;
                cable[i][j] = num;
                totalCableLength += num;
            }
        }
    }
    
    private int setCables(int startIdx){
        int ret = 0, cnt = 0;
        boolean[] check = new boolean[N+5];
        PriorityQueue<Computer> pq = new PriorityQueue<Computer>();
        pq.add(new Computer(0,startIdx));
        while(!pq.isEmpty()){
            int nowComputer = pq.peek().computer;
            int nowCableLength = pq.peek().cableLength;
            pq.poll();
            if(check[nowComputer]) continue;
            check[nowComputer] = true;
            ret += nowCableLength;
            cnt++;
            for(int i = 0; i < N; i++){
                if(cable[nowComputer][i] == INF && cable[i][nowComputer] == INF) continue;
                pq.add(new Computer(Math.min(cable[nowComputer][i],cable[i][nowComputer]), i));
            }
        }
        if(cnt < N) return -1;
        return totalCableLength - ret;
    }
    
}
