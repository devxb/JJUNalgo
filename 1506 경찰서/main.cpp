#include <iostream>
#include <algorithm>
#include <string>
#include <utility>
#include <vector>
#include <deque>

using namespace std;

int n, order = 1;
int cost[101], orders[101];
int ans;
bool visited[101];
vector<vector<int> > edge(101);
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
        int builtedCost = 987654321;
        int groupInnerIdx = -1;
        while(groupInnerIdx != idx && !groups.empty()){
            groupInnerIdx = groups.back();
            visited[groupInnerIdx] = true;
            builtedCost = min(builtedCost, cost[groupInnerIdx]);
            groups.pop_back();
        }
        ans += builtedCost;
    }
    return group;
}

void input(){
    cin >> n;
    for(int i = 1; i <= n; i++) cin >> cost[i]; 
    for(int i = 1; i <= n; i++){
        string line;
        cin >> line;
        for(int j = 1; j <= n; j++){
            if(line[j-1] == '0') continue;
            edge[i].push_back(j);
        }
    }
}

int main() {
    ios::sync_with_stdio(false);
	cin.tie(NULL);
    cout.tie(NULL);
    input();
    for(int i = 1; i <= n; i++) {
        if(orders[i] > 0) continue;
        scc(i);
    }
    cout << ans << "\n";
    return 0;
}
