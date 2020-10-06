//
//  main.cpp
//  11062 카드게임
//
//  Created by 이준영 on 2020/10/04.
//

#include <iostream>
#include <algorithm>

using namespace std;

int T, N;
int arr[1005];
int dp[1005][1005];

int go(int t, int left, int right){
    if(left == right){
        dp[left][right] = arr[left];
        if(t == 2){
            return 0;
        }
        return dp[left][right];
    }
    if(dp[left][right] != 0){
        return dp[left][right];
    }
    int next_t = 0;
    if(t == 1){
        next_t = 2;
    }
    else{
        next_t = 1;
    }
    if(t == 2){
        return min(go(next_t, left+1, right), go(next_t, left, right-1));
    }
    return dp[left][right] = max(go(next_t, left+1, right) + arr[left], go(next_t, left, right-1) + arr[right]);
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> T;
    for(int i = 1; i <= T; i++){
        cin >> N;
        for(int j = 1; j <= N; j++){
            cin >> arr[j];
        }
        go(1, 1, N);
        cout << dp[1][N] << "\n";
         for(int l = 1; l <= N; l++){
             for(int k = l; k <= N; k++){
                 dp[l][k] = 0;
             }
         }
    }
    return 0;
}
