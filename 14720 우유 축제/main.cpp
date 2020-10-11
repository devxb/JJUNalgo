//
//  main.cpp
//  14720 우유축제
//
//  Created by 이준영 on 20/07/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <algorithm>
#include <utility>
#include <deque>

using namespace std;

int N,max_num;
int milk[1005];
int dp[5][1005];
deque<int> deq;

int check_m(int milk){
    if(milk == 0){
        return 1;
    }
    if(milk == 1){
        return 2;
    }
    if(milk == 2){
        return 0;
    }
    return -1;
}

void go(int idx){
    int m = 0;
    int cnt = 1;
    for(int i = idx; i <= N; i++){
        if(dp[m][i] > cnt){
            break;
        }
        if(milk[i] == m){
            dp[m][i] = cnt;
            max_num = max(max_num,dp[m][i]);
            m = check_m(m);
            cnt++;
        }
    }
    return;
}

int main(int argc, const char * argv[]) {
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N;
    for(int i = 1; i <= N; i++){
        cin >> milk[i];
        if(milk[i] == 0){
            deq.push_back(i);
        }
    }
    while(!deq.empty()){
        go(deq.front());
        deq.pop_front();
    }
    cout << max_num << "\n";
    return 0;
}
