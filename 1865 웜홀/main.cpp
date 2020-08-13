//
//  main.cpp
//  1865 웜홀 (SPFA)
//
//  Created by 이준영 on 13/08/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <vector>
#include <algorithm>
#include <deque>
using namespace std;

int TC,N,M,W,cle = 2100000000;;

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> TC;
    for(int i = 1; i <= TC; i++){
        cin >> N >> M >> W;
        vector<vector<pair<int,int> > > vec;
        int check[N+5], visit[N+5], spfa[N+5];
        for(int j = 1; j <= N; j++){
            check[j] = cle;
            visit[j] = 0;
            spfa[j] = 0;
        }
        vec.resize(N+5);
        for(int j = 1; j <= M; j++){
            int S,E,T;
            cin >> S >> E >> T;
            vec[S].push_back({E, T});
            vec[E].push_back({S, T});
        }
        for(int j = 1; j <= W; j++){
            int S, E, T;
            cin >> S >> E >> T;
            vec[S].push_back({E, -T});
        }
        int c = 0;
        for(int j = 1; j <= N; j++){
            if(check[j] != cle or c == 1){
                continue;
            }
            check[j] = 0;
            deque<int> deq;
            deq.push_back(j);
            while(!deq.empty()){
                int now_idx = deq.front();
                deq.pop_front();
                visit[now_idx]++;
                if(visit[now_idx] > N+1){
                    c = 1;
                    break;
                }
                for(int l = 0; l < vec[now_idx].size(); l++){
                    int next_idx = vec[now_idx][l].first;
                    int next_num = check[now_idx] + vec[now_idx][l].second;
                    if(next_num < check[next_idx]){
                        check[next_idx] = next_num;
                        deq.push_back(next_idx);
                    }
                }
            }
        }
        if(c == 0){
            cout << "NO" << "\n";
        }
        else{
            cout << "YES" << "\n";
        }
    }
    return 0;
}
