//
//  main.cpp
//  N-Queen
//
//  Created by 이준영 on 09/04/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <utility>
#include <queue>
#include <functional>
#include <vector>
#include <algorithm>

using namespace std;

char arr[505][505];
int check[505][505][3];
int dx[] = {0,1,0,-1};
int dy[] = {1,0,-1,0};
int N,t,r,c;
int print = 2000000000;

void go(){
    priority_queue<pair<pair<int,int>,pair<int,int> >, vector<pair<pair<int,int>,pair<int,int> > >, greater<pair<pair<int,int>,pair<int,int> > > > pq;
    pq.push({{0,0},{1,1}});
    pq.push({{t,1},{1,1}});
    while(!pq.empty()){
        int now_Y = pq.top().second.first;
        int now_X = pq.top().second.second;
        int now_T = pq.top().first.first;
        int now_M = pq.top().first.second;
        pq.pop();
        if((check[now_Y][now_X][now_M] >= now_T and check[now_Y][now_X][now_M] != 2000000000) or now_T > print){
            continue;
        }
        if(now_Y == r and now_X == c){
            print = min(print,now_T);
        }
        check[now_Y][now_X][now_M] = now_T;
        if(now_M == 1){
            pq.push({{now_T,0},{now_Y,now_X}});
            pair<int,int> move = {-1,-1};
            for(int i = 1; i < now_X; i++){
                if(arr[now_Y][i] == '#'){
                    move = {now_Y,i};
                }
            }
            if(move.first != -1 and check[move.first][move.second][1] >= now_T+1){
                pq.push({{now_T+1,1},{move.first,move.second}});
            }
            move = {-1,-1};
            for(int i = now_X+1; i <= N; i++){
                if(arr[now_Y][i] == '#'){
                    move = {now_Y,i};
                    break;
                }
            }
            if(move.first != -1 and check[move.first][move.second][1] >= now_T+1){
                pq.push({{now_T+1,1},{move.first,move.second}});
            }
            move = {-1,-1};
            for(int i = 1; i < now_Y; i++){
                if(arr[i][now_X] == '#'){
                    move = {i,now_X};
                }
            }
            if(move.first != -1 and check[move.first][move.second][1] >= now_T+1){
                pq.push({{now_T+1,1},{move.first,move.second}});
            }
            move = {-1,-1};
            for(int i = now_Y+1; i <= N; i++){
                if(arr[i][now_X] == '#'){
                    move = {i,now_X};
                    break;
                }
            }
            if(move.first != -1 and check[move.first][move.second][1] >= now_T+1){
                pq.push({{now_T+1,1},{move.first,move.second}});
            }
        }
        if(now_M == 0){
            pq.push({{now_T+t,1},{now_Y,now_X}});
            for(int i = 0; i < 4; i++){
                int move_Y = now_Y + dy[i];
                int move_X = now_X + dx[i];
                if(move_Y > 0 and move_Y <= N and move_X > 0 and move_X <= N and check[move_Y][move_X][0] >= now_T+1){
                    pq.push({{now_T+1,0},{move_Y,move_X}});
                }
            }
        }
    }
}
int main() {
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N >> t >> r >> c;
    for(int i = 1; i <= N; i++){
        for(int j = 1; j <= N; j++){
            cin >> arr[i][j];
        }
    }
    for(int i = 1; i <= N; i++){
        for(int j = 1; j <= N; j++){
            check[i][j][0] = 2000000000;
            check[i][j][1] = 2000000000;
        }
    }
    go();
    cout << print << "\n";
    return 0;
}

