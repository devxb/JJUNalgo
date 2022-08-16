import java.util.*;
import java.io.*;

import static java.lang.Math.max;

public class Main{

    private int N, K;
    private int[] num;

    private NumIdx[] segTree;

    private StringBuilder resultPrinter = new StringBuilder();

    private NumIdx faultNumIdx = new NumIdx(0, 987654321);

    public static void main(String[] args){
        (new Main()).solve();
    }

    private void solve(){
        this.input();
        this.updateSeg();
        this.selectNum(0, N-(N-K), N-K);
        System.out.println(resultPrinter.toString());
    }

    private void input(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            this.inputNK(br);
            this.inputNum(br);
        }catch(IOException IOE){}
    }

    private void inputNK(BufferedReader br) throws IOException{
        String[] read = br.readLine().split(" ");
        this.N = Integer.parseInt(read[0]);
        this.segTree = new NumIdx[N*4+5];
        this.K = Integer.parseInt(read[1]);
    }

    private void inputNum(BufferedReader br) throws IOException{
        String read = br.readLine();
        this.num = new int[N];
        this.parseReadToNum(read);
    }

    private void parseReadToNum(String read){
        for(int i = 0; i < N; i++) num[i] = (int)read.charAt(i) - (int)'0';
    }

    private void updateSeg(){
        for(int i = 0; i < N; i++) updateSeg(0, N-1, 1, new NumIdx(num[i], i));
    }

    private void updateSeg(int left, int right, int idx, NumIdx numIdx){
        if(left > right || numIdx.idx > right || numIdx.idx < left) return;
        if(this.segTree[idx] == null) this.segTree[idx] = numIdx;
        if(this.segTree[idx].num < numIdx.num) this.segTree[idx] = numIdx;
        if(left == right) return;
        int mid = (left + right) / 2;
        if(left <= numIdx.idx && numIdx.idx <= mid)  this.updateSeg(left, mid, idx*2, numIdx);
        if(mid+1 <= numIdx.idx && numIdx.idx <= right)this.updateSeg(mid+1, right, idx*2+1, numIdx);
    }

    private void selectNum(int left, int right, int select){
        NumIdx numIdx = querySeg(0, N-1, 1, left, right);
        this.resultPrinter.append(numIdx.num);
        int remainNums = N - (numIdx.idx+1);
        int nextLeft = numIdx.idx + 1;
        int nextSelected = select - 1;
        int nextRight = nextLeft + (remainNums-nextSelected);
        if(nextSelected == 0) return;
        this.selectNum(nextLeft, nextRight, nextSelected);
    }

    private NumIdx querySeg(int left, int right, int idx, int queryLeft, int queryRight){
        NumIdx ans = this.faultNumIdx;
        if(left > right || queryLeft > right || queryRight < left) return ans;
        if(queryLeft <= left && right <= queryRight) return segTree[idx];
        int mid = (left + right) / 2;
        ans = ans.compareNumIdx(querySeg(left, mid, idx*2, queryLeft, queryRight));
        ans = ans.compareNumIdx(querySeg(mid+1, right, idx*2+1, queryLeft, queryRight));
        return ans;
    }

    private static class NumIdx{

        int num, idx;

        public NumIdx(int num, int idx){
            this.num = num;
            this.idx = idx;
        }

        public NumIdx compareNumIdx(NumIdx numIdx){
            if(this.num < numIdx.num) return numIdx;
            if(this.num == numIdx.num && this.idx > numIdx.idx) return numIdx;
            return this;
        }

    }

}
