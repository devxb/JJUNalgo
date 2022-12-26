import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    private int distance, island, delete;
    private List<Integer> islands;
    {
        islands = new ArrayList<>();
    }

    public static void main(String[] args){
        (new Main()).solve();
    }

    private void solve(){
        input();
        System.out.println(parametricSearch());
    }

    private void input(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            String[] line = br.readLine().split(" ");
            distance = Integer.parseInt(line[0]);
            island = Integer.parseInt(line[1]);
            delete = Integer.parseInt(line[2]);
            islands.add(0);
            for(int i = 0; i < island; i++) islands.add(Integer.parseInt(br.readLine()));
            islands.add(distance);
            Collections.sort(islands);
        }catch(IOException ignored){}
    }

    private int parametricSearch(){
        int left = 0, right = distance;
        int ans = getMinJumpDistance();
        while(left <= right){
            int mid = (left + right) / 2;
            if(isFarable(mid)){
                left = mid + 1;
                ans = mid;
                continue;
            }
            right = mid - 1;
        }
        return ans;
    }

    private int getMinJumpDistance(){
        int ans = distance;
        for(int i = 1; i < islands.size(); i++) ans = Math.min(ans, islands.get(i) - islands.get(i-1));
        return ans;
    }

    private boolean isFarable(int distance){
        int currentDeleted = delete;
        int beforeIdx = 0;
        for(int i = 1; i < islands.size(); i++){
            if(islands.get(i) - beforeIdx < distance) {
                if(currentDeleted == 0) return false;
                currentDeleted--;
                continue;
            }
            beforeIdx = islands.get(i);
        }
        return true;
    }

}
