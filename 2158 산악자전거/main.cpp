//
//  main.cpp
//  2158 산악자전거
//
//  Created by 이준영 on 2020/12/30.
//

#include <iostream>
#include <vector>
#include <queue>
#include <utility>
#include <algorithm>
#include <cmath>

using namespace std;
typedef long double ld;

int V, R, C, arr[105][105];
pair<ld,ld> check[105][105];
ld num = 1000000000000000000;
int dy[] = {-1,0,1,0};
int dx[] = {0,-1,0,1};
priority_queue<pair<pair<ld,ld>, pair<int,int> >, vector<pair<pair<ld, ld>, pair<int,int> > >, greater<pair<pair<ld, ld>, pair<int,int> > > > pq;

long double getV(){
    return 0;
}

void BFS(){
    while(!pq.empty()){
        int nowX = pq.top().second.second;
        int nowY = pq.top().second.first;
        ld nowT = pq.top().first.first;
        ld nowV = pq.top().first.second;
        pq.pop();
        if(nowY == R and nowX == C){
            num = min(num,nowT);
            return;
        }
        if(check[nowY][nowX].first < nowT){
            continue;
        }
        if(check[nowY][nowX].first == nowT and check[nowY][nowX].second > nowV){
            continue;
        }
        for(int i = 0; i < 4; i++){
            int nextY = nowY + dy[i];
            int nextX = nowX + dx[i];
            if(nextY > R or nextY < 1 or nextX > C or nextX < 1){
                continue;
            }
            ld nextT = nowT + (1 / nowV);
            ld nextV = nowV * pow(2,arr[nowY][nowX] - arr[nextY][nextX]);
            if(check[nextY][nextX].first < nextT){
                continue;
            }
            if(check[nextY][nextX].first == nextT and check[nextY][nextX].second >= nextV){
                continue;
            }
            check[nextY][nextX] = {nextT,nextV};
            pq.push({{nextT,nextV},{nextY,nextX}});
        }
    }
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    for(int i = 1; i <= 100; i++){
        for(int j = 1; j <= 100; j++){
            check[i][j] = {num,-1};
        }
    }
    cin >> V >> R >> C;
    for(int i = 1; i <= R; i++){
        for(int j = 1; j <= C; j++){
            cin >> arr[i][j];
        }
    }
    pq.push({{0,V},{1,1}});
    check[1][1] = {0,V};
    BFS();
    cout << fixed;
    cout.precision(2);
    cout << num << "\n";
    return 0;
}
