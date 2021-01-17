//
//  main.cpp
//  1967 트리의 지름
//
//  Created by 이준영 on 2021/01/17.
//

#include <iostream>
#include <vector>
#include <utility>
#include <algorithm>
#include <queue>

using namespace std;

int n, check[10005][5];
vector<vector<pair<int,int> > > vec;
pair<int,int> num;
void go(int start, int marking){
    num = {0,0};
    priority_queue<pair<int,int>, vector<pair<int,int> >, greater<pair<int,int> > > q;
    q.push({0,start});
    check[start][marking] = 0;
    while(!q.empty()){
        int nowIdx = q.top().second;
        int nowNum = q.top().first;
        q.pop();
        if(check[nowIdx][marking] < nowNum){
            continue;
        }
        if(num.first < nowNum){
            num = {nowNum, nowIdx};
        }
        for(int i = 0; i < vec[nowIdx].size(); i++){
            int nextIdx = vec[nowIdx][i].first;
            int nextNum = nowNum + vec[nowIdx][i].second;
            if(check[nextIdx][marking] <= nextNum){
                continue;
            }
            check[nextIdx][marking] = nextNum;
            q.push({nextNum, nextIdx});
        }
    }
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> n;
    for(int i = 1; i <= 2; i++){
        for(int j = 1; j <= n; j++){
            check[j][i] = 2000000000;
        }
    }
    vec.resize(n+5);
    for(int i = 1; i < n; i++){
        int from, to, cost;
        cin >> from >> to >> cost;
        vec[from].push_back({to, cost});
        vec[to].push_back({from, cost});
    }
    go(1,1);
    go(num.second, 2);
    cout << num.first << "\n";
    return 0;
}
