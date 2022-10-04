import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Math.abs;

public class Main{

    private int number;
    private List<Integer> numbers;
    private Deque<Integer> increaseNumbers;

    public static void main(String[] args){
        (new Main()).solve();
    }

    private void solve(){
        input();
        setIncreaseNumbers();
        StringBuilder resultPrinter = new StringBuilder();
        while(!increaseNumbers.isEmpty()){
            resultPrinter.append(increaseNumbers.pollFirst()).append(" ");
        }
        System.out.println(resultPrinter.toString());
    }

    private void input(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            input(br);
        }catch(IOException IOE){}
    }

    private void input(BufferedReader br) throws IOException{
        number = Integer.parseInt(br.readLine());
        numbers = new ArrayList<>();
        String[] read = br.readLine().split(" ");
        for(String element : read)
            numbers.add(Integer.parseInt(element));
        Collections.sort(numbers);
    }

    private void setIncreaseNumbers(){
        increaseNumbers = new LinkedList<>();
        int left = 1, right = number-1;
        increaseNumbers.add(numbers.get(0));
        while(left <= right){
            int leftNum = numbers.get(left);
            int rightNum = numbers.get(right);
            if(abs(leftNum - increaseNumbers.getFirst()) < abs(rightNum - increaseNumbers.getFirst())) {
                increaseNumbers.addFirst(rightNum);
                right--;
                continue;
            }
            increaseNumbers.addFirst(leftNum);
            left++;
        }
    }

}
