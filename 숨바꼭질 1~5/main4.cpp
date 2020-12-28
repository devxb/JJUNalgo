//
//  main.cpp
//  13931 숨바꼭질 4
//
//  Created by 이준영 on 09/09/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <deque>
#include <utility>
#include <queue>
#include <vector>

using namespace std;

int N, K, sec;
pair<int, int> par[100005];

void BFS(){
    priority_queue<pair<int,int>, vector<pair<int,int> >, greater<pair<int,int> > > pq;
    pq.push({0,N});
    par[N] = {0, -1};
    while(!pq.empty()){
        int now_sec = pq.top().first;
        int now_idx = pq.top().second;
        pq.pop();
        if(now_idx == K){
            sec = now_sec;
            break;
        }
        if(now_idx + 1 <= 100000){
            if(par[now_idx + 1].first == 0 or par[now_idx + 1].first > now_sec + 1 or now_idx == N){
                pq.push({now_sec + 1, now_idx + 1});
                par[now_idx + 1] = {now_sec+1, now_idx};
            }
        }
        if(now_idx * 2 <= 100000){
            if(par[now_idx * 2].first == 0 or par[now_idx * 2].first > now_sec + 1 or now_idx == N){
                pq.push({now_sec + 1, now_idx * 2});
                par[now_idx * 2] = {now_sec+1, now_idx};
            }
        }
        if(now_idx - 1 >= 0){
            if(par[now_idx - 1].first == 0 or par[now_idx - 1].first > now_sec + 1 or now_idx == N){
                pq.push({now_sec + 1, now_idx - 1});
                par[now_idx - 1] = {now_sec+1, now_idx};
            }
        }
    }
    return;
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N >> K;
    BFS();
    cout << sec << "\n";
    int temp_sec = sec;
    int temp_K = K;
    deque<int> par_deq;
    while(temp_sec > 0){
        par_deq.push_back(par[temp_K].second);
        temp_K = par[temp_K].second;
        temp_sec = temp_sec - 1;
    }
    while(!par_deq.empty()){
        cout << par_deq.back() << " ";
        par_deq.pop_back();
    }
    cout << K << "\n";
    return 0;
}
