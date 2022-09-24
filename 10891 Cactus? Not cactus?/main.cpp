#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>

using namespace std;

int N, M, order = 1;
int orders[100001];
int inDegree[100001];
int outDegree[100001];
vector<vector<int>> edges;

int dfs(int befIdx, int idx){
    if(orders[idx] > 0){
        outDegree[idx]++;
        return 1;
    }
    orders[idx] = order++;
    int cycle = 0;
    for(int i = 0; i < edges[idx].size(); i++){
        int nextIdx = edges[idx][i];
        if(befIdx == nextIdx || orders[nextIdx] > orders[idx]) continue;
        cycle += dfs(idx, nextIdx);
    }
    inDegree[idx] = cycle;
    return cycle - outDegree[idx];
}

int main() {
    ios::sync_with_stdio(false);
	  cin.tie(NULL);
    cout.tie(NULL);
    cin >> N >> M;
    edges.resize(N+1);
    for(int i = 0; i < M; i++){
        int from, to;
        cin >> from >> to;
        edges[from].push_back(to);
        edges[to].push_back(from);
    }
    for(int i = 1; i <= N; i++)
        if(orders[i] == 0) dfs(i, i);
    for(int i = 1; i <= N; i++){
        if(inDegree[i] >= 2){
            cout << "Not cactus\n";
            return 0;
        }
    }
    cout << "Cactus\n";
    return 0;
}
