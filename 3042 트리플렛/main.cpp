//
//  main.cpp
//  3042 트리플렛
//
//  Created by 이준영 on 19/09/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <string>
#include <vector>

using namespace std;

int N,cnt;
vector<pair<int,int> > vec;

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N;
    for(int i = 1; i <= N; i++){
        string str;
        cin >> str;
        for(int j = 1; j <= N; j++){
            if(str[j-1] != '.'){
                vec.push_back({i,j});
            }
        }
    }
    if(vec.size() == 0){
        cout << 0 << "\n";
        return 0;
    }
    for(int i = 0; i < vec.size()-2; i++){
        for(int j = i+1; j < vec.size()-1; j++){
            float incl_Y = vec[i].first - vec[j].first;
            float incl_X = vec[i].second - vec[j].second;
            float incl;
            if(incl_Y == 0){
                incl = -2000000000;
            }
            else if(incl_X == 0){
                incl = 2000000000;
            }
            else{
                incl = incl_X / incl_Y;
            }
            for(int l = j+1; l < vec.size(); l++){
                float incl_Y = vec[j].first - vec[l].first;
                float incl_X = vec[j].second - vec[l].second;
                float incl2 = 0;
                if(incl_Y == 0){
                    incl2 = -2000000000;
                }
                else if(incl_X == 0){
                    incl2 = 2000000000;
                }
                else{
                    incl2 = incl_X / incl_Y;
                }
                if(incl2 == incl){
                    cnt++;
                }
            }
        }
    }
    cout << cnt << "\n";
    return 0;
}
