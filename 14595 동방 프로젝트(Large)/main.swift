//
//  main.cpp
//  14595 동방 프로젝트(Large)
//
//  Created by 이준영 on 2020/11/14.
//

#include <iostream>
#include <algorithm>
#include <queue>
#include <vector>
#include <utility>

using namespace std;

int N, M, cnt, right;
int par[1000005];
int check[1000005];
priority_queue<pair<int,int>, vector<pair<int,int> >, greater<pair<int,int> > > pq;
int find(int num){
    if(par[num] == num){
        return num;
    }
    return par[num] = find(par[num]);
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> M;
    for(int i = 1; i <= N; i++){
        par[i] = i;
    }
    for(int i = 1; i <= M; i++){
        int a, b;
        cin >> a >> b;
        pq.push({a,b});
    }
    int right = 0;
    while(!pq.empty()){
        int a = pq.top().first;
        int b = pq.top().second;
        if(a < right){
            a = right;
        }
        pq.pop();
        int num = find(a);
        for(int j = a; j <= b; j++){
            par[j] = num;
        }
        right = b;
    }
    for(int i = 1; i <= N; i++){
        if(check[par[i]] == 0){
            cnt++;
            check[par[i]] = 1;
        }
    }
    cout << cnt << "\n";
    return 0;
}
