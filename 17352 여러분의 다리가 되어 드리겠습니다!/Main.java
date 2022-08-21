import java.io.*;

import static java.lang.Math.min;
import static java.lang.Math.max;

public class Main{

    private int[] bridges;

    public static void main(String[] args){
        (new Main()).solve();
    }

    private void solve(){
        this.input();
        System.out.println(this.connectBridge());
    }

    private void input(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            this.inputBridges(br);
        }catch(IOException IOE){}
    }

    private void inputBridges(BufferedReader br) throws IOException{
        int bridgeCount = Integer.parseInt(br.readLine());
        this.bridges = new int[bridgeCount+1];
        for(int i = 0; i < bridgeCount-2; i++){
            String[] read = br.readLine().split(" ");
            int from = this.findRootBridge(max(Integer.parseInt(read[0]), Integer.parseInt(read[1])));
            int to = this.findRootBridge(min(Integer.parseInt(read[0]), Integer.parseInt(read[1])));
            this.bridges[from] = to;
        }
    }

    private String connectBridge(){
        int[] disConnectedBridgeSets = new int[2];
        for(int i = 2; i <= this.bridges.length-1; i++)
            if(this.findRootBridge(i) != this.findRootBridge(i-1)) return i + " " + (i-1);
        return "err";
    }

    private int findRootBridge(int bridge){
        if(bridges[bridge] == 0) return bridge;
        return bridges[bridge] = this.findRootBridge(bridges[bridge]);
    }

}
