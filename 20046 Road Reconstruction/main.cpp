//
//  main.cpp
//  20046 Road Reconstruction
//
//  Created by 이준영 on 2020/10/13.
//

#include <iostream>
#include <queue>
#include <vector>
#include <utility>

using namespace std;

int m, n;
int arr[1005][1005];
int check[1005][1005];
int dy[] = {-1, 0, 1, 0};
int dx[] = {0, -1, 0, 1};

void BFS(){
    priority_queue<pair<int,pair<int,int> >, vector<pair<int,pair<int,int> > >, greater<pair<int,pair<int,int> > > > pq;
    pq.push({arr[1][1],{1,1}});
    while(!pq.empty()){
        int now_Y = pq.top().second.first;
        int now_X = pq.top().second.second;
        int now_num = pq.top().first;
        pq.pop();
        if(check[now_Y][now_X] <= now_num){
            continue;
        }
        check[now_Y][now_X] = now_num;
        if(now_Y == m and now_X == n){
            break;
        }
        for(int i = 0; i < 4; i++){
            int next_Y = now_Y + dy[i];
            int next_X = now_X + dx[i];
            int next_num = now_num + arr[next_Y][next_X];
            if(next_num < check[next_Y][next_X] and next_Y <= m and next_Y > 0 and next_X > 0 and next_X <= n and arr[next_Y][next_X] != -1){
                pq.push({next_num, {next_Y, next_X}});
            }
        }
    }
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> m >> n;
    for(int i = 1; i <= m; i++){
        for(int j = 1; j <= n; j++){
            cin >> arr[i][j];
        }
    }
    if(arr[1][1] == -1 or arr[m][n] == -1){
        cout << -1 << "\n";
        return 0;
    }
    for(int i = 1; i <= m; i++){
        for(int j = 1; j <= n; j++){
            check[i][j] = 200000000;
        }
    }
    BFS();
    if(check[m][n] == 200000000){
        cout << -1 << "\n";
        return 0;
    }
    cout << check[m][n] << "\n";
    return 0;
}
