//
//  main.cpp
//  12785 토쟁이의 등굣길
//
//  Created by 이준영 on 2021/01/10.
//

#include <iostream>

using namespace std;

int w, h, x, y;
long long dp[215][215], dii = 1000007;

void set(){
    for(int i = 0; i <= h; i++){
        for(int j = 0; j <= w; j++){
            dp[i][j] = 0;
        }
    }
}

long long fill(int sx, int sy, int tx, int ty){
    set();
    dp[sy][sx] = 1;
    for(int i = sy; i <= h; i++){
        for(int j = sx; j <= w; j++){
            if(i == sy and j == sx){
                continue;
            }
            dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % dii;
        }
    }
    return dp[ty][tx] % dii;
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> w >> h >> x >> y;
    fill(1, 1, w, h);
    if(w == x and h == y){
        cout << fill(1, 1, w, h) % dii << "\n";
    }
    else{
        cout << (fill(1, 1, x, y) * fill(x, y, w, h)) % dii << "\n";
    }
    return 0;
}
