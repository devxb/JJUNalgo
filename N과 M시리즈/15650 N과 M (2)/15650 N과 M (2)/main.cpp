//
//  main.cpp
//  15650 N과 M (2)
//
//  Created by 이준영 on 2021/01/02.
//

#include <iostream>
#include <deque>
#include <vector>
#include <algorithm>

using namespace std;

int N, M, check[10];
deque<int> deq;

void go(int idx, int cnt){
    if(cnt >= M){
        for(int i = 0; i < deq.size(); i++){
            cout << deq[i] << " ";
        }
        cout << "\n";
        return;
    }
    for(int i = idx; i <= N; i++){
        if(check[i] == 1){
            continue;
        }
        deq.push_back(i);
        check[i] = 1;
        go(i, cnt+1);
        check[i] = 0;
        deq.pop_back();
    }
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> M;
    for(int i = 1; i <= N; i++){
        deq.push_back(i);
        check[i] = 1;
        go(i, 1);
        check[i] = 0;
        deq.pop_back();
    }
    return 0;
}
