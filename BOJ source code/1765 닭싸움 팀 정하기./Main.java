// 1765
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main{
    
    public static void main(String[] args){
        Solve solve = new Solve();
        solve.run();
    }
    
}

class Solve{
    
    private static class Node{
        
        private int idx;
        private ArrayList<Integer> friend = new ArrayList<Integer>();
        private ArrayList<Integer> enemy = new ArrayList<Integer>();
        
        public Node(int idx){
            this.idx = idx;
        }
        
        public void addFriend(int idx){
            friend.add(idx);
        }
        
        public void addEnemy(int idx){
            enemy.add(idx);
        }
        
    }
    
    private int n, m;
    private Node[] arr;
    private boolean[] check;
    
    public void run(){
        input();
        System.out.println(getTeam());
    }
    
    private void input(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            n = Integer.parseInt(br.readLine());
            m = Integer.parseInt(br.readLine());
            arr = new Node[n+1];
            check = new boolean[n+1];
            for(int i = 0; i <= n; i++) arr[i] = new Node(i);
            String[] read = null;
            for(int i = 0; i < m; i++){
                read = br.readLine().split(" ");
                int a = Integer.parseInt(read[1]);
                int b = Integer.parseInt(read[2]);
                if(read[0].equals("E")){
                    arr[a].addEnemy(b);
                    arr[b].addEnemy(a);
                }
                else{
                    arr[a].addFriend(b);
                    arr[b].addFriend(a);
                }
            }
        }catch(IOException IOE){}
    }
    
    private int getTeam(){
        int ans = 0;
        for(int i = 1; i <= n; i++){
            if(check[i]) continue;
            findFriends(i, true, new boolean[n+5]);
            ans++;
        }
        return ans;
    }
    
    private void findFriends(int idx, boolean isFriend, boolean[] dupCheck){
        if(dupCheck[idx]) return;
        dupCheck[idx] = true;
        if(isFriend) check[idx] = true;
        if(isFriend) for(int i = 0; i < arr[idx].friend.size(); i++){
            int nNode = arr[idx].friend.get(i);
            if(dupCheck[nNode]) continue;
            findFriends(nNode, isFriend, dupCheck);
        }
        
        for(int i = 0; i < arr[idx].enemy.size(); i++){
            int nNode = arr[idx].enemy.get(i);
            if(dupCheck[nNode]) continue;
            findFriends(nNode, (isFriend == true) ? false : true, dupCheck);
        }
    }
    
}
