//
//  main.cpp
//  1261 알고스팟
//
//  Created by 이준영 on 30/07/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <algorithm>
#include <string>
#include <deque>
#include <utility>

using namespace std;

int N, M, print = 1000000000;
int arr[105][105], check[105][105];
int dy[] = {-1, 0, 1, 0};
int dx[] = {0, -1, 0, 1};

void set(){
    for(int i = 0; i <= 100; i++){
        for(int j = 0; j <= 100; j++){
            check[i][j] = print;
        }
    }
}

void BFS(){
    deque<pair<int,pair<int,int> > > deq;
    deq.push_back({0,{1,1}});
    while(!deq.empty()){
        int now_num = deq.front().first;
        int now_y = deq.front().second.first;
        int now_x = deq.front().second.second;
        deq.pop_front();
        if(check[now_y][now_x] <= now_num){
            continue;
        }
        check[now_y][now_x] = now_num;
        if(now_y == N and now_x == M){
            print = min(print, now_num);
            continue;
        }
        for(int i = 0; i < 4; i++){
            int next_y = now_y + dy[i];
            int next_x = now_x + dx[i];
            if(next_y > 0 and next_y <= N and next_x > 0 and next_x <= M){
                if(arr[next_y][next_x] == 1 and now_num + 1 < check[next_y][next_x]){
                    deq.push_back({now_num+1, {next_y, next_x}});
                }
                if(arr[next_y][next_x] == 0 and now_num < check[next_y][next_x]){
                    deq.push_back({now_num, {next_y, next_x}});
                }
            }
        }
    }
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    cin.tie(NULL);
    cout.tie(NULL);
    set();
    cin >> M >> N;
    for(int i = 1; i <= N; i++){
        string str;
        cin >> str;
        for(int j = 1; j <= M; j++){
            arr[i][j] = (int)str[j-1] - 48;
        }
    }
    BFS();
    cout << print << "\n";
    return 0;
}
