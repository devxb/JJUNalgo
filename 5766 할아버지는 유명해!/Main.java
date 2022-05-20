import java.io.*;
import java.util.*;

public class Main{
	
	public static void main(String[] args){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			(new Main()).solve(br);
		}catch(IOException IOE){}
	}
	
	private void solve(BufferedReader br) throws IOException{
		String[] line = null;
		StringBuilder answer = new StringBuilder();
		while(true){
			line = br.readLine().split(" ");
			if(this.isEndOfInput(line)) break;
			int weeks = Integer.parseInt(line[0]);
			int playerCount = Integer.parseInt(line[1]);
			int[][] ranks = this.inputRank(weeks, playerCount, br);
			List<Player> players = this.sortPlayerByRank(weeks, ranks);
			this.appendSecondPlayer(answer, players);
		}
		System.out.println(answer);
	}
	
	private boolean isEndOfInput(String[] line){
		return line[0].equals("0") && line[1].equals("0");
	}
	
	private int[][] inputRank(int weeks, int playerCount, BufferedReader br) throws IOException{
		int[][] ranks = new int[weeks][playerCount];
		for(int week = 0; week < weeks; week++){
			String[] playerNumbers = br.readLine().split(" ");
			for(int player = 0; player < playerCount; player++) ranks[week][player] = Integer.parseInt(playerNumbers[player]);
		}
		return ranks;
	}
	
	private List<Player> sortPlayerByRank(int weeks, int[][] ranks){
		Player[] playerArrays = new Player[10001];
		for(int week = 0; week < weeks; week++){
			for(int playerNumber : ranks[week]){
				if(playerArrays[playerNumber] == null) playerArrays[playerNumber] = new Player(playerNumber, 1);
				else playerArrays[playerNumber].score++;
			}
		}
		List<Player> players = new ArrayList<Player>();
		for(Player player : playerArrays)
			if(player != null) players.add(player);
		Collections.sort(players);
		return players;
	}
	
	private void appendSecondPlayer(StringBuilder answer, List<Player> players){
		int idx = 0, firstScore = players.get(0).score;
		while(idx < players.size() && players.get(idx).score == firstScore) idx++;
		if(this.isAllPlayerSameScore(idx, players.size())) this.appendAllPlayers(answer, players);
		else{
			int secondScore = players.get(idx).score;
			while(idx < players.size() && players.get(idx).score == secondScore){
				answer.append(players.get(idx).number).append(" ");
				idx++;
			}
		}
		answer.append("\n");
	}
	
	private boolean isAllPlayerSameScore(int idx, int size){
		return idx == size;
	}
	
	private void appendAllPlayers(StringBuilder answer, List<Player> players){
		for(Player player : players) answer.append(player.number).append(" ");
	}
	
	private static class Player implements Comparable<Player>{
		
		int number, score;
		
		public Player(int number, int score){
			this.number = number;
			this.score = score;
		}
		
		@Override
		public int compareTo(Player player){
			if(this.score < player.score) return 1;
			if(this.score > player.score) return -1;
			if(this.number < player.number) return -1;
			if(this.number > player.number) return 1;
			return 0;
		}
		
	}
	
}
