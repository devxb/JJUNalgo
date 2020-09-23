//
//  main.cpp
//  10423 전기가 부족해
//
//  Created by 이준영 on 13/07/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <algorithm>
#include <deque>
#include <vector>
#include <utility>
#include <queue>

using namespace std;

int N, M, K;
vector<int> power;
priority_queue<pair<int,int>, vector<pair<int,int> >, greater<pair<int,int> > > pq;
vector<vector<pair<int,int> > > vec;
int check[1005];

void find(){
    while(!pq.empty()){
        int now_idx = pq.top().second;
        int now_num = pq.top().first;
        pq.pop();
        if(check[now_idx] != 0){
            continue;
        }
        check[now_idx] = now_num;
        for(int i = 0; i < vec[now_idx].size(); i++){
            int next_idx = vec[now_idx][i].first;
            int next_num = vec[now_idx][i].second;
            if(check[next_idx] == 0){
                pq.push({next_num, next_idx});
            }
        }
    }
}

int main(int argc, const char * argv[]) {
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N >> M >> K;
    vec.resize(N+5);
    for(int i = 1; i <= K; i++){
        int p;
        cin >> p;
        power.push_back(p);
        check[p] = -1;
    }
    for(int i = 1; i <= M; i++){
        int from, to, num;
        cin >> from >> to >> num;
        vec[from].push_back({to,num});
        vec[to].push_back({from,num});
    }
    for(int i = 0; i < power.size(); i++){
        for(int j = 0; j < vec[power[i]].size(); j++){
            pq.push({vec[power[i]][j].second, vec[power[i]][j].first});
        }
    }
    find();
    int cnt = 0;
    for(int i = 1; i <= N; i++){
        if(check[i] != -1){
            cnt = cnt + check[i];
        }
    }
    cout << cnt << "\n";
    return 0;
}
