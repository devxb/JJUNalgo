//
//  main.cpp
//  19581 두 번째 트리의 지름
//
//  Created by 이준영 on 2020/11/25.
//

#include <iostream>
#include <vector>
#include <algorithm>
#include <utility>

using namespace std;

int N;
int dist[100005][5];

vector<vector<pair<int,int> > > vec;
pair<int,int> leaf[5];
pair<int,int> can[5];

void dis(int idx, int w){
    dist[idx][0] = w;
    if(leaf[1].first <= w){
        leaf[2] = {leaf[1].first, leaf[1].second};
        leaf[1] = {w,idx};
    }
    else if(leaf[2].first <= w){
        leaf[2] = {w,idx};
    }
    for(int i = 0; i < vec[idx].size(); i++){
        if(dist[vec[idx][i].first][0] == -1){
            dis(vec[idx][i].first, w + vec[idx][i].second);
        }
    }
    return;
}

void find(int idx, int w, int target){
    dist[idx][target] = w;
    if(can[target].first <= w){
        can[target].second = can[target].first;
        can[target].first = w;
    }
    else if(can[target].second <= w){
        can[target].second = w;
    }
    for(int i = 0; i < vec[idx].size(); i++){
        if(dist[vec[idx][i].first][target] == -1){
            find(vec[idx][i].first, w + vec[idx][i].second, target);
        }
    }
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    vec.resize(N+5);
    for(int i = 1; i < N; i++){
        dist[i][0] = -1;
        dist[i][1] = -1;
        dist[i][2] = -1;
        int from, to, w;
        cin >> from >> to >> w;
        vec[from].push_back({to,w});
        vec[to].push_back({from,w});
    }
    dist[N][0] = -1;
    dist[N][1] = -1;
    dist[N][2] = -1;
    dis(1, 0);
    find(leaf[1].second, 0, 1);
    find(leaf[2].second, 0, 2);
    if(can[2].first == can[1].first){
        cout << max(can[1].second, can[2].second) << "\n";
    }
    else{
        cout << max(can[1].second, can[2].first) << "\n";
    }
    return 0;
}
