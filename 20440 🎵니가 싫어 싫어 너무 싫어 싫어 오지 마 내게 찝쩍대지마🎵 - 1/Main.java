import java.io.*;
import java.util.*;

public class Main {

    private int[] mos;
    private List<Query> queries = new ArrayList<Query>();

    public static void main(String[] args){
        (new Main()).solve();
    }

    private void solve(){
        this.input();
        int[] ans = this.sweep();
        System.out.println(ans[0] + "\n" + ans[1] + " " + ans[2]);
    }

    private void input(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            this.inputMos(br);
        }catch(IOException IOE){}
    }

    private void inputMos(BufferedReader br) throws IOException{
        int query = Integer.parseInt(br.readLine());
        for(int i = 0; i < query; i++){
            String[] read = br.readLine().split(" ");
            this.queries.add(new Query(Integer.parseInt(read[0]), 1));
            this.queries.add(new Query(Integer.parseInt(read[1]), -1));
        }
        Collections.sort(this.queries);
    }

    private int[] sweep(){
        int[] ans = {0, 0, 0};
        int[] temp = {0, 0, 0};
        for(Query query : this.queries){
            if(query.isIncQuery()){
                temp[0]++;
                if(temp[2] == query.idx) {
                    temp[1] = temp[1];
                    temp[2] = 0;
                }
                else {
                    temp[1] = query.idx;
                    temp[2] = 0;
                }
            }
            else if(!query.isIncQuery()){
                temp[2] = query.idx;
                if(ans[0] < temp[0] || (ans[0] == temp[0] && ans[1] == temp[1] && ans[2] < temp[2])) this.copyArray(ans, temp);
                temp[0]--;
            }
        }
        return ans;
    }

    private void copyArray(int[] arr, int[] temp){
        arr[0] = temp[0];
        arr[1] = temp[1];
        arr[2] = temp[2];
    }

    private static class Query implements Comparable<Query>{

        public int idx, weight;

        public Query(int idx, int weight){
            this.idx = idx;
            this.weight = weight;
        }

        public boolean isIncQuery(){
            return weight > 0;
        }

        @Override
        public int compareTo(Query q){
            if(this.idx > q.idx) return 1;
            if(this.idx < q.idx) return -1;
            if(this.weight > q.weight) return 1;
            if(this.weight < q.weight) return -1;
            return 0;
        }

    }

}
