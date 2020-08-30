//
//  main.cpp
//  2611 자동차경주
//
//  Created by 이준영 on 13/07/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <algorithm>
#include <vector>
#include <utility>

using namespace std;

pair<int,int> dp[1005];
vector<vector<pair<int,int> > > vec;
vector<int> print;
int N,M;

void go(int idx, int num, int check){
    if(check == 1 and idx == 1){
        return;
    }
    for(int i = 0; i < vec[idx].size(); i++){
        if(num + vec[idx][i].second >= dp[vec[idx][i].first].first){
            dp[vec[idx][i].first].first = num + vec[idx][i].second;
            dp[vec[idx][i].first].second = idx;
            go(vec[idx][i].first,num + vec[idx][i].second, 1);
        }
    }
}

void path(int idx, int check){
    print.push_back(idx);
    if(check == 1 and idx == 1){
        return;
    }
    path(dp[idx].second, 1);
}

int main() {
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N >> M;
    vec.resize(1005);
    for(int i = 1; i <= M; i++){
        int from,to,num;
        cin >> from >> to >> num;
        vec[from].push_back({to,num});
    }
    go(1,0,0);
    path(1,0);
    cout << dp[1].first << "\n";
    for(int i = print.size()-1; i >= 0; i--){
        cout << print[i] << " ";
    }
    return 0;
}
