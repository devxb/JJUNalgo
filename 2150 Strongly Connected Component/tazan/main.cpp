#include <iostream>
#include <algorithm>
#include <string>
#include <utility>
#include <vector>
#include <deque>

using namespace std;

int V, E, order = 1; 
int orders[100001];
bool visited[100001];
vector<vector<int> > edge(100001);
vector<vector<int> > sccs(100001); 
deque<int> groups;

int scc(int idx){
    if(orders[idx] != 0) return orders[idx];
    groups.push_back(idx);
    int group = order;
    orders[idx] = order++;
    for(int i = 0; i < edge[idx].size(); i++){
        int nextIdx = edge[idx][i];
        if(orders[nextIdx] == 0) group = min(group, scc(nextIdx));
        if(!visited[nextIdx]) group = min(group, orders[nextIdx]); 
    }
    if(group == orders[idx]){
        int groupInnerIdx = -1;
        while(groupInnerIdx != idx && !groups.empty()){
            groupInnerIdx = groups.back();
            visited[groupInnerIdx] = true;
            sccs[group].push_back(groupInnerIdx);
            groups.pop_back();
        }
    }
    return group;
}

void input(){
    cin >> V >> E;
    for(int i = 0; i < E; i++){
        int from, to;
        cin >> from >> to;
        edge[from].push_back(to);
    }
}

int main() {
    ios::sync_with_stdio(false);
	cin.tie(NULL);
    cout.tie(NULL);
    input();
    for(int i = 1; i <= V; i++) {
        if(orders[i] > 0) continue;
        scc(i);
    }
    vector<pair<int, int> > printOrder;
    for(int i = 1; i <= V; i++){
        if(sccs[i].empty()) continue;
        sort(sccs[i].begin(), sccs[i].end());
        printOrder.push_back({sccs[i][0], i});
    }
    sort(printOrder.begin(), printOrder.end());
    cout << printOrder.size() << "\n";
    for(int i = 0; i < printOrder.size(); i++){
        for(int j = 0; j < sccs[printOrder[i].second].size(); j++) cout << sccs[printOrder[i].second][j] << " ";
        cout << "-1\n";
    }
    return 0;
}
