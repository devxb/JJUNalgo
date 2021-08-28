import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;

public class Main{
    
    public static void main(String[] args){
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    private static class Truck{
        
        private int initTime, weight;
        
        public Truck(int initTime, int weight){
            this.initTime = initTime;
            this.weight = weight;
        }
        
    }
    
    private Queue<Truck> bridgeState = new LinkedList<Truck>();
    private int bridgeWeight;
    private int time, w, L;
    
    public void run(){
        input();
        outQueue();
        System.out.println(time);
    }
    
    private void input(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            String[] read = br.readLine().split(" ");
            int n = Integer.parseInt(read[0]);
            this.w = Integer.parseInt(read[1]);
            this.L = Integer.parseInt(read[2]);
            read = br.readLine().split(" ");
            for(int i = 0; i < n; i++) queueOperate(Integer.parseInt(read[i]));
        }catch(IOException IOE){}
    }
    
    private void queueOperate(int weight){
        this.time++;
        while(bridgeState.size() >= w || bridgeWeight+weight > L){
            if(time - bridgeState.peek().initTime >= w){
                bridgeWeight -= bridgeState.peek().weight;
                bridgeState.poll();
            }
            else time++;
        }
        bridgeWeight += weight;
        bridgeState.add(new Truck(time, weight));
    }
    
    private void outQueue(){
        while(!bridgeState.isEmpty()){
            if(time - bridgeState.peek().initTime >= w) bridgeState.poll();
            else time++;
        }
    }
    
}
