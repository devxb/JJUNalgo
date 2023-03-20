import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main{

    private int num;
    private List<Integer> primeList;

    public static void main(String[] args){
        (new Main()).solve();
    }

    private void solve(){
        input();
        if(num < 8){
            System.out.println(-1);
            return;
        }
        makePrimeList();
        getPrimeComb(num,4);
    }

    private void input(){
        primeList = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            num = Integer.parseInt(br.readLine());
        }catch(IOException ignored){}
    }

    private void makePrimeList(){
        int maxNum = 1000005;
        boolean[] visited = new boolean[maxNum];
        for(int i = 2; i < maxNum; i++){
            if(!visited[i]){
                primeList.add(i);
                for(int j = i; j < maxNum; j += i){
                    visited[j] = true;
                }
            }
        }
    }

    private boolean getPrimeComb(int remain, int cnt){
        if(cnt == 0 && remain != 0){
            return false;
        }
        if(remain == 0 && cnt == 0){
            return true;
        }
        boolean isEnd = false;
        int i = 0;
        for(; primeList.get(i) <= remain; i++);
        for(int j = i; j >= 0; j--){
            isEnd = getPrimeComb(remain - primeList.get(j), cnt-1);
            if(isEnd){
                System.out.print(primeList.get(j) + " ");
                break;
            }
        }
        return isEnd;
    }

}
