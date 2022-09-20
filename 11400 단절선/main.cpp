#include <iostream>
#include <algorithm>
#include <cmath>
#include <vector>
#include <string>
#include <utility>
#include <set>

using namespace std;

vector<vector<int> > edges(100001);
set<pair<int, int> > breakEdges; 
int nodeCount, edgeCount, order;
int visitedOrders[100001];

int findBreakPoints(int befIdx, int idx){
    if(visitedOrders[idx] > 0) return visitedOrders[idx];
    visitedOrders[idx] = order++;
    int ans = visitedOrders[idx];
    int diffTree = 0;
    for(int i = 0; i < edges[idx].size(); i++){
        int nextIdx = edges[idx][i];
        if(befIdx == nextIdx || visitedOrders[nextIdx] >= visitedOrders[idx]) continue;
        if(visitedOrders[nextIdx] == 0) diffTree++;
        int temp = findBreakPoints(idx, nextIdx);
        ans = min(ans, temp);
    }
    if(visitedOrders[befIdx] < ans) breakEdges.insert({min(idx, befIdx), max(idx, befIdx)});
    return visitedOrders[idx] = ans;
}

void input(){
    cin >> nodeCount >> edgeCount;
    for(int i = 0; i < edgeCount; i++){
        int from, to;
        cin >> from >> to;
        edges[from].push_back(to);
        edges[to].push_back(from);
    }
}

int main() {
    ios::sync_with_stdio(false);
	cin.tie(NULL);
    cout.tie(NULL);
    input();
    for(int i = 1; i <= nodeCount; i++){
        if(visitedOrders[i] > 0) continue;
        order = 1;
        findBreakPoints(i, i);
    }
    cout << breakEdges.size() << "\n";
    for(auto i : breakEdges)
        cout << i.first << " " << i.second << "\n";
    return 0;
}
