//
//  main.cpp
//  1162 도로포장
//
//  Created by 이준영 on 2020/12/29.
//

#include <iostream>
#include <vector>
#include <utility>
#include <algorithm>
#include <queue>

using namespace std;

vector<vector<pair<long long,long long> > > vec;
priority_queue<pair<long long,pair<long long,long long> >, vector<pair<long long,pair<long long,long long> > >, greater<pair<long long,pair<long long,long long> > > > pq;
long long N,M,K, check[100005][25], num = 9223372036854775800;

void BFS(){
    check[1][0] = 0;
    while(!pq.empty()){
        long long nowIdx = pq.top().second.first;
        long long nowTime = pq.top().first;
        long long nowP = pq.top().second.second;
        pq.pop();
        if(nowIdx == N){
            num = min(num, nowTime);
        }
        if(check[nowIdx][nowP] < nowTime){
            continue;
        }
        check[nowIdx][nowP] = nowTime;
        for(int i = 0; i < vec[nowIdx].size(); i++){
            long long nextIdx = vec[nowIdx][i].first;
            long long nextTime = nowTime + vec[nowIdx][i].second;
            if(check[nextIdx][nowP] > nextTime){
                check[nextIdx][nowP] = nextTime;
                pq.push({nextTime,{nextIdx,nowP}});
            }
            if(nowP < K and check[nextIdx][nowP+1] > nowTime){
                check[nextIdx][nowP+1] = nowTime;
                pq.push({nowTime,{nextIdx,nowP+1}});
            }
        }
    }
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> M >> K;
    for(int i = 0; i <= N; i++){
        for(int j = 0; j <= K; j++){
            check[i][j] = num;
        }
    }
    vec.resize(N+5);
    for(int i = 1; i <= M; i++){
        long long from, to, t;
        cin >> from >> to >> t;
        vec[from].push_back({to,t});
        vec[to].push_back({from,t});
    }
    pq.push({0,{1,0}});
    BFS();
    cout << num << "\n";
    return 0;
}
