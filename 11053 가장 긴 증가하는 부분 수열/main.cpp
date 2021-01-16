//
//  main.cpp
//  11053 가장 긴 증가하는 부분 수열
//
//  Created by 이준영 on 2021/01/16.
//

#include <iostream>
#include <algorithm>

using namespace std;

int N, arr[1005], dp[1005], num;

void go(int idx){
    if(idx > N){
        return;
    }
    for(int i = idx-1; i > 0; i--){
        if(arr[idx] > arr[i]){
            dp[idx] = max(dp[i], dp[idx]);
        }
    }
    dp[idx]++;
    num = max(num, dp[idx]);
    go(idx+1);
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    for(int i = 1; i <= N; i++){
        cin >> arr[i];
    }
    go(1);
    cout << num << "\n";
    return 0;
}
