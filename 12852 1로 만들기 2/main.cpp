//
//  main.cpp
//  12852 1로 만들기 2
//
//  Created by 이준영 on 2021/01/03.
//

#include <iostream>
#include <queue>
#include <utility>
#include <algorithm>
#include <vector>

using namespace std;

int N, d[] = {2,3};
priority_queue<pair<int,int>, vector<pair<int,int> >, greater<pair<int,int> > > pq;
pair<int,int> path[1000005];

void findPath(int idx){
    if(path[idx].second == -1){
        cout << idx << " ";
        return;
    }
    findPath(path[idx].second);
    cout << idx << " ";
}

void BFS(){
    while(!pq.empty()){
        int nowCnt = pq.top().first;
        int nowIdx = pq.top().second;
        pq.pop();
        if(path[nowIdx].first < nowCnt){
            continue;
        }
        for(int i = 0; i < 3; i++){
            int nextIdx = -1, nextCnt;
            if(i == 2){
                nextIdx = nowIdx - 1;
            }
            else if(nowIdx % d[i] == 0){
                nextIdx = nowIdx / d[i];
            }
            nextCnt = nowCnt + 1;
            if(nextIdx == -1 or path[nextIdx].first <= nextCnt){
                continue;
            }
            path[nextIdx] = {nextCnt,nowIdx};
            pq.push({nextCnt,nextIdx});
        }
    }
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    for(int i = 0; i <= N; i++){
        path[i] = {1000000000,-1};
    }
    pq.push({0,N});
    path[N] = {0,-1};
    BFS();
    cout << path[1].first << "\n";
    findPath(1);
    return 0;
}
