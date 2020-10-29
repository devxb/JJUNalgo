//
//  main.cpp
//  11560 다항식 게임
//
//  Created by 이준영 on 2020/10/28.
//

#include <iostream>

using namespace std;

long long int T;
long long int dp[25][300];

void go(int idx){
    if(idx > 20){
        return;
    }
    int i = 0;
    while(dp[idx-1][i] != 0){
        long long int now_coe = dp[idx-1][i];
        for(int root = 0; root <= idx; root++){
            long long int next_root = i + root;
            dp[idx][next_root] = dp[idx][next_root] + (now_coe * 1);
        }
        i++;
    }
    go(idx+1);
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> T;
    dp[0][0] = 1;
    go(1);
    for(int i = 1; i <= T; i++){
        long long int n,k;
        cin >> k >> n;
        cout << dp[k][n] << "\n";
    }
    return 0;
}
