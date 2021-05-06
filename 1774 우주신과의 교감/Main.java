import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args){
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Pair{
    public double first, second;
    public Pair(double first, double second){
        this.first = first;
        this.second = second;
    }
    
    public double getDistance(Pair p){
        return Math.sqrt(Math.pow(this.first - p.first,2)+Math.pow(this.second - p.second,2));
    }
}

class PairWithDistance implements Comparable<PairWithDistance>{
    public double distance;
    public int first, second;
    public PairWithDistance(double distance, int first, int second){
        this.distance = distance;
        this.first = first;
        this.second = second;
    }
    
    @Override
    public int compareTo(PairWithDistance pwd){
        if(this.distance > pwd.distance) return 1;
        if(this.distance < pwd.distance) return -1;
        return 0;
    }
    
}

class Solve{
    
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private ArrayList<Pair> position = new ArrayList<Pair>();
    private PriorityQueue<PairWithDistance> pq = new PriorityQueue<PairWithDistance>();
    private int[] union;
    
    public void run(){
        input();
        setKruskal();
        System.out.printf("%.2f\n",getMinPath());
    }
    
    private int setUnion(int idx){
        if(union[idx] == idx) return idx;
        return union[idx] = setUnion(union[idx]);
    }
    
    private void updateUnion(int idx1, int idx2){
        int son = setUnion(idx1), par = setUnion(idx2);
        if(idx1 < idx2){
            par = setUnion(idx1);
            son = setUnion(idx2);
        }
        union[son] = setUnion(union[par]);
    }
    
    private void setKruskal(){
        for(int i = 1; i < position.size(); i++){
            for(int j = 1; j < position.size(); j++){
                if(i == j) continue;
                double distance = position.get(i).getDistance(position.get(j));
                pq.add(new PairWithDistance(distance, i, j));
            }
        }
    }
    
    private double getMinPath(){
        double ret = 0;
        while(!pq.isEmpty()){
            double nowDis = pq.peek().distance;
            int p1 = pq.peek().first;
            int p2 = pq.peek().second;
            pq.poll();
            if(setUnion(p1) == setUnion(p2)) continue;
            ret += nowDis;
            updateUnion(p1, p2);
        }
        return ret;
    }
    
    private void input(){
        try{
            String temp[] = br.readLine().split(" ");
            int N = Integer.parseInt(temp[0]);
            int M = Integer.parseInt(temp[1]);
            union = new int[N+5];
            position.add(new Pair(-987654321, -987654321));
            for(int i = 1; i <= N; i++){
                union[i] = i;
                temp = br.readLine().split(" ");
                position.add(new Pair(Double.parseDouble(temp[0]),Double.parseDouble(temp[1])));
            }
            for(int i = 1; i <= M; i++){
                temp = br.readLine().split(" ");
                updateUnion(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]));
            }
        } catch(IOException ioe){}
    }
    
}
