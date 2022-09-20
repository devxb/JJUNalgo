#include <iostream>
#include <algorithm>
#include <cmath>
#include <vector>
#include <string>

using namespace std;

vector<vector<int> > edges(10001);
int nodeCount, edgeCount, order;
int visitedOrders[10001];
bool isBreakPoint[10001];

int findBreakPoints(int befIdx, int idx){
    if(visitedOrders[idx] > 0) return visitedOrders[idx];
    visitedOrders[idx] = order++;
    int ans = visitedOrders[idx];
    int diffTree = 0;
    for(int i = 0; i < edges[idx].size(); i++){
        int nextIdx = edges[idx][i];
        if(befIdx == nextIdx) continue;
        if(visitedOrders[nextIdx] == 0) diffTree++;
        int temp = findBreakPoints(idx, nextIdx);
        if(visitedOrders[idx] <= temp) isBreakPoint[idx] = true;
        ans = min(ans, temp);
    }
    if(befIdx == idx && diffTree > 1) isBreakPoint[idx] = true;
    else if(befIdx == idx && diffTree <= 1) isBreakPoint[idx] = false;
    if(edges[idx].size() == 1) isBreakPoint[idx] = false;
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
    int resultCount = 0;
    string resultNodes = "";
    for(int i = 1; i <= nodeCount; i++){
        if(!isBreakPoint[i]) continue;
        resultCount++;
        resultNodes.append(to_string(i)).append(" ");
    }
    cout << resultCount << "\n" << resultNodes << "\n"; 
    return 0;
}
