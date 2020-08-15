//
//  main.cpp
//  1937 욕심쟁이 판다
//
//  Created by 이준영 on 26/07/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <algorithm>

using namespace std;

int N,max_num = 1;
int arr[505][505];
int dp[505][505];
int go(int be_Y, int be_X, int Y, int X){
    if(arr[be_Y][be_X] >= arr[Y][X] or Y > N or Y < 1 or X > N or X < 1){
        return 0;
    }
    if(dp[Y][X] != 0){
        return dp[Y][X];
    }
    return dp[Y][X] = 1 + max(max(max(max(go(Y, X, Y+1, X),go(Y, X, Y, X+1)),go(Y, X, Y-1, X)),go(Y, X, Y, X-1)),dp[Y][X]);
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N;
    for(int i = 1; i <= N; i++){
        for(int j = 1; j <= N; j++){
            cin >> arr[i][j];
        }
    }
    arr[0][0] = -1;
    for(int i = 1; i <= N; i++){
        for(int j = 1; j <= N; j++){
            go(0,0,i,j);
            max_num = max(max_num,dp[i][j]);
        }
    }
    cout << max_num << "\n";
    return 0;
}
