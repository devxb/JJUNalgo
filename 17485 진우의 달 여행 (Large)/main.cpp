//
//  main.cpp
//  진우의 달 여행
//
//  Created by 이준영 on 23/05/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <algorithm>

using namespace std;

int dp[1005][1005][5];
int arr[1005][1005];
int N,M;

void go(int y, int x){
    if(x == 1){
        dp[y][x][1] = 1000000;
        dp[y][x][2] = dp[y-1][x][3] + arr[y][x];
        dp[y][x][3] = min(dp[y-1][x+1][1] + arr[y][x], dp[y-1][x+1][2] + arr[y][x]);
    }
    else if(x == M){
        dp[y][x][1] = min(dp[y-1][x-1][2] + arr[y][x], dp[y-1][x-1][3] + arr[y][x]);
        dp[y][x][2] = dp[y-1][x][1] + arr[y][x];
        dp[y][x][3] = 1000000;
    }
    else{
        dp[y][x][1] = min(dp[y-1][x-1][2] + arr[y][x] , dp[y-1][x-1][3] + arr[y][x]);
        dp[y][x][2] = min(dp[y-1][x][3] + arr[y][x], dp[y-1][x][1] + arr[y][x]);
        dp[y][x][3] = min(dp[y-1][x+1][1] + arr[y][x], dp[y-1][x+1][2] + arr[y][x]);
    }
    if(y == N and x == M){
        return;
    }
    else{
        if(x == M){
            go(y+1,1);
        }
        else{
            go(y,x+1);
        }
    }
    return;
}

int main() {
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N >> M;
    for(int i = 1; i <= N; i++){
        for(int j = 1; j <= M; j++){
            cin >> arr[i][j];
            if(i == 1){
                dp[i][j][1] = arr[i][j];
                dp[i][j][2] = arr[i][j];
                dp[i][j][3] = arr[i][j];
            }
        }
    }
    go(2,1);
    int min_num = 1000000;
    for(int i = 1; i <= M; i++){
        for(int j = 1; j <= 3; j++){
            min_num = min(min_num, dp[N][i][j]);
        }
    }
    cout << min_num << "\n";
    return 0;
}
