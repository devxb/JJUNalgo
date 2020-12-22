//
//  main.cpp
//  1516 게임 개발
//
//  Created by 이준영 on 2020/12/22.
//

#include <iostream>
#include <vector>
#include <utility>
#include <deque>
#include <queue>
#include <algorithm>
using namespace std;

int N, dp[505], cost[505], maxNum = 0;
vector<vector<int> > vec;

int go(int idx){
    int now_cost = 0;
    for(int i = 0; i < vec[idx].size(); i++){
        if(dp[vec[idx][i]] != -1){
            now_cost = max(dp[vec[idx][i]], now_cost);
            continue;
        }
        else{
            now_cost = max(go(vec[idx][i]), now_cost);
        }
    }
    maxNum = max(maxNum, now_cost);
    return dp[idx] = now_cost + cost[idx];
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    vec.resize(N+5);
    for(int i = 1; i <= N; i++){
        dp[i] = -1;
    }
    for(int i = 1; i <= N; i++){
        cin >> cost[i];
        while(true){
            int bui;
            cin >> bui;
            if(bui == -1){
                break;
            }
            vec[i].push_back(bui);
        }
        if(vec[i].empty()){
            dp[i] = cost[i];
        }
    }
    for(int i = 1; i <= N; i++){
        if(dp[i] != -1){
            continue;
        }
        go(i);
    }
    for(int i = 1; i <= N; i++){
        cout << dp[i] << "\n";
    }
    return 0;
}
