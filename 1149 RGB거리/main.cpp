//
//  main.cpp
//  1149 RGB거리
//
//  Created by 이준영 on 2020/12/27.
//

#include <iostream>
#include <algorithm>
#include <vector>
#include <utility>
using namespace std;

int N, dp[1005][5];
vector<vector<int> > vec;
pair<int,int> path[] = {{1,2}, {0,2}, {0,1}};

void go(int idx){
    if(idx > N){
        return;
    }
    for(int i = 0; i < 3; i++){
        dp[idx][i] = min(dp[idx-1][path[i].first],dp[idx-1][path[i].second]) + vec[idx][i];
    }
    go(idx+1);
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    vec.resize(N+5);
    for(int i = 1; i <= N; i++){
        for(int j = 1; j <= 3; j++){
            int num;
            cin >> num;
            vec[i].push_back(num);
        }
    }
    go(1);
    cout << min(dp[N][0],min(dp[N][1],dp[N][2])) << "\n";
    return 0;
}
