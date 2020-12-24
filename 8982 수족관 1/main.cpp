//
//  main.cpp
//  8982 수족관 1
//
//  Created by 이준영 on 2020/12/24.
//

#include <iostream>
#include <vector>
#include <utility>
#include <algorithm>

using namespace std;

int N, K, R, water, waterCheck[40005], wall[40005];
vector<pair<pair<int,int>,pair<int,int> > > sink;

void goLeft(int idx, int height){
    int water = 0;
    for(int i = idx; i >= 0; i--){
        if(wall[i] < height){
            height = wall[i];
        }
        if(waterCheck[i] > wall[i] - height){
            waterCheck[i] = wall[i] - height;
        }
    }
    return;
}

void goRight(int idx, int height){
    int water = 0;
    for(int i = idx; i < R; i++){
        if(wall[i] < height){
            height = wall[i];
        }
        if(waterCheck[i] > wall[i] - height){
            waterCheck[i] = wall[i] - height;
        }
    }
    return;
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    int temp_a = 0, temp_b = 0;
    for(int i = 1; i <= N; i++){
        int a, b;
        cin >> a >> b;
        if(i % 2 == 1){
            waterCheck[a] = b;
            wall[a] = b;
            for(int j = temp_a; j < a; j++){
                wall[j] = b;
                waterCheck[j] = b;
            }
        }
        else{
            temp_a = a;
            temp_b = b;
        }
        R = max(R,a);
    }
    
    cin >> K;
    for(int i = 1; i <= K; i++){
        int a,b,c,d;
        cin >> a >> b >> c >> d;
        sink.push_back({{a,b},{c,d}});
    }
    
    sort(sink.begin(), sink.end());
    
    for(int i = 0; i < sink.size(); i++){
        for(int j = sink[i].first.first; j < sink[i].second.first; j++){
            waterCheck[j] = 0;
        }
        goLeft(sink[i].first.first-1,sink[i].first.second);
        goRight(sink[i].second.first, sink[i].first.second);
    }
    for(int i = 0; i < R; i++){
        water += waterCheck[i];
    }
    cout << water << "\n";
    return 0;

}
