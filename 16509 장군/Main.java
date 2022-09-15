import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main{

    private static final int INF = 987654321;
    private Position pawn;
    private Position king;

    private int dy[] = {-1, -1, -1,
                        -1, -1, -1,
                        0, -1, -1,
                        0, 1, 1,
                        1, 1, 1,
                        1, 1, 1,
                        0, 1, 1,
                        0, -1, -1};
    private int dx[] = {0, -1, -1,
                        0, 1, 1,
                        1, 1, 1,
                        1, 1, 1,
                        0, 1, 1,
                        0, -1, -1,
                        -1, -1, -1,
                        -1, -1, -1};

    public static void main(String[] args){
        (new Main()).solve();
    }

    private void solve(){
        input();
        System.out.println(movePawn());
    }

    private void input(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            String[] read = br.readLine().split(" ");
            pawn = new Position(Integer.parseInt(read[0]), Integer.parseInt(read[1]));
            read = br.readLine().split(" ");
            king = new Position(Integer.parseInt(read[0]), Integer.parseInt(read[1]));
        }catch(IOException IOE){}
    }

    private int movePawn(){
        PriorityQueue<Position> pq = new PriorityQueue<>();
        pq.add(pawn);
        while(!pq.isEmpty()){
            Position nowPosition = pq.poll();
            if(nowPosition.y == king.y && nowPosition.x == king.x) return nowPosition.cnt;
            if(nowPosition.cnt > 1000000) return -1;
            for(int i = 0; i < 24; i++){
                int nextY = nowPosition.y;
                int nextX = nowPosition.x;
                int startI = i+3;
                boolean trig = true;
                for(int j = i; j < startI; j++){
                    nextY += dy[j];
                    nextX += dx[j];
                    if(isOutOfBounds(nextY, nextX) ||
                            (j+1 != startI && (nextY == king.y && nextX == king.x))) trig = false;
                }
                if(trig) pq.add(new Position(nextY, nextX, nowPosition.cnt+1));
                if(nextY == king.y && nextX == king.x) return nowPosition.cnt+1;
                i = startI-1;
            }
        }
        return -1;
    }

    private boolean isOutOfBounds(int y, int x){
        return y < 0 || x < 0 || y >= 10 || x >= 9;
    }

    private static class Position implements Comparable<Position>{

        int y, x, cnt;

        public Position(int y, int x, int cnt){
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }

        public Position(int y, int x){
            this.y = y;
            this.x = x;
            this.cnt = 0;
        }

        @Override
        public int compareTo(Position pos){
            if(this.cnt > pos.cnt) return 1;
            if(this.cnt < pos.cnt) return -1;
            return 0;
        }

    }

}
