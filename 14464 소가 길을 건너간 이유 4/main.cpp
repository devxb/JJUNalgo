//
//  main.cpp
//  14464
//
//  Created by 이준영 on 17/07/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int C,N;
int check[20005];
vector<int> chicken;
vector<pair<int,int> > cow;

int go(){
    int cnt = 0;
    for(int i = 0; i < C; i++){
        int now_C = chicken[i];
        for(int j = 0; j < N; j++){
            if(cow[j].first >= now_C and now_C >= cow[j].second and check[j] == 0){
                cnt++;
                check[j] = 1;
                break;
            }
        }
    }
    return cnt;
}

int main() {
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> C >> N;
    for(int i = 1; i <= C; i++){
        int chic;
        cin >> chic;
        chicken.push_back(chic);
    }
    for(int i = 1; i <= N; i++){
        int from,to;
        cin >> from >> to;
        cow.push_back({to,from});
    }
    sort(chicken.begin(),chicken.end());
    sort(cow.begin(),cow.end());
    cout << go() << "\n";
    return 0;
}
