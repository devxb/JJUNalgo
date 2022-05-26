import java.io.*;
import java.util.*;

public class Main{
	
	public static void main(String[] args){
		(new Main()).solve();
	}
	
	private void solve(){
		List<Galaxy> galaxies = this.inputGalaxy();
		int multiverseCount = this.countMultiverse(galaxies);
		System.out.println(multiverseCount);
	}
	
	private List<Galaxy> inputGalaxy(){
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			return this.inputGalaxy(br);
		}catch(IOException IOE){}
		return null;
	}
	
	private List<Galaxy> inputGalaxy(BufferedReader br) throws IOException{
		String[] line = br.readLine().split(" ");
		int galaxyCount = Integer.parseInt(line[0]);
		int planetCount = Integer.parseInt(line[1]);
		List<Galaxy> galaxies = new ArrayList<Galaxy>(galaxyCount);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < galaxyCount; i++){
			line = br.readLine().split(" ");
			Galaxy galaxy = new Galaxy();
			for(int j = 0; j < planetCount; j++) galaxy.addPlanet(Integer.parseInt(line[j]));
			galaxy.complete(sb);
			galaxies.add(galaxy);
		}
		return galaxies;
	}
	
	private int countMultiverse(List<Galaxy> galaxies){
		Map<Galaxy, Integer> multiverse = new HashMap<Galaxy, Integer>();
		for(Galaxy galaxy : galaxies){
			int nowGalaxyMultiverseCount = multiverse.getOrDefault(galaxy, 0);
			multiverse.put(galaxy, nowGalaxyMultiverseCount+1);
		}
		int multiverseCount = 0;
		for(Map.Entry<Galaxy, Integer> entry : multiverse.entrySet()) multiverseCount = multiverseCount + ((entry.getValue() * (entry.getValue()-1)) / 2);
		return multiverseCount;
	}
	
	private static class Galaxy{
		
		private List<Planet> planets;
		private String orderOfPlanets;
		
		public Galaxy(){
			this.planets = new ArrayList<Planet>();
		}
		
		public void addPlanet(int planetSize){
			Planet planet = new Planet(this.planets.size(), planetSize);
			this.planets.add(planet);
		}
		
		public void complete(StringBuilder sb){
			Collections.sort(this.planets);
			sb.setLength(0);
			int relSize = 0;
			for(int i = 0; i < planets.size(); i++){
				if(i > 0 && planets.get(i).size > planets.get(i-1).size) relSize++;
				sb.append(planets.get(i).order).append(relSize);
			}
			this.orderOfPlanets = sb.toString();
		}
		
		@Override
		public boolean equals(Object galaxy){
			if(!(galaxy instanceof Galaxy)) return false;
			return this.orderOfPlanets.equals(((Galaxy)galaxy).getOrderOfPlanets());
		}
		
		String getOrderOfPlanets(){
			return this.orderOfPlanets;
		}
		
		@Override
		public int hashCode(){
			return this.orderOfPlanets.hashCode();
		}
		
	}
	
	private static class Planet implements Comparable<Planet>{
		
		int order, size;
		
		public Planet(int order, int size){
			this.order = order;
			this.size = size;
		}
		
		@Override
		public int compareTo(Planet planet){
			if(this.size > planet.size) return 1;
			if(this.size < planet.size) return -1;
			if(this.size == planet.size){
				if(this.order > planet.order) return 1;
				if(this.order < planet.order) return -1;
			}
			return 0;
		}
		
	}
	
}
