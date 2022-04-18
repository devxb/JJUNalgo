import java.util.*;

class Solution {
    
    public String solution(int n, int t, int m, String[] timetable) {
        List<Shuttle> shuttles = this.setShuttles(n, t, m);
        Queue<Crew> crews = this.arrayToQueue(timetable);
        this.pickCrews(crews, shuttles);
        return this.pickCon(shuttles);
    }
    
    private List<Shuttle> setShuttles(int n, int t, int m){
        ArrivalTime arrival = new ArrivalTime("09:00");
        List<Shuttle> shuttles = new ArrayList<Shuttle>(n);
        for(int i = 0; i < n; i++){
            shuttles.add(new Shuttle(m, arrival));
            arrival.plusMinutes(t);
        }
        return shuttles;
    }
    
    private Queue<Crew> arrayToQueue(String[] timetable){
        List<Crew> crewsList = this.arrayToList(timetable);
        Queue<Crew> crewsQueue = new LinkedList<Crew>();
        for(Crew crew : crewsList) crewsQueue.add(crew);
        return crewsQueue;
    }
    
    private List<Crew> arrayToList(String[] timetable){
        List<Crew> crewsList = new ArrayList<Crew>(timetable.length);
        for(String time : timetable) crewsList.add(new Crew(time));
        Collections.sort(crewsList);
        return crewsList;
    }
    
    private void pickCrews(Queue<Crew> crews, List<Shuttle> shuttles){
        int shuttleIdx = 0;
        Shuttle nowShuttle = shuttles.get(shuttleIdx);
        while(!crews.isEmpty() && shuttleIdx < shuttles.size()){
            Crew nowCrew = crews.peek();
            boolean pickResult = nowShuttle.accept(nowCrew);
            if(!pickResult){
                shuttleIdx++;
                if(shuttleIdx == shuttles.size()) break;
                nowShuttle = shuttles.get(shuttleIdx);
                continue;
            }
            crews.poll();
        }
    }
    
    private String pickCon(List<Shuttle> shuttles){
        Shuttle lastShuttle = shuttles.get(shuttles.size()-1);
        try{
            if(lastShuttle.isFull()) {
                ArrivalTime arrivalTime = lastShuttle.getLastArrival();
                arrivalTime.minusOneMinutes();
                return arrivalTime.toString();
            }
            else return lastShuttle.arrival.toString();
        }catch(NullPointerException npe){
            return lastShuttle.arrival.toString();
        }
    }
    
    private static class Crew extends ArrivalTime implements Comparable<Crew>{
        
        public Crew(String date){
            super(date);
        }
        
        @Override
        public int compareTo(Crew crew){
            if(crew instanceof ArrivalTime){
                boolean faster = super.isFaster(crew);
                if(faster) return 1;
                return -1;
            }
            return -1;
        }
        
    }
    
    private static class Shuttle{
        
        private final ArrivalTime arrival;
        private ArrivalTime lastArrival = null;
        private int capacity;
        private final int maximumCapacity;
        
        public Shuttle(int maximumCapacity, ArrivalTime arrival){
            this.arrival = new ArrivalTime(arrival);
            this.capacity = 0;
            this.maximumCapacity = maximumCapacity;
        }
        
        public ArrivalTime getLastArrival() throws NullPointerException{
            return this.lastArrival;
        }
        
        public boolean isFull(){
            return this.maximumCapacity <= this.capacity;
        }
        
        public boolean accept(ArrivalTime arrivalTime){
            if(this.isFull() || !this.arrival.isFaster(arrivalTime)) return false;
            this.lastArrival = arrivalTime;
            this.capacity++;
            return true;
        }
        
    }
    
    private static class ArrivalTime{
        
        private int hour;
        private int minutes;
        
        public ArrivalTime(ArrivalTime arrivalTime){
            this.hour = arrivalTime.getHour();
            this.minutes = arrivalTime.getMinutes();
        }
        
        public ArrivalTime(String date){
            String[] hourAndMinutes = date.split(":");
            this.hour = Integer.parseInt(hourAndMinutes[0], 10);
            this.minutes = Integer.parseInt(hourAndMinutes[1], 10);
        }
        
        public boolean isFaster(ArrivalTime arrival){
            if(this.hour > arrival.getHour()) return true;
            if(this.hour == arrival.getHour() 
               && this.minutes >= arrival.getMinutes()) return true;
            return false;
        }
        
        public int getHour(){
            return this.hour;
        }
        
        public int getMinutes(){
            return this.minutes;
        }
        
        public void plusMinutes(int minutes){
            this.minutes += minutes;
            this.plusHour(this.minutes/60);
            this.minutes = this.minutes%60;
        }
        
        public void minusOneMinutes(){
            this.minutes--;
            if(minutes < 0){
                this.minutes = 59;
                this.plusHour(-1);
            }
        }
        
        public void plusHour(int hour){
            this.hour += hour;
        }
        
        @Override
        public String toString(){
            String hour = "";
            String minutes = "";
            if(this.hour < 10) hour = "0"+this.hour;
            else hour = ""+this.hour;
            if(this.minutes < 10) minutes = "0"+this.minutes;
            else minutes = ""+this.minutes;
            return hour + ":" + minutes;
        }
        
    }
    
}
