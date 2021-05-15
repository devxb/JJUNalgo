package algorithm;

import java.io.*;
import java.util.*;

public class Main{
    
    public static void main(String[] args){
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Hole implements Comparable<Hole>{
    public int y, x, idx;
    private Hole hole;
    
    public Hole(int y, int x){
        set(y,x);
    }
    
    private void set(int y, int x){
        this.y = y;
        this.x = x;
    }
    
    public void connect(Hole warp){
        this.hole = warp;
        warp.hole = this;
    }
    
    public Hole getHole(){
        return this.hole;
    }
    
    @Override
    public int compareTo(Hole hole){
        if(this.y > hole.y) return 1;
        if(this.y < hole.y) return -1;
        if(this.y == hole.y){
            if(this.x > hole.x) return 1;
            if(this.x < hole.x) return -1;
        }
        return 0;
    }
    
}

class Solve{
    
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private int N;
    private boolean[] connected;
    private ArrayList<Hole> wormHoles = new ArrayList<Hole>();
    
    public void run(){
        input();
        System.out.println(connectWormHole(0, 0));
    }
    
    private boolean loopCheck(Hole hole){ // true : loop, false : noloop
        boolean[] visited = new boolean[N+5];
        while(true){
            if(visited[hole.idx]) return true; // 이미 들어갔던 웜홀인지 체크 맞다면 return true
            visited[hole.idx] = true; // 아니라면 true
            hole = hole.getHole(); // 연결된 웜홀로 나오기
            hole = nextHole(hole); // +x 방향으로 이동했을때 다음 구멍 찾기
            if(hole == null){ // 다음 구멍이 없다면, 
                return false;
            }
        }
    }
    
    private Hole nextHole(Hole hole){
        for(int i = 0; i < N; i++){
            Hole wormHole = wormHoles.get(i);
            if(wormHole.y == hole.y && wormHole.x > hole.x) return wormHole;
        }
        return null;
    }
    
    private int connectWormHole(int idx, int connect){
        int ret = 0;
        if(connect == N){
            for(int i = 0; i < N; i++){
                if(loopCheck(wormHoles.get(i))) return 1;
            }
            return 0;
        }
        for(int A = idx; A < N; A++){
            if(connected[A]) continue;
            connected[A] = true;
            for(int B = A+1; B < N; B++){
                if(connected[B]) continue;
                connected[B] = true;
                wormHoles.get(A).connect(wormHoles.get(B));
                ret += connectWormHole(A+1, connect+2);
                connected[B] = false;
            }
            connected[A] = false;
        }
        return ret;
    }
    
    private void input(){
        try{
            String[] read = br.readLine().split(" ");
            N = Integer.parseInt(read[0]);
            connected = new boolean[N+5];
            for(int i = 0; i < N; i++){
                read = br.readLine().split(" ");
                int x = Integer.parseInt(read[0]);
                int y = Integer.parseInt(read[1]);
                wormHoles.add(new Hole(y,x));
            }
            Collections.sort(wormHoles);
            for(int i = 0; i < N; i++) wormHoles.get(i).idx = i;
        } catch (IOException ioe){}
    }
    
}
