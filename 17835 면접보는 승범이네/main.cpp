//
//  main.cpp
//  17835 면접보는 승범이네
//
//  Created by 이준영 on 28/02/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <algorithm>
#include <vector>
#include <deque>
#include <queue>
#include <utility>

using namespace std;

vector<int> start_num;
vector<vector<pair<int,int> > > vec;
queue<pair<long long int, int>> pq;
int N,M,K;
long long int check[100005][5], print_num, print_city = 100005;

void fill(int n){
    if(n > N){
        return;
    }
    check[n][1] = 9223372036854775806;
    fill(n+1);
}

void go(){
    while(!pq.empty()){
        long long int now_num = pq.front().first;
        int now_city = pq.front().second;
        pq.pop();
        if(check[now_city][1] < now_num){
            continue;
        }
        for(int i = 0; i < vec[now_city].size(); i++){
            if(check[vec[now_city][i].first][1] <= now_num + vec[now_city][i].second){
                continue;
            }
            check[vec[now_city][i].first][1] = now_num + vec[now_city][i].second;
            pq.push({now_num + vec[now_city][i].second, vec[now_city][i].first});
        }
    }
}

void find(){
    for(int i = 1; i <= N; i++){
        if(check[i][2] != -1 && print_num < check[i][1]){
            print_num = check[i][1];
            print_city = i;
        }
    }
}
int main() {
    ios::sync_with_stdio(NULL);
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N >> M >> K;
    fill(1);
    vec.resize(N+5);
    for(int i = 0; i < M; i++){
        int a,b,c;
        cin >> a >> b >> c;
        vec[b].push_back({a,c});
    }
    for(int i = 0; i < K; i++){
        int a;
        cin >> a;
        check[a][2] = -1;
        pq.push({0,a});
    }
    go();
    find();
    printf("%lld\n%lld\n",print_city,print_num);
    return 0;
}

