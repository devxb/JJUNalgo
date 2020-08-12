//
//  main.cpp
//  11657 타임머신
//
//  Created by 이준영 on 10/08/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <vector>
#include <utility>
#include <cmath>
#include <deque>

using namespace std;

int N, M, cle = 2000000000;
vector<vector<pair<int,int> > > vec;
int check[505];
int visit[505];

void bel(int idx, int num, int cycle){
    if(check[idx] <= num){
        return;
    }
    check[idx] = num;
    visit[idx] = cycle;
    if(visit[idx] >= N+1){
        cout << -1 << "\n";
        exit(0);
    }
    for(int i = 0; i < vec[idx].size(); i++){
        bel(vec[idx][i].first, check[idx] + vec[idx][i].second, cycle+1);
    }
    return;
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N >> M;
    vec.resize(N+5);
    for(int i = 1; i <= N; i++){
        check[i] = cle;
    }
    for(int i = 1; i <= M; i++){
        int from, to, num;
        cin >> from >> to >> num;
        vec[from].push_back({to, num});
    }
    for(int i = 1; i <= N; i++){
        bel(1,0,i);
    }
    for(int i = 2; i <= N; i++){
        if(check[i] == cle){
            cout << -1 << "\n";
            continue;
        }
        cout << check[i] << "\n";
    }
    return 0;
}
