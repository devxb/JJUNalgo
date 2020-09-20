//
//  main.cpp
//  9009 피보나치
//
//  Created by 이준영 on 20/09/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <deque>

using namespace std;

int fibo[100005];
int T, N, start_idx = 2;
deque<int> deq;

void make_fibo(int idx){
    if(fibo[idx-1] > N){
        start_idx = idx;
        return;
    }
    fibo[idx] = fibo[idx-2] + fibo[idx-1];
    make_fibo(idx+1);
}

void go(int idx, int num){
    if(num == 0){
        while(!deq.empty()){
            cout << deq.front() << " ";
            deq.pop_front();
        }
        cout << "\n";
        return;
    }
    deq.push_front(fibo[idx]);
    num = num - fibo[idx];
    while(num < fibo[idx] or fibo[idx+1] < num){
        idx = idx - 1;
    }
    go(idx, num);
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> T;
    fibo[0] = 0;
    fibo[1] = 1;
    for(int i = 1; i <= T; i++){
        cin >> N;
        make_fibo(start_idx);
        int go_idx = 0;
        int j = 0;
        while(true){
            if(fibo[j] > N){
                break;
            }
            go_idx = j;
            j++;
        }
        go(go_idx, N);
    }
    return 0;
}
