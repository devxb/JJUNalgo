import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main{

    private int N;
    private Set<String> group = new HashSet<>();

    public static void main(String[] args){
        (new Main()).solve();
    }

    private void solve(){
        input();
        System.out.println(group.size());
    }

    private void input(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            N = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < N; i++){
                sb.setLength(0);
                String line = br.readLine();
                char[] splited = new char[line.length()];
                for(int s = 0; s < splited.length; s++) splited[s] = line.charAt(s);
                Arrays.sort(splited);
                for(int s = 0; s < splited.length; s++) sb.append(splited[s]);
                group.add(sb.toString());
            }
        }catch(IOException IOE){}
    }

}
