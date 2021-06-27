import java.util.*;
import java.io.*;

public class Main{
    
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private String read;
    
    public void run() throws IOException{
        while((read = br.readLine()) != null && !read.equals("")) System.out.println(read);
    }
    
}
