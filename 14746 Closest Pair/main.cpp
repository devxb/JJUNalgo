//
//  main.cpp
//  14746 Closest Pair
//
//  Created by 이준영 on 14/08/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <vector>
#include <utility>

using namespace std;

int N, M, c1, c2;
pair<int, int> min_num = {200000000,0};
vector<pair<int,int> > vec;

int rev(int num){
    if(num < 0){
        return num * -1;
    }
    return num;
}

int cal(int a, int b){
    return rev(a - b);
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N >> M;
    cin >> c1 >> c2;
    int min_y = cal(c1, c2);
    for(int i = 1; i <= N; i++){
        int num;
        cin >> num;
        vec.push_back({num,1});
    }
    for(int i = 1; i <= M; i++){
        int num;
        cin >> num;
        vec.push_back({num,2});
    }
    sort(vec.begin(),vec.end());
    for(int i = 0; i < vec.size()-1; i++){
        if(vec[i].second != vec[i+1].second){
            int temp = cal(vec[i].first, vec[i+1].first);
            if(min_num.first > temp){
                min_num = {temp,1};
            }
            else if(min_num.first == temp){
                min_num.second++;
            }
        }
    }
    cout << min_num.first + min_y << " " << min_num.second << "\n";
    return 0;
}

