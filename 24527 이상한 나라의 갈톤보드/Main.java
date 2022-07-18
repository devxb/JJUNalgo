// develxb's ipad.

import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Main {
    
    private int height;
    private int dropQuery, expQuery;
    private List<Query> expQueries;
    private double[] lmos;

    public static void main(String[] args){
        (new Main()).solve();
    }
    
    private void solve(){
        this.input();
        this.updateLmos();
        this.updateLmos();
        StringBuilder sb = this.query(new StringBuilder());
        System.out.print(sb.toString());
    }

    private void input(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            this.inputQuery(br);
            this.inputDropQuery(br);
            this.inputExpQuery(br);
        }catch(IOException IOE){}
    }

    private void inputQuery(BufferedReader br) throws IOException{
        String[] read = br.readLine().split(" ");
        height = Integer.parseInt(read[0]);
        read = br.readLine().split(" ");
        dropQuery = Integer.parseInt(read[0]);
        expQuery = Integer.parseInt(read[1]);
    }

    private void inputDropQuery(BufferedReader br) throws IOException{
        this.lmos = new double[this.height+5];
        for(int q = 0; q < this.dropQuery; q++){
            String[] read = br.readLine().split(" ");
            long[] fromAndTo = this.getFromTo(Long.parseLong(read[0]));
            double nowAmount = Double.parseDouble(read[1]) / (double)((double)fromAndTo[1] - (double)fromAndTo[0]+1);
            this.lmos[(int)fromAndTo[0]] += nowAmount;
            this.lmos[(int)fromAndTo[1]+1] += -1 * nowAmount;
        }
    }

    private long[] getFromTo(long idx){
        long from = 1, to = this.height, height = 1L;
        for(int i = 0; (i < 20 && from <= to); i++){
            height = (from + to) / 2;
            if((height * (height-1)) / 2 < idx && idx <= ((height+1) * height) / 2) break;
            if(((height+1) * height) / 2 < idx) from = height+1;
            else to = height;
        }
        long startIdx = (height * (height-1) / 2) + 1;
        long endIdx = (height+1) * height / 2;
        return new long[]{1+idx-startIdx, (this.height+1)-(endIdx-idx)};
    }

    private void inputExpQuery(BufferedReader br) throws IOException{
        this.expQueries = new ArrayList<Query>(this.expQuery);
        for(int q = 0; q < this.expQuery; q++){
            String[] read = br.readLine().split(" ");
            this.expQueries.add(new Query(
                Long.parseLong(read[0]),
                Long.parseLong(read[1])
            ));
        }
    }

    private void updateLmos(){
        for(int i = 1; i <= this.height+1; i++) this.lmos[i] += this.lmos[i-1];
    }

    private StringBuilder query(StringBuilder sb){
        for(Query query : this.expQueries) 
            sb.append(this.lmos[(int)query.to] - this.lmos[(int)query.from-1]).append("\n");
        return sb;
    }

    private static class Query{

        long from, to;

        public Query(long from, long to){
            this.from = from;
            this.to = to;
        }

    }

}
