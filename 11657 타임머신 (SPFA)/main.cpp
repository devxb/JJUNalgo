//
//  main.cpp
//  11657 타임머신 (SPFA)
//
//  Created by 이준영 on 12/08/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <vector>
#include <deque>
#include <vector>

using namespace std;

int N, M, cle = 2100000000;
long long int visit[505], check[505], node_max[505];
vector<vector<pair<int,int> > > vec;
deque<int> deq;

void spfa(){
    deq.push_back(1);
    node_max[1] = 0;
    while(!deq.empty()){
        int now_idx = deq.front();
        visit[now_idx]++;
        if(visit[now_idx] > N + 1){
            cout << -1 << "\n";
            exit(0);
        }
        deq.pop_front();
        for(int i = 0; i < vec[now_idx].size(); i++){
            long long int next_num = vec[now_idx][i].second + node_max[now_idx];
            int next_idx = vec[now_idx][i].first;
            if(next_num < node_max[next_idx]){
                node_max[next_idx] = next_num;
                deq.push_back(next_idx);
            }
        }
    }
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N >> M;
    vec.resize(N+5);
    for(int i = 1; i <= N; i++){
        node_max[i] = cle;
    }
    for(int i = 1; i <= M; i++){
        int from, to, num;
        cin >> from >> to >> num;
        vec[from].push_back({to,num});
    }
    spfa();
    for(int i = 2; i <= N; i++){
        if(node_max[i] == cle){
            cout << -1 << "\n";
            continue;
        }
        cout << node_max[i] << "\n";
    }
    return 0;
}
