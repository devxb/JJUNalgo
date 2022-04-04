import java.util.*;

class Solution {
    
    private static class Music implements Comparable<Music>{
        
        private final int genrePriority;
        private final int personalNumber;
        private final int musicPlays;
        
        public Music(int genrePriority, int personalNumber, int musicPlays){
            this.genrePriority = genrePriority;
            this.personalNumber = personalNumber;
            this.musicPlays = musicPlays;
        }
        
        @Override
        public int compareTo(Music music){
            if(this.genrePriority < music.getGenrePriority()) return 1;
            if(this.genrePriority > music.getGenrePriority()) return -1;
            if(this.musicPlays < music.getMusicPlays()) return 1;
            if(this.musicPlays > music.getMusicPlays()) return -1;
            if(this.personalNumber > music.getPersonalNumber()) return 1;
            if(this.personalNumber < music.getPersonalNumber()) return -1;
            return 0;
        }
        
        public int getGenrePriority(){
            return this.genrePriority;
        }
        
        public int getPersonalNumber(){
            return this.personalNumber;
        }
        
        public int getMusicPlays(){
            return this.musicPlays;
        }
        
    }
    
    private static class GenrePlays implements Comparable<GenrePlays>{
        
        private final String genre;
        private final int plays;
        
        public GenrePlays(String genre, int plays){
            this.genre = genre;
            this.plays = plays;
        }
        
        @Override
        public int compareTo(GenrePlays genrePlays){
            if(this.plays > genrePlays.getPlays()) return 1;
            if(this.plays < genrePlays.getPlays()) return -1;
            return 0;
        }
        
        public String getGenre(){
            return this.genre;
        }
        
        public int getPlays(){
            return this.plays;
        }
        
    }
    
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> genreTotalPlays = this.getGenreTotalPlays(genres, plays);
        List<GenrePlays> genrePerPriority = this.getGenrePerPriority(genreTotalPlays);  
        List<Music> musics = this.getMusics(genres, plays, genrePerPriority);
        return this.convertMusicsToIntArray(musics);
    }
    
    private Map<String, Integer> getGenreTotalPlays(String[] genres, int[] plays){
        Map<String, Integer> genreTotalPlays = new HashMap<String, Integer>(genres.length); 
        for(int i = 0; i < genres.length; i++){
            Integer genrePlays = genreTotalPlays.getOrDefault(genres[i], 0);
            genreTotalPlays.put(genres[i], genrePlays+plays[i]);
        }
        return genreTotalPlays;
    }
    
    private ArrayList<GenrePlays> getGenrePerPriority(Map<String, Integer> genreTotalPlays){
        ArrayList<GenrePlays> genrePerPriority = new ArrayList<GenrePlays>();
        Map<String, Integer> genreDupCheck = new HashMap<String, Integer>();
        for(Map.Entry<String, Integer> mapArg : genreTotalPlays.entrySet()){
            Integer genreFindedNum = genreDupCheck.getOrDefault(mapArg.getKey(), 0);
            if(genreFindedNum >= 2) continue;
            genreDupCheck.put(mapArg.getKey(), genreFindedNum+1);
            genrePerPriority.add(new GenrePlays(mapArg.getKey(), mapArg.getValue()));
        }
        Collections.sort(genrePerPriority);
        return genrePerPriority;
    }
    
    private ArrayList<Music> getMusics(String[] genres, int[] plays, List<GenrePlays> genrePlays){
        ArrayList<Music> musics = new ArrayList<Music>(genres.length);
        Map<String, Integer> hashGenrePlays = this.convertListGenrePlaysToHashGenrePlays(genrePlays);
        for(int i = 0; i < genres.length; i++) 
            musics.add(new Music((int)hashGenrePlays.get(genres[i]), i, plays[i]));
        Collections.sort(musics);
        return musics;
    }
    
    private Map<String, Integer> convertListGenrePlaysToHashGenrePlays(List<GenrePlays> genrePlays){
        Map<String, Integer> hashGenrePlays = new HashMap<String, Integer>(genrePlays.size());
        for(int i = 0; i < genrePlays.size(); i++) 
            hashGenrePlays.put(genrePlays.get(i).getGenre(), i);
        return hashGenrePlays;
    }
    
    private int[] convertMusicsToIntArray(List<Music> musics){
        List<Integer> intArrayTempList = new ArrayList<Integer>();
        int[] genreDupCheck = new int[musics.size()];
        for(int i = 0; i < musics.size(); i++){
            if(genreDupCheck[musics.get(i).getGenrePriority()] >= 2) continue;
            genreDupCheck[musics.get(i).getGenrePriority()]++;
            intArrayTempList.add(musics.get(i).getPersonalNumber());
        }
        int[] intArray = new int[intArrayTempList.size()];
        for(int i = 0; i < intArrayTempList.size(); i++) intArray[i] = intArrayTempList.get(i);
        return intArray;
    }
    
}
