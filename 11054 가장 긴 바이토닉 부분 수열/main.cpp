//
// xb205
// 2021.01.24
//

#include <iostream>
#include <algorithm>
using namespace std;

int dp[1005][3], arr[1005], N;

void getdp(int idx){
    for(int i = 1; i < idx; i++){
        if(arr[i] < arr[idx]){
            dp[idx][1] = max(dp[i][1], dp[idx][1]);
        }
    }
    for(int i = N; i > N-idx+1; i--){
        if(arr[i] < arr[N-idx+1]){
            dp[N-idx+1][2] = max(dp[i][2], dp[N-idx+1][2]);
        }
    }
    dp[idx][1]++;
    dp[N-idx+1][2]++;
    if(idx < N){
        getdp(idx+1);
    }
}

int find(){
    int num = 0;
    for(int i = 1; i <= N; i++){
        num = max(num, dp[i][1]+dp[i][2]-1);
    }
    return num;
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    for(int i = 1; i <= N; i++){
        cin >> arr[i];
    }
    getdp(1);
    cout << find() << "\n";
    return 0;
}
