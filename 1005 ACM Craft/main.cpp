//
//  main.cpp
//  1005 ACM Craft
//
//  Created by 이준영 on 2020/11/27.
//

#include <iostream>
#include <queue>
#include <utility>
#include <algorithm>
#include <vector>

using namespace std;

vector<vector<int> > vec;
int T, arr[1005], check[1005];

int go(int idx){
    check[idx] = max(check[idx],arr[idx]);
    for(int i = 0; i < vec[idx].size(); i++){
        if(check[vec[idx][i]] != -1){
            check[idx] = max(check[idx], check[vec[idx][i]] + arr[idx]);
            continue;
        }
        check[idx] = max(check[idx],go(vec[idx][i]) + arr[idx]);
    }
    return check[idx];
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> T;
    for(int i = 1; i <= T; i++){
        int N,K,W;
        cin >> N >> K;
        vector<vector<int> > erase;
        vec = erase;
        vec.resize(N+5);
        for(int j = 1; j <= N; j++){
            arr[j] = -1;
            check[j] = -1;
        }
        
        for(int j = 1; j <= N; j++){
            cin >> arr[j];
        }
        
        for(int j = 1; j <= K; j++){
            int from, to;
            cin >> from >> to;
            vec[to].push_back(from);
        }
        cin >> W;
        cout << go(W) << "\n";
    }
    
    return 0;
}
