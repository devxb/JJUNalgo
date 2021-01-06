//
//  main.cpp
//  16562 친구비
//
//  Created by 이준영 on 2021/01/05.
//

#include <iostream>
#include <algorithm>

using namespace std;

int N, M, K, fri[10005], uni[10005], cost[10005], check[10005];

int find(int idx){
    if(uni[idx] == idx){
        return idx;
    }
    return uni[idx] = find(uni[idx]);
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> M >> K;
    for(int i = 1; i <= N; i++){
        uni[i] = i;
        cin >> fri[i];
        cost[i] = fri[i];
    }
    for(int i = 1; i <= M; i++){
        int v, w, f1, f2;
        cin >> v >> w;
        f1 = min(v,w);
        f2 = max(v,w);
        uni[find(f2)] = find(f1);
    }
    for(int i = 1; i <= N; i++){
        uni[i] = find(i);
    }
    for(int i = 1; i <= N; i++){
        cost[uni[i]] = min(cost[uni[i]], fri[i]);
    }
    int num = 0;
    for(int i = 1; i <= N; i++){
        if(check[uni[i]] == 0){
            check[uni[i]] = 1;
            num += cost[uni[i]];
        }
    }
    if(num <= K){
        cout << num << "\n";
    }
    else{
        cout << "Oh no" << "\n";
    }
    return 0;
}
