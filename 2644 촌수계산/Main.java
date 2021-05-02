import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args){
        System.out.println(new solve().get());
    }
}

class solve{
    
    private int n, person1, person2, m;
    ArrayList<ArrayList<Integer> > familyTree;
    boolean[] check;
    
    public int get(){
        input();
        check = new boolean[n+5];
        return calcFamilyTree(person1, 0);
    }
    
    private int calcFamilyTree(int idx, int family){
        int ret = -1;
        if(idx == person2) return family;
        check[idx] = true;
        for(int i = 0; i < familyTree.get(idx).size(); i++){
            int _idx = familyTree.get(idx).get(i);
            if(check[_idx]) continue;
            ret = Math.max(calcFamilyTree(_idx, family+1),ret);
        }
        return ret;
    }
    
    private void input(){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        person1 = sc.nextInt();
        person2 = sc.nextInt();
        m = sc.nextInt();
        familyTree = new ArrayList<ArrayList<Integer> >(n+5); // 용량 재할당 방지 사이즈는 따로 지정해줘야함
        for(int i = 0; i <= n; i++) familyTree.add(new ArrayList<Integer>());
        for(int i = 0; i < m; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            familyTree.get(a).add(b);
            familyTree.get(b).add(a);
        }
    }
    
}
