import java.util.Deque;
import java.util.LinkedList;
import java.util.Collections;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main{
    
    public static void main(String[] args){
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    private static class Ball implements Comparable<Ball>{
        
        private int size, color, pri;
        
        public Ball(int color, int size, int pri){
            this.size = size;
            this.color = color;
            this.pri = pri;
        }
        
        @Override
        public int compareTo(Ball ball){
            if(this.size > ball.size) return 1;
            if(this.size < ball.size) return -1;
            if(this.size == ball.size){
                if(this.color > ball.color) return 1;
                if(this.color < ball.color) return -1;
            }
            return 0;
        }
        
    }
    
    private static class Pair implements Comparable<Pair>{
        
        private int pri, size;
        
        public Pair(int pri, int size){
            this.pri = pri;
            this.size = size;
        }
        
        @Override
        public int compareTo(Pair p){
            if(this.pri > p.pri) return 1;
            if(this.pri < p.pri) return -1;
            return 0;
        }
        
    }
    
    private int N;
    private int[] dupSize;
    private int[] sameColor;
    private ArrayList<Ball> balls;
    
    public void run(){
        input();
        huntBalls();
    }
    
    private void huntBalls(){
        int totalSize = 0;
        ArrayList<Pair> printer = new ArrayList<Pair>();
        Ball befBall = null;
        for(Ball ball : balls){
            int num = totalSize - dupSize[ball.size] - sameColor[ball.color];
            Pair p = null;
            if(befBall != null && befBall.size == ball.size && befBall.color == ball.color) p = new Pair(ball.pri, befBall.pri);
            else p = new Pair(ball.pri, num);
            printer.add(p);
            totalSize += ball.size;
            dupSize[ball.size] += ball.size;
            sameColor[ball.color] += ball.size;
            befBall = ball;
            befBall.pri = p.size;
        }
        Collections.sort(printer); 
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < printer.size(); i++) {
            Pair p = printer.get(i);
            sb.append(p.size);
            if(i == printer.size()-1) continue;
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
    
    private void input(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            String[] read = br.readLine().split(" ");
            N = Integer.parseInt(read[0]);
            dupSize = new int[2001];
            sameColor = new int[N+1];
            balls = new ArrayList<Ball>();
            for(int i = 1; i <= N; i++){
                read = br.readLine().split(" ");
                int color = Integer.parseInt(read[0]);
                int size = Integer.parseInt(read[1]);
                balls.add(new Ball(color, size, i));
            }
            Collections.sort(balls);
        }catch(IOException IOE){}
    }
    
}
