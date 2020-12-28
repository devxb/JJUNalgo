//
//  main.cpp
//  1697 숨바꼭질
//
//  Created by 이준영 on 31/05/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <algorithm>
#include <queue>
#include <utility>

using namespace std;

int N,K;
int check[200005];
void BFS(int now){
    queue<pair<int,int> > que;
    que.push({0,now});
    while(!que.empty()){
        int now_idx = que.front().second;
        int now_num = que.front().first;
        que.pop();
        if(now_idx == K){
            cout << now_num << "\n";
            return;
        }
        if(check[now_idx+1] == 0 and now_idx+1 <= 100000){
            check[now_idx+1] = 1;
            que.push({now_num+1, now_idx+1});
        }
        if(check[now_idx-1] == 0 and now_idx-1 >= 0){
            check[now_idx-1] = 1;
            que.push({now_num+1, now_idx-1});
        }
        if(check[now_idx*2] == 0 and now_idx*2 <= 100000){
            check[now_idx*2] = 1;
            que.push({now_num+1, now_idx*2});
        }
    }
}

int main() {
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N >> K;
    BFS(N);
    return 0;
}

