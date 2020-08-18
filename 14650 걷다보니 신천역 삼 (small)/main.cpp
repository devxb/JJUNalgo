//
//  main.cpp
//  14650 걷다보니 신천역 삼 (Small)
//
//  Created by 이준영 on 08/07/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <algorithm>

using namespace std;

int N,cnt;

void go(int idx, int num){
    if(idx == N){
        if(num % 3 == 0){
            cnt++;
        }
        return;
    }
    go(idx+1,num+2);
    go(idx+1,num+1);
    go(idx+1,num+0);
}

int main() {
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N;
    go(1,1);
    go(1,2);
    cout << cnt << "\n";
    return 0;
}
