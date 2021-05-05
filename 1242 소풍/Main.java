import java.io.*;

public class Main {
    
    public static void main(String[] args){
        Solve solve = new Solve();
        System.out.println(solve.run());
    }
    
}

class Solve{
    
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private int input[] = new int[5];
    
    public Solve(){
        input();
    }
    
    public int run(){
        return getExit(input[0], input[2]);
    }
    
    private void input(){
        try{
            String str[] = br.readLine().split(" ");
            for(int i = 0; i < 3; i++) input[i] = Integer.parseInt(str[i]);
        } catch (IOException ioe){}
    }
    
    private int getExit(int numberOfPeople, int target){
        int exitNumber = input[1] % numberOfPeople;
        if(exitNumber == 0) exitNumber = numberOfPeople;
        if(exitNumber == target || numberOfPeople == 1) return 1;
        if(target < exitNumber) {
            target = (numberOfPeople-exitNumber) + target;
        }
        else target = target - exitNumber;
        return 1 + getExit(numberOfPeople-1,target);
    }
}
