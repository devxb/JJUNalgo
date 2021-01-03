//
//  main.cpp
//  9466 팀 프로젝트
//
//  Created by 이준영 on 2021/01/02.
//

#include <iostream>
#include <vector>
#include <algorithm>
#include <deque>

using namespace std;

int T, check[100005], group[100005], cnt, target;
bool gcheck[100005], ok;

void go(int idx, int num){
    check[idx] = num;
    if(check[group[idx]] == num){
        ok = true;
        gcheck[idx] = ok;
        target = group[idx];
        if(idx == target){
            ok = false;
        }
        return;
    }
    if((check[group[idx]] != num and check[group[idx]] != -1) or gcheck[group[idx]] == true){
        return;
    }
    go(group[idx], num);
    gcheck[idx] = ok;
    if(idx == target){
        ok = false;
        return;
    }
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> T;
    for(int i = 1; i <= T; i++){
        int n;
        cin >> n;
        for(int j = 1; j <= n; j++){
            check[j] = -1;
            group[j] = -1;
            gcheck[j] = false;
        }
        for(int j = 1; j <= n; j++){
            cin >> group[j];
        }
        cnt = 0;
        for(int j = 1; j <= n; j++){
            if(gcheck[j] == true){
                continue;
            }
            ok = false;
            target = -1;
            go(j, j);
            if(gcheck[j] == false){
                cnt++;
            }
        }
        cout << cnt << "\n";
    }
    return 0;
}
