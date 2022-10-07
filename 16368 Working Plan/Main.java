import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main{

    private int persons, days, consWork, needRest;
    private List<Person> personList = new ArrayList<>();
    private int[] needToWork;
    private int[] worked;

    public static void main(String[] args){
        (new Main()).solve();
    }

    private void solve(){
        input();
        boolean isCanMake = makePlan();
        if(!isCanMake) {
            System.out.println(-1);
            return;
        }
        Collections.sort(personList, (o1, o2)->{
            if(o1.idx > o2.idx) return 1;
            if(o1.idx < o2.idx) return -1;
            return 0;
        });
        StringBuilder result = new StringBuilder();
        result.append(1).append("\n");
        for(Person person : personList){
            for(Integer workStartedAt : person.startedAt){
                result.append(workStartedAt).append(" ");
            }
            result.append("\n");
        }
        System.out.println(result);
    }

    private void input(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            String[] line = br.readLine().split(" ");
            persons = Integer.parseInt(line[0]);
            days = Integer.parseInt(line[1]);
            needToWork = new int[days+1];
            worked = new int[days+1];
            consWork = Integer.parseInt(line[2]);
            needRest = Integer.parseInt(line[3]);
            line = br.readLine().split(" ");
            for(int i = 0; i < persons; i++) personList.add(new Person(i+1, Integer.parseInt(line[i])));
            line = br.readLine().split(" ");
            for(int i = 0; i < days; i++)
                needToWork[i+1] = Integer.parseInt(line[i]);
        }catch(IOException IOE){}
    }

    private boolean makePlan(){
        for(int nowDay = 1; nowDay < needToWork.length; nowDay++) {
            sortPersonList();
            int tempNeedToWork = needToWork[nowDay];
            if(tempNeedToWork <= 0) continue;
            for(int p = 0; p < personList.size() && needToWork[nowDay] > 0; p++){
                Person person = personList.get(p);
                if(person.restAt >= nowDay || person.canWork < Math.min(consWork, needToWork.length-nowDay)) continue;
                needToWork[nowDay]--;
                int workAt = nowDay + consWork - 1;
                person.restAt = workAt + needRest;
                person.canWork -= consWork;
                person.startedAt.add(nowDay);
            }
            if(needToWork[nowDay] > 0) return false;
            for(int nw = nowDay+1; nw <= nowDay+consWork-1; nw++) needToWork[nw] -= tempNeedToWork;
        }
        return true;
    }

    private void sortPersonList(){
        Collections.sort(personList, (o1, o2) -> {
            if(o1.canWork > o2.canWork) return -1;
            if(o1.canWork < o2.canWork) return 1;
            return 0;
        });
    }

    private static class Person{
        int idx, canWork, restAt;
        List<Integer> startedAt;
        public Person(int idx, int canWork){
            this.idx = idx;
            this.canWork = canWork;
            startedAt = new ArrayList<>();
        }
    }

}
