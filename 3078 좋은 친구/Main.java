import java.io.*;
import java.sql.Array;
import java.util.*;

public class Main {

    private int friendCount, diffGrade;
    private List<ArrayList<Integer>> friends = new ArrayList<ArrayList<Integer>>();
    private List<Integer> nameLengths = new ArrayList<Integer>();

    public static void main(String[] args){
        (new Main()).solve();
    }

    private void solve(){
        this.input();
        System.out.println(countGoodFriends());
    }

    private void input(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            this.inputFriends(br);
        }catch(IOException IOE){}
    }

    private void inputFriends(BufferedReader br) throws IOException{
        initFriends();
        String[] read = br.readLine().split(" ");
        friendCount = Integer.parseInt(read[0]);
        diffGrade = Integer.parseInt(read[1]);
        boolean[] nameLengthChecked = new boolean[21];
        for(int i = 1; i <= friendCount; i++){
            String line = br.readLine();
            friends.get(line.length()).add(i);
            if(nameLengthChecked[line.length()]) continue;
            nameLengthChecked[line.length()] = true;
            nameLengths.add(line.length());
        }
    }

    private void initFriends(){
        for(int i = 0; i <= 21; i++) friends.add(new ArrayList<Integer>());
    }

    private long countGoodFriends(){
        sortFriends();
        long goodFriends = 0L;
        for(int nameLength : nameLengths){
            int left = 0, right = 0;
            int[] hitedIdx = new int[friends.get(nameLength).size()+1];
            while(left <= right && right < friends.get(nameLength).size()){
                int leftFriend = friends.get(nameLength).get(left);
                int rightFriend = friends.get(nameLength).get(right);
                if(left == right){
                    right++;
                    continue;
                }
                if(rightFriend - leftFriend <= diffGrade){
                    int befHitIdx = hitedIdx[left] == 0 ? left : hitedIdx[left];
                    goodFriends += (long)(right - befHitIdx);
                    hitedIdx[left] = right;
                }
                if(right+1 < friends.get(nameLength).size() && friends.get(nameLength).get(right+1) - leftFriend <= diffGrade) right++;
                else left++;
            }
        }
        return goodFriends;
    }

    private void sortFriends(){
        Collections.sort(nameLengths);
        for(int nameLength : nameLengths) Collections.sort(friends.get(nameLength));
    }

}
