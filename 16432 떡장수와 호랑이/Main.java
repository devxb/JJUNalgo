import java.io.*;
import java.util.*;

public class Main {

    private int days;
    private List<ArrayList<Integer>> cakes;
    private int[] pickedCakes;
    private int[][] check;

    public static void main(String[] args) {
        (new Main()).solve();
    }
    private void solve(){
        this.input();
        for(int i = 0; i < cakes.get(0).size(); i++) {
            boolean result = this.giveCakes(1, cakes.get(0).get(i));
            if(result) {
                this.pickedCakes[0] = cakes.get(0).get(i);
                break;
            }
        }
        String result = this.initResultPrinter(new StringBuilder());
        System.out.print(result);
    }

    private void input(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            this.input(br);
        }catch(IOException IOE){}
    }

    private void input(BufferedReader br) throws IOException{
        this.days = Integer.parseInt(br.readLine());
        this.pickedCakes = new int[days];
        this.check = new int[days][10];
        this.cakes = this.initCakes(new ArrayList<ArrayList<Integer>>());
        for(int i = 0; i < this.days; i++){
            String[] read = br.readLine().split(" ");
            for(int r = 1; r < read.length; r++) cakes.get(i).add(Integer.valueOf(read[r]));
        }
    }

    private List<ArrayList<Integer>> initCakes(List<ArrayList<Integer>> cakes){
        for(int i = 0; i < this.days; i++) cakes.add(new ArrayList<Integer>());
        return cakes;
    }

    private boolean giveCakes(int day, int befCake){
        if(day >= this.days) return true;
        if(this.check[day][befCake] > 0) return this.convertIntToBoolean(this.check[day][befCake]);
        boolean give = false;
        for(int i = 0; i < this.cakes.get(day).size(); i++){
            int nextCake = this.cakes.get(day).get(i);
            if(befCake == nextCake) continue;
            give = this.giveCakes(day+1, nextCake);
            if(give){
                this.pickedCakes[day] = nextCake;
                break;
            }
        }
        this.check[day][befCake] = this.convertBooleanToInt(give);
        return give;
    }

    private boolean convertIntToBoolean(int intResult){
        if(intResult == 1) return true;
        return false;
    }

    private int convertBooleanToInt(boolean booleanResult){
        if(booleanResult) return 1;
        return 2;
    }

    private String initResultPrinter(StringBuilder resultPrinter) {
        for (int i = 0; i < this.days; i++) {
            if (pickedCakes[i] == 0) return "-1\n";
            resultPrinter.append(this.pickedCakes[i]).append("\n");
        }
        return resultPrinter.toString();
    }

}
