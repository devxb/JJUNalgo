//
// xb205
// 2021.01.28
//

#include <iostream>
#include <string>
#include <algorithm>
#include <bitset>
using namespace std;
int N, arr[17][17], dp[65536][16][16], BIT = 1 << 15, INF = 200;

void set(){
    for(int i = 0; i <= 65535; i++){
        for(int j = 0; j <= N; j++){
            for(int l = 0; l <= N; l++){
                if(j == l){
                    continue;
                }
                dp[i][j][l] = 200;
            }
        }
    }
}

int getdp(int idx, int people, int bit, int num){
    int pep = people;
    for(int i = 1; i <= N; i++){
        if((bit & (1 << (i-1))) or num > arr[idx][i]){
            continue;
        }
        int _bit = bit | (1 << (i-1));
        if(dp[_bit][idx][i] <= arr[idx][i]){
            continue;
        }
        dp[_bit][idx][i] = arr[idx][i];
        pep = max(pep, getdp(i, people+1, _bit, arr[idx][i]));
    }
    return pep;
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    set();
    for(int i = 1; i <= N; i++){
        string str;
        cin >> str;
        for(int j = 1; j <= N; j++){
            arr[i][j] = (int)(str[j-1] - '0');
        }
    }
    BIT |= (1 << 0);
    cout << getdp(1, 1, BIT, 0) << "\n";
    return 0;
}
