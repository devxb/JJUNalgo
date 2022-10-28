#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
#include <queue>
#include <utility>
#include <functional>

using namespace std;

long long N, M;
long long startIdx, endIdx;
pair<long long, long long> result = {-1, -1};
long long waters[100001];
pair<long long, long long> timeAndWater[100001];
vector<vector<pair<long long, long long> > > edges(100001);

struct compare{
    bool operator()(pair<long long, pair<long long, long long>> &first, pair<long long, pair<long long, long long>> &second){
        if(first.first < second.first) return false;
        if(first.first > second.first) return true;
        if(first.first == second.first){
            if(first.second.first < second.second.first) return true;
            return false;
        }
    }
};

void init(){
    for(int i = 0; i <= 100000; i++) timeAndWater[i] = {9223372036854775807, 0};
}

void dijkstra(){
    init();
    priority_queue<pair<long long, pair<long long, long long>>, vector<pair<long long, pair<long long, long long>>>, compare> pq;
    pq.push({0, {waters[startIdx], startIdx}});
    while(!pq.empty()){
        pair<long long, pair<long long, long long>> nowIdx = pq.top();
        pq.pop();
        if(timeAndWater[nowIdx.second.second].first < nowIdx.first
        ||
        timeAndWater[nowIdx.second.second].first == nowIdx.first && timeAndWater[nowIdx.second.second].second >= nowIdx.second.first) continue;
        timeAndWater[nowIdx.second.second] = {nowIdx.first, nowIdx.second.first};
        if(nowIdx.second.second == endIdx){
            result = {nowIdx.first, nowIdx.second.first};
            continue;
        }
        for(int i = 0; i < edges[nowIdx.second.second].size(); i++){
            long long nextIdx = edges[nowIdx.second.second][i].first;
            long long nextWeight = nowIdx.first + edges[nowIdx.second.second][i].second;
            long long nextWater = nowIdx.second.first + waters[nextIdx];
            pq.push({nextWeight, {nextWater, nextIdx}});
        }
    }
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    for(int i = 1; i <= N; i++) cin >> waters[i];
    cin >> M;
    for(int i = 1; i <= M; i++) {
        long long from, to, weight;
        cin >> from >> to >> weight;
        edges[from].push_back({to, weight});
        edges[to].push_back({from, weight});
    }
    cin >> startIdx >> endIdx;
    dijkstra();
    if(result.first == -1) cout << -1 << "\n";
    else cout << result.first << " " << result.second << "\n";
}
