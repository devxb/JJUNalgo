//
//  main.cpp
//  2206 벽 부수고 이동하기
//
//  Created by 이준영 on 01/06/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <algorithm>
#include <queue>
#include <string>

using namespace std;
int N,M;
char arr[1005][1005];
int check[1005][1005][3];
//check[y][x][0] = 벽을 안부수고 이동했을때
//check[y][x][1] = 벽을 부수고 이동했을때
int dy[] = {-1,0,1,0};
int dx[] = {0,-1,0,1};
int min_num = 2000*2000;

void BFS(int Y,int X){
    queue<pair<pair<int,int>,pair<int,int> > > que;
    que.push({{1,0},{Y,X}});
    while(!que.empty()){
        int now_num = que.front().first.first;
        int now_check = que.front().first.second;
        int now_Y = que.front().second.first;
        int now_X = que.front().second.second;
        que.pop();
        if(now_Y == N and now_X == M){
            min_num = min(now_num,min_num);
            continue;
        }
        if(now_num >= min_num){
            continue;
        }
        for(int i = 0; i < 4; i++){
            int next_Y = now_Y + dy[i];
            int next_X = now_X + dx[i];
            if(next_Y > 0 and next_Y <= N and next_X > 0 and next_X <= M){
                if(arr[next_Y][next_X] == '1' and now_check == 0 and check[next_Y][next_X][1] == 0){
                    check[next_Y][next_X][1] = 1;
                    que.push({{now_num+1,1},{next_Y,next_X}});
                } // 다음위치가 벽인데, 지금 벽을 부술 기회가 있음
                if(arr[next_Y][next_X] == '0' and check[next_Y][next_X][now_check] == 0){
                    check[next_Y][next_X][now_check] = 1;
                    que.push({{now_num+1,now_check},{next_Y,next_X}});
                } // 다음위치가 벽이 아님
            }
        }
    }
}

int main() {
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N >> M;
    for(int i = 1; i <= N; i++){
        string str;
        cin >> str;
        for(int j = 1; j <= M; j++){
            arr[i][j] = str[j-1];
        }
    }
    BFS(1,1);
    if(min_num == 2000*2000){
        cout << -1 << "\n";
    }
    else{
        cout << min_num << "\n";
    }
    return 0;
}
