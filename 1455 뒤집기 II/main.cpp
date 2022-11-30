#include <iostream>
#include <algorithm>
#include <vector>
#include <deque>
#include <cmath>
#include <string>

using namespace std;

int N, M;
string coins[100];

void flip(int y, int x){
    for(int i = 0; i <= y; i++)
        for(int j = 0; j <= x; j++)
            coins[i][j] = coins[i][j] == '1' ? '0' : '1';
}

int flipCoin(int y, int x){
    int flipCount = 0;
    if(coins[y][x] == '1') {
        flip(y, x);
        flipCount++;
    }
    if(y == 0 && x == 0) return flipCount;
    if(x == 0) return flipCoin(y-1, M-1) + flipCount;
    return flipCoin(y, x-1) + flipCount;
}

void input(){
    cin >> N >> M;
    for(int i = 0; i < N; i++) cin >> coins[i];
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    input();
    cout << flipCoin(N-1, M-1) << "\n";
    return 0;
}
