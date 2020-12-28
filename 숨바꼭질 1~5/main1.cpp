//
//  main.cpp
//  6118 숨바꼭질
//
//  Created by 이준영 on 21/08/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <vector>
#include <utility>
#include <deque>

using namespace std;

int N, M;
pair<int,pair<int,int> > num;
vector<vector<int> > vec;
int arr[20005];

void set(){
    for(int i = 1; i <= N; i++){
        arr[i] = 2100000000;
    }
    return;
}

void BFS(){
    deque<pair<int,int> > deq;
    deq.push_back({1,0});
    while(!deq.empty()){
        int now_idx = deq.front().first;
        int now_cnt = deq.front().second;
        deq.pop_front();
        if(arr[now_idx] <= now_cnt){
            continue;
        }
        arr[now_idx] = now_cnt;
        for(int i = 0; i < vec[now_idx].size(); i++){
            int next_idx = vec[now_idx][i];
            if(arr[next_idx] > now_cnt + 1){
                deq.push_back({next_idx, now_cnt + 1});
            }
        }
    }
}

void count(){
    for(int i = 1; i <= N; i++){
        if(arr[i] > num.second.first){
            num = {i, {arr[i], 1}};
            continue;
        }
        if(arr[i] == num.second.first){
            num.second.second++;
        }
    }
    return;
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N >> M;
    vec.resize(N+5);
    set();
    for(int i = 1; i <= M; i++){
        int from, to;
        cin >> from >> to;
        vec[from].push_back(to);
        vec[to].push_back(from);
    }
    BFS();
    count();
    cout << num.first << " " << num.second.first << " " << num.second.second << "\n";
    return 0;
}

