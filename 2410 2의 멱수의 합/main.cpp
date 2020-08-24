//
//  main.cpp
//  2410 2의 멱수의 합
//
//  Created by 이준영 on 24/08/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>

using namespace std;

int N;
int dp[1000005];

void go(int idx, int num){
    if(idx > N){
        return;
    }
    dp[idx] = (dp[idx] % 1000000000 + dp[idx - num] % 1000000000) % 1000000000;
    go(idx+1, num);
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N;
    dp[0] = 1;
    for(int i = 1; i <= N; i = i * 2){
        go(i,i);
    }
    cout << dp[N] % 1000000000 << "\n";
    return 0;
}
