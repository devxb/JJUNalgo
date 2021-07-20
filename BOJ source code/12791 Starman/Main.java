import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args) throws Exception{
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    ArrayList<ArrayList<String> > album;
    
    public void run() throws Exception{
        initial();
        int Q = Integer.parseInt(br.readLine());
        for(int i = 0; i < Q; i++){
            String[] read = br.readLine().split(" ");
            int from = Integer.parseInt(read[0]);
            int to = Integer.parseInt(read[1]);
            String answerString = "";
            int answerInt = 0;
            for(int year = from; year <= to; year++){
                for(int song = 0; song < album.get(year).size(); song++){
                    answerInt++;
                    answerString += year + " " + album.get(year).get(song) + "\n";
                }
            }
            System.out.println(answerInt);
            System.out.print(answerString);
        }
    }
    
    private void initial(){
        album = new ArrayList<ArrayList<String>>(2100);
        for(int i = 0; i <= 2017; i++) album.add(new ArrayList<String>());
        album.get(1967).add("DavidBowie");
        album.get(1969).add("SpaceOddity");
        album.get(1970).add("TheManWhoSoldTheWorld");
        album.get(1971).add("HunkyDory");
        album.get(1972).add("TheRiseAndFallOfZiggyStardustAndTheSpidersFromMars");
        album.get(1973).add("AladdinSane");
        album.get(1973).add("PinUps");
        album.get(1974).add("DiamondDogs");
        album.get(1975).add("YoungAmericans");
        album.get(1976).add("StationToStation");
        album.get(1977).add("Low");
        album.get(1977).add("Heroes");
        album.get(1979).add("Lodger");
        album.get(1980).add("ScaryMonstersAndSuperCreeps");
        album.get(1983).add("LetsDance");
        album.get(1984).add("Tonight");
        album.get(1987).add("NeverLetMeDown");
        album.get(1993).add("BlackTieWhiteNoise");
        album.get(1995).add("1.Outside");
        album.get(1997).add("Earthling");
        album.get(1999).add("Hours");
        album.get(2002).add("Heathen");
        album.get(2003).add("Reality");
        album.get(2013).add("TheNextDay");
        album.get(2016).add("BlackStar");
    }
    
    
}
