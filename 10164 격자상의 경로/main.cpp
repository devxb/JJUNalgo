//
//  main.cpp
//  10164 격자상의 경로
//
//  Created by 이준영 on 2020/11/24.
//

#include <iostream>
#include <utility>
#include <algorithm>

using namespace std;

int N, M, K, num;
pair<int,int> P;
int dp[20][20];

void set(){
    for(int i = 0; i <= N; i++){
        for(int j = 0; j <= M; j++){
            dp[i][j] = 0;
        }
    }
}

int go(int Y, int X){
    for(int i = Y; i <= N; i++){
        for(int j = X; j <= M; j++){
            if(i == Y and j == X){
                continue;
            }
            dp[i][j] = dp[i-1][j] + dp[i][j-1];
        }
    }
    return dp[N][M];
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> M >> K;
    int cnt = 1;
    for(int i = 1; i <= N; i++){
        for(int j = 1; j <= M; j++){
            if(cnt == K){
                P = {i,j};
            }
            cnt++;
        }
    }
    dp[1][1] = 1;
    go(1,1);
    if(P.first != 0 and P.second != 0){
        num = num + dp[P.first][P.second];
        set();
        dp[P.first][P.second] = 1;
        num = num * go(P.first,P.second);
    }
    else{
        num = num + dp[N][M];
    }
    cout << num << "\n";
    return 0;
}

