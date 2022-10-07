import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main{

    private static List<Integer> tries = new ArrayList<>();

    public static void main(String[] args){
        (new Main()).solve();
    }

    private void solve(){
        initTri();
        StringBuilder result = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            int testcases = Integer.parseInt(br.readLine());
            for(int i = 0; i < testcases; i++){
                int num = Integer.parseInt(br.readLine());
                result.append(isMakeable(num) ? 1 : 0).append("\n");
            }
        }catch(IOException IOE){}
        System.out.print(result);
    }

    private void initTri(){
        int triCount = 1;
        int increase = 2;
        while(triCount <= 1000){
            tries.add(triCount);
            triCount += increase;
            increase++;
        }
    }

    private boolean isMakeable(int num){
        for(int i = 0; i < tries.size(); i++){
            int sumI = tries.get(i);
            if(sumI >= num) break;
            for(int j = 0; j < tries.size(); j++){
                int sumJ = sumI + tries.get(j);
                if(sumJ >= num) break;
                for(int l = 0; l < tries.size(); l++){
                    int sumL = sumJ + tries.get(l);
                    if(sumL > num) break;
                    if(sumL == num) return true;
                }
            }
        }
        return false;
    }

}
