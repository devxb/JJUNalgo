import java.util.*;
import static java.lang.Math.max;

class Solution {
    
    public int solution(String[] lines) {
        List<Log> logs = this.initLog(lines);
        return calcOperationOfSeconds(logs);
    }
    
    private List<Log> initLog(String[] lines){
        List<Log> logs = new ArrayList<Log>(lines.length);
        for(int i = 0; i < lines.length; i++) logs.add(new Log(lines[i]));
        return logs;
    }
    
    private int calcOperationOfSeconds(List<Log> logs){
        int operationCount = 0;
        for(int i = 0; i < logs.size(); i++){
            Log standardLog = logs.get(i);
            int tempOperationCount = 1;
            for(int j = i+1; j < logs.size(); j++){
                if(!standardLog.isLogIncluded(logs.get(j))) continue;
                tempOperationCount++;
            }
            operationCount = max(operationCount, tempOperationCount);
        }
        return operationCount;
    }
    
    private static class Log {
        
        private long startSeconds;
        private long endSeconds;
        
        public boolean isLogIncluded(Log includedLog){
            if(this.endSeconds + 1000 > includedLog.getStartSeconds()) return true;
            return false;
        }
        
        public long getStartSeconds(){
            return this.startSeconds;
        }
        
        public long getEndSeconds(){
            return this.endSeconds;
        }
        
        public Log(String logLine){
            String[] splitedLogLine = logLine.split(" ");
            String date = splitedLogLine[1];
            String operationTime = splitedLogLine[2];
            this.parseEndSeconds(date, operationTime);
        }
        
        private void parseEndSeconds(String date, String operationTime){
            String[] times = date.split(":");
            long hourSeconds = Long.parseLong(times[0])*3600 * 1000L;
            long minuteSeconds = Long.parseLong(times[1])*60 * 1000L;
            long seconds = (long)(Double.parseDouble(times[2]) * 1000L);
            this.endSeconds = hourSeconds + minuteSeconds + seconds;
            this.parseStartSeconds(hourSeconds + minuteSeconds + seconds, operationTime);
        }
        
        private void parseStartSeconds(long endSeconds, String operationTime){
            long operationSeconds = (long)(
                Double.parseDouble(operationTime.substring(0, operationTime.length()-1))
                *1000L);
            this.startSeconds = (endSeconds-operationSeconds)+1;
        }
        
    }
    
}
