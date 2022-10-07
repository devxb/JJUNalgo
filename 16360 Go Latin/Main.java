import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main{

    Map<String, String> table = new HashMap<>();
    StringBuilder result = new StringBuilder();

    public static void main(String[] args){
        (new Main()).solve();
    }

    private void solve(){
        initTables();
        input();
        System.out.print(result);
    }

    private void initTables(){
        table.put("a", "as");
        table.put("i", "ios");
        table.put("y", "ios");
        table.put("l", "les");
        table.put("n", "anes");
        table.put("ne", "anes");
        table.put("o", "os");
        table.put("r", "res");
        table.put("t", "tas");
        table.put("u", "us");
        table.put("v", "ves");
        table.put("w", "was");
    }

    private void input(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            int words = Integer.parseInt(br.readLine());
            for(int i = 0; i < words; i++){
                String word = br.readLine();
                String lastString = word.substring(word.length()-1);
                String lastTwoString = word.substring(word.length()-2);
                if(table.containsKey(lastString))
                    result.append(word, 0, word.length()-1).append(table.get(lastString)).append("\n");
                else if(table.containsKey(lastTwoString)){
                    result.append(word, 0, word.length()-2).append(table.get(lastTwoString)).append("\n");
                }
                else result.append(word).append("us").append("\n");
            }
        }catch(IOException IOE){}
    }

}
