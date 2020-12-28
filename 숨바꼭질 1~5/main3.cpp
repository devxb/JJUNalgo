//
//  main.cpp
//  13549 숨바꼭질 3
//
//  Created by 이준영 on 2020/12/27.
//

#include <iostream>
#include <queue>
#include <utility>
#include <vector>
using namespace std;
int N, K, check[200005], dx[] = {1,-1};
priority_queue<pair<int,int>, vector<pair<int,int> >, greater<pair<int,int> > > pq;

void BFS(){
    while(!pq.empty()){
        int nowNum = pq.top().first;
        int nowIdx = pq.top().second;
        pq.pop();
        if(check[nowIdx] <= nowNum){
            continue;
        }
        if(nowIdx == K){
            cout << nowNum << "\n";
            return;
        }
        check[nowIdx] = nowNum;
        for(int i = 0; i <= 2; i++){
            int nextIdx = nowIdx;
            int nextNum = nowNum;
            if(i == 2){
                nextIdx *= 2;
            }
            else{
                nextIdx += dx[i];
                nextNum++;
            }
            if(nextIdx >= 0 and nextIdx <= 200000 and check[nextIdx] > nextNum){
                pq.push({nextNum,nextIdx});
            }
        }
    }
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> K;
    for(int i = 0; i <= 200000; i++){
        check[i] = 1000000000;
    }
    pq.push({0,N});
    BFS();
    return 0;
}
