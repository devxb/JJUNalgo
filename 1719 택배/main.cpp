//
//  main.cpp
//  1719 택배
//
//  Created by 이준영 on 2020/12/31.
//

#include <iostream>
#include <utility>

using namespace std;

int n, m, arr[205][205];
pair<int,int> dp[205][205];

void path(int idx, int target, int change){
    if(dp[idx][target].second == target){
        dp[target][change].second = idx;
        return;
    }
    path(dp[idx][target].second, target, change);
    return;
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> n >> m;
    for(int i = 1; i <= n; i++){
        for(int j = 1; j <= n; j++){
            dp[i][j] = {100000000,0};
        }
    }
    for(int i = 1; i <= m; i++){
        int from, to, num;
        cin >> from >> to >> num;
        arr[from][to] = num;
        arr[to][from] = num;
        dp[from][to] = {num, to};
        dp[to][from] = {num, from};
    }
    for(int mid = 1; mid <= n; mid++){
        for(int from = 1; from <= n; from++){
            for(int to = 1; to <= n; to++){
                if(dp[from][to].first > dp[from][mid].first + dp[mid][to].first){
                    dp[from][to] = {dp[from][mid].first + dp[mid][to].first, mid};
                }
            }
        }
    }
    for(int i = 1; i <= n; i++){
        for(int j = 1; j <= n; j++){
            path(dp[i][j].second, i, j);
        }
    }
    for(int i = 1; i <= n; i++){
        for(int j = 1; j <= n; j++){
            if(i == j){
                cout << "-" << " ";
                continue;
            }
            cout << dp[i][j].second << " ";
        }
        cout << "\n";
    }
    return 0;
}
