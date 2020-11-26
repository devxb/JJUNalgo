//
//  main.cpp
//  17911 Keyboards in Concert
//
//  Created by 이준영 on 2020/11/23.
//

#include <iostream>
#include <algorithm>

using namespace std;

int n, m;

int ins[1005][1005];
int tune[1005];
int dp[1005];

int distance(int inst, int idx, int cnt){
    while(idx <= m && ins[inst][tune[idx]] == 1){
        dp[idx] = cnt;
        idx++;
    }
    return idx;
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> n >> m;
    int k;
    for(int i = 1; i <= n; i++){
        cin >> k;
        for(int j = 1; j <= k; j++){
            int num;
            cin >> num;
            ins[i][num] = 1;
        }
    }
    for(int i = 1; i <= m; i++){
        cin >> tune[i];
    }
    int now = 1, cnt = 0;
    dp[1] = 0;
    while(now <= m){
        int temp = now;
        for(int i = 1; i <= n; i++){
            now = max(now, distance(i, temp, cnt));
        }
        cnt++;
    }
    cout << dp[m] << "\n";
    return 0;
}

