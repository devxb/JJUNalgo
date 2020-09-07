//
//  main.cpp
//  11067 모노톤 길
//
//  Created by 이준영 on 07/09/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <utility>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

int T;

void go(){
    int n = 1, m = 1, max_X = 0, cnt = 1;
    cin >> n;
    pair<int, int> check[100005];
    vector<vector<pair<int,int> > > vec;
    vec.resize(100005);
    for(int i = 1; i <= n; i++){
        int X, Y;
        cin >> X >> Y;
        if(X == 0 and Y == 0){
            vec[X].push_back({Y+100000, -1});
            max_X = max(max_X, X);
            continue;
        }
        vec[X].push_back({Y+100000, 1});
        max_X = max(max_X, X);
    }
    int idx = 0, X = 0, Y = 100000;
    while(idx <= n){
        check[cnt] = {X, Y - 100000};
        cnt++;
        pair<int, pair<int,int> > next;
        next = {100000000,{0, 0}};
        int next_X = X, temp_i = 0, c = 0;
        for(next_X = X; next_X <= max_X; next_X++){
            for(int i = 0; i < vec[next_X].size(); i++){
                if(vec[next_X][i].second == -1){
                    continue;
                }
                if(next.first > sqrt(pow(next_X - X,2)) + sqrt(pow(vec[next_X][i].first - Y,2))){
                    next = {sqrt(pow(next_X - X,2)) + sqrt(pow(vec[next_X][i].first - Y,2)),{next_X, vec[next_X][i].first}};
                    temp_i = i;
                    c = 1;
                }
            }
            if(c != 0){
                X = next.second.first;
                Y = next.second.second;
                vec[next_X][temp_i].second = -1;
                break;
            }
        }
        idx++;
    }
    cin >> m;
    for(int i = 1; i <= m; i++){
        int a;
        cin >> a;
        cout << check[a].first << " " << check[a].second << "\n";
    }
    return;
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> T;
    for(int i = 1; i <= T; i++){
        go();
    }
    return 0;
}
