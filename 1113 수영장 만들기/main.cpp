//
//  main.cpp
//  1113
//
//  Created by 이준영 on 08/07/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <algorithm>
#include <string>
#include <deque>

using namespace std;

int N, M, max_num, fill_num, fill_cnt ,cnt, num, temp_num, ER;
int map[55][55];
pair<int,int> update[55][55];
int check[55][55];
int dy[] = {-1, 0, 1, 0};
int dx[] = {0, -1, 0, 1};
deque<pair<int,int> > deq;

void dfs(int Y, int X){
    if(Y == N or Y == 0 or X == M or X == 0 or ER == 1){
        while(!deq.empty()){
            check[deq.front().first][deq.front().second] = 0;
            deq.pop_front();
        }
        ER = 1;
        return;
    }
    deq.push_back({Y,X});
    check[Y][X] = 1;
    for(int i = 0; i < 4; i++){
        int next_Y = Y + dy[i];
        int next_X = X + dx[i];
        if(map[next_Y][next_X] == fill_cnt and check[next_Y][next_X] != 1 and next_Y > 0 and next_Y <= N and next_X > 0 and next_X <= M){
            dfs(next_Y, next_X);
        }
        else if(check[next_Y][next_X] != 1){
            fill_num = min(fill_num, map[next_Y][next_X]);
            if(fill_num - fill_cnt <= 0){
                ER = 1;
                while(!deq.empty()){
                    check[deq.front().first][deq.front().second] = 0;
                    deq.pop_front();
                }
                return;
            }
        }
        
    }
    return;
}

void cnt_dfs(int Y, int X, int C){
    check[Y][X] = 2;
    temp_num = temp_num + update[Y][X].first;
    for(int i = 0; i < 4; i++){
        int next_Y = Y + dy[i];
        int next_X = X + dx[i];
        if(update[next_Y][next_X].second == C and check[next_Y][next_X] != 2 and next_Y > 0 and next_Y <= N and next_X > 0 and next_X <= M){
            cnt_dfs(next_Y,next_X,C);
        }
    }
    return;
}

int main() {
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N >> M;
    for(int i = 1; i <= N; i++){
        string str;
        cin >> str;
        for(int j = 0; j < M; j++){
            map[i][j+1] = (int)str[j] - 48;
            max_num = max(max_num,map[i][j+1]);
        }
    }
    for(int i = 1; i < max_num; i++){
        fill_cnt = i;
        for(int j = 2; j < N; j++){
            for(int l = 2; l < M; l++){
                if(map[j][l] == fill_cnt){
                    fill_num = 10;
                    cnt++;
                    ER = 0;
                    dfs(j,l);
                    while(!deq.empty()){
                        int now_Y = deq.front().first;
                        int now_X = deq.front().second;
                        deq.pop_front();
                        check[now_Y][now_X] = 0;
                        update[now_Y][now_X].first = update[now_Y][now_X].first + fill_num - map[now_Y][now_X];
                        map[now_Y][now_X] = fill_num;
                        update[now_Y][now_X].second = cnt;
                    }
                }
            }
        }
    }
    for(int i = 1; i <= N; i++){
        for(int j = 1; j <= M; j++){
            if(check[i][j] != 2){
                temp_num = 0;
                cnt_dfs(i,j,update[i][j].second);
                num = num + temp_num;
            }
        }
    }
    cout << num << "\n";
    return 0;
}
