import java.io.*;

public class Main {

    private int query;
    private int[] grounds;
    private int[] queries;

    public static void main(String[] args){
        (new Main()).solve();
    }

    private void solve(){
        this.input();
        this.sweep();
        StringBuilder result = new StringBuilder();
        this.initResultPrinter(result);
        System.out.println(result.toString());
    }

    private void input(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            this.inputGround(br);
            this.inputQuery(br);
        }catch(IOException IOE){}
    }

    private void inputGround(BufferedReader br) throws IOException{
        String[] read = br.readLine().split(" ");
        int groundSize = Integer.parseInt(read[0]);
        this.grounds = new int[groundSize+2];
        this.query = Integer.parseInt(read[1]);
        read = br.readLine().split(" ");
        for(int i = 1; i <= groundSize; i++) grounds[i] = Integer.parseInt(read[i-1]);
    }

    private void inputQuery(BufferedReader br) throws IOException{
        this.queries = new int[this.grounds.length];
        for(int i = 0; i < this.query; i++) {
            String[] read = br.readLine().split(" ");
            int from = Integer.parseInt(read[0]);
            int to = Integer.parseInt(read[1]);
            int weight = Integer.parseInt(read[2]);
            this.queries[from] += weight;
            this.queries[to+1] += (-1*weight);
        }
    }

    private void sweep(){
        for(int i = 1; i <= this.grounds.length-1; i++) {
            queries[i] += queries[i-1];
            this.grounds[i] += this.queries[i];
        }
    }

    private void initResultPrinter(StringBuilder printer){
        for(int i = 1; i <= this.grounds.length-2; i++) printer.append(this.grounds[i]).append(" ");
    }

}
