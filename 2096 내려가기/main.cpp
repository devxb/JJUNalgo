//
// xb205
// 2021.01.24
//

#include <iostream>
#include <algorithm>
using namespace std;

int N;
int dp[3][4][3];
int arr[3][4];

void update(int par, int son){
    dp[son][1][0] = arr[son][1] + max(dp[par][1][0], dp[par][2][0]);
    dp[son][2][0] = arr[son][2] + max(dp[par][1][0], max(dp[par][2][0], dp[par][3][0]));
    dp[son][3][0] = arr[son][3] + max(dp[par][2][0], dp[par][3][0]);
    dp[son][1][1] = arr[son][1] + min(dp[par][1][1], dp[par][2][1]);
    dp[son][2][1] = arr[son][2] + min(dp[par][1][1], min(dp[par][2][1], dp[par][3][1]));
    dp[son][3][1] = arr[son][3] + min(dp[par][2][1], dp[par][3][1]);
    return;
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    for(int i = 1; i <= N; i++){
        int idx = i % 2; 
        cin >> arr[idx][1] >> arr[idx][2] >> arr[idx][3];
        if(i == 1){
            dp[idx][1][0] = dp[idx][1][1] = arr[idx][1];
            dp[idx][2][0] = dp[idx][2][1] = arr[idx][2];
            dp[idx][3][0] = dp[idx][3][1] = arr[idx][3];
            continue;
        }
        update((idx+1)%2, idx);
    }
    int target = N % 2;
    cout << max(dp[target][1][0], max(dp[target][2][0], dp[target][3][0])) << " " << min(dp[target][1][1], min(dp[target][2][1], dp[target][3][1])) << "\n";
    return 0;
}
