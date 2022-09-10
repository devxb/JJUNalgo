import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class Main{

    private int N, M;
    private int[][] land;
    private int[][] landCum;

    public static void main(String[] args){
        (new Main()).solve();
    }

    private void solve(){
        input();
        initCum();
        System.out.println(findSubsections());
    }

    private void input(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            input(br);
        }catch(IOException IOE){}
    }

    private void input(BufferedReader br) throws IOException{
        String[] read = br.readLine().split(" ");
        N = Integer.parseInt(read[0]);
        M = Integer.parseInt(read[1]);
        land = new int[N][M];
        landCum = new int[N][M];
        for(int i = 0; i < N; i++){
            read = br.readLine().split(" ");
            for(int j = 0; j < M; j++) land[i][j] = Integer.parseInt(read[j]);
        }
    }

    private void initCum(){
        landCum[0][0] = land[0][0];
        for(int i = 1; i < N; i++) landCum[i][0] = landCum[i-1][0] + land[i][0];
        for(int j = 1; j < M; j++) landCum[0][j] = landCum[0][j-1] + land[0][j];
        for(int i = 1; i < N; i++)
            for(int j = 1; j < M; j++)
                landCum[i][j] = land[i][j] + landCum[i][j-1] + landCum[i-1][j] - landCum[i-1][j-1];
    }

    private int findSubsections(){
        int subsections = 0;
        for(int height = 0; height <= 10; height++)
            for(int width = 0; width <= 10 && width*height <= 10; width++)
                subsections += findSubsections(0,0, height, width);
        return subsections;
    }

    private int findSubsections(int y, int x, int height, int width){
        if(y + height >= N) return 0;
        if(x + width >= M) return findSubsections(y+1, 0, height, width);
        int sectionWeight = getSectionWeight(y, x, height, width);
        int ans = 0;
        if(sectionWeight == 10) ans++;
        return ans + findSubsections(y, x+1, height, width);
    }

    private int getSectionWeight(int y, int x, int height, int width){
        int sectionWeight = landCum[y+height][x+width];
        if(y-1 >= 0) sectionWeight -= landCum[y-1][x+width];
        if(x-1 >= 0) sectionWeight -= landCum[y+height][x-1];
        if(y-1 >= 0 && x-1 >= 0) sectionWeight += landCum[y-1][x-1];
        return sectionWeight;
    }

}
