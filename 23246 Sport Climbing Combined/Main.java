import java.io.*;
import java.util.*;

public class Main{

    private List<Player> players = new ArrayList<>();

    public static void main(String[] args){
        (new Main()).solve();
    }

    private void solve(){
        input();
        for(int i = 0; i < 3; i++) System.out.print(players.get(i).playerNumber + " ");
    }

    private void input(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            input(br);
        }catch(IOException IOE){}
    }

    private void input(BufferedReader br) throws IOException{
        String[] read = br.readLine().split(" ");
        int playerCount = Integer.parseInt(read[0]);
        for(int i = 0; i < playerCount; i++){
            read = br.readLine().split(" ");
            Player player = new Player(
                    Integer.parseInt(read[0]),
                    Integer.parseInt(read[1]),
                    Integer.parseInt(read[2]),
                    Integer.parseInt(read[3])
            );
            players.add(player);
        }
        Collections.sort(players);
    }

    private static class Player implements Comparable<Player>{

        int playerNumber, gopScore, hapScore;

        public Player(int playerNumber, int score1, int score2, int score3){
            this.playerNumber = playerNumber;
            this.gopScore = score1*score2*score3;
            this.hapScore = score1+score2+score3;
        }

        @Override
        public int compareTo(Player p){
            if(p.gopScore < this.gopScore) return 1;
            if(p.gopScore > this.gopScore) return -1;
            if(p.hapScore < this.hapScore) return 1;
            if(p.hapScore > this.hapScore) return -1;
            if(p.playerNumber < this.playerNumber) return 1;
            if(p.playerNumber > this.playerNumber) return -1;
            return 0;
        }

    }

}
