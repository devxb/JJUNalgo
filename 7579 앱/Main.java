
import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args){
        Solve solve = new Solve();
        solve.run();
    }
    
}

class App implements Comparable<App>{
    public int memory, cost;
    
    public App(int memory, int cost){
        this.memory = memory;
        this.cost = cost;
    }
    
    @Override
    public int compareTo(App a){
        if(this.cost > a.cost) return 1;
        else if(this.cost < a.cost) return -1;
        if(this.cost == a.cost){
            if(this.memory > a.memory) return 1;
            else if(this.memory < a.memory) return 1;
        }
        return 0;
    }
}

class Solve{
    
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private int N,M;
    private int[][] dp;
    private ArrayList<App> applications = new ArrayList<App>();
    
    public void run(){
        input();
        System.out.println(getMinimumCost());
    }
    
    private void input(){
        try{
            String[] read = br.readLine().split(" ");
            N = Integer.parseInt(read[0]);
            M = Integer.parseInt(read[1]);
            dp = new int[100*100+5][N+5];
            read = br.readLine().split(" ");
            String[] read2 = br.readLine().split(" ");
            for(int i = 0; i < N; i++){
                applications.add(new App(Integer.parseInt(read[i]),Integer.parseInt(read2[i])));
            }
            applications.add(new App(-1, -1)); // 1번 인덱스 부터 시작하기위해 바닥값 설정
            Collections.sort(applications);
        } catch(IOException ioe){}
    }
    
    private int getMinimumCost(){
        for(int ableCost = 0; ableCost <= 100*100; ableCost++){
            for(int appNum = 1; appNum <= N; appNum++){
                if(ableCost > 0)dp[ableCost][appNum] = Math.max(dp[ableCost-1][appNum],dp[ableCost][appNum]);
                dp[ableCost][appNum] = Math.max(dp[ableCost][appNum-1],dp[ableCost][appNum]);
                App app = applications.get(appNum);
                if(app.cost <= ableCost) {
                    dp[ableCost][appNum] = Math.max(
                    dp[ableCost-app.cost][appNum-1]+app.memory
                    ,dp[ableCost][appNum]
                    );
                }
                if(dp[ableCost][appNum] >= M) return ableCost;
            }
        }
        return 100*100; // 모든 앱을 비활성화 해야할때
    }
}
