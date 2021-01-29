//
// xb205
// 2021.01.29
//

#include <iostream>
#include <deque>
using namespace std;
int K, cnt, bottle[1000005];

void getbottle(int idx){
    if(bottle[idx] <= 1){
        return;
    }
    cnt -= bottle[idx]/2;
    bottle[idx+1] += bottle[idx]/2;
    bottle[idx] %= 2;
    getbottle(idx+1);
    return;
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> bottle[1] >> K;
    cnt = bottle[1];
    int num = 0;
    while(cnt > K){
        if(bottle[1] <= 1){
            bottle[1]++; 
            num++;
            cnt++;
        }
        getbottle(1);
    }
    cout << num << "\n";
    return 0;
}
