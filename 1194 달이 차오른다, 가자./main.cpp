//
//  main.cpp
//  달이 차오른다, 가자
//
//  Created by 이준영 on 24/05/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <deque>
#include <algorithm>
#include <utility>
#include <string>
#include <bitset>
using namespace std;
int N,M;
pair<int,int> start;
char arr[55][55];
int check[55][55][1 << 10];
int print = -1;
char key_arr[] = {'x','a','b','c','d','e','f'};
char door_arr[] = {'X','A','B','C','D','E','F'};
int dx[] = {-1,0,1,0};
int dy[] = {0,-1,0,1};

void bfs(pair<int,int> idx){
    deque<pair<pair<int,int>,pair<int,int>>> deq;
    deq.push_back({{idx.first,idx.second},{1 << 10,0}});
    while(!deq.empty()){
        int now_y = deq.front().first.first;
        int now_x = deq.front().first.second;
        int now_key = deq.front().second.first;
        int now_num = deq.front().second.second;
        deq.pop_front();
        if(arr[now_y][now_x] == '1'){
            if(print == -1){
                print = now_num;
            }
            else{
                print = min(print,now_num);
            }
            continue;
        }
        for(int i = 0; i < 4; i++){
            int next_y = dy[i] + now_y;
            int next_x = dx[i] + now_x;
            if(next_y >= 1 and next_y <= N and next_x >= 1 and next_x <= M and check[next_y][next_x][now_key] == 0 and arr[next_y][next_x] != '#'){
                if(arr[next_y][next_x] == '.' or arr[next_y][next_x] == '1' or arr[next_y][next_x] == '0'){
                    check[next_y][next_x][now_key] = 1;
                    deq.push_back({{next_y,next_x},{now_key,now_num+1}});
                }
                if((int)arr[next_y][next_x] >= 97 and (int)arr[next_y][next_x] <= 102){
                    for(int i = 97; i <= 102; i++){
                        if(key_arr[i-96] == arr[next_y][next_x]){
                            int next_key = now_key | (1 << (i-96));
                            check[next_y][next_x][next_key] = 1;
                            check[next_y][next_x][now_key] = 1;
                            deq.push_back({{next_y,next_x},{next_key,now_num+1}});
                            }
                    }
                }
                else if((int)arr[next_y][next_x] >= 65 and (int)arr[next_y][next_x] <= 70){
                    for(int i = 65; i <= 70; i++){
                        if(door_arr[i-64] == arr[next_y][next_x]){
                            if(now_key & 1 << (i - 64)){
                                check[next_y][next_x][now_key] = 1;
                                deq.push_back({{next_y,next_x},{now_key,now_num+1}});
                            }
                        }
                    }
                }
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
            if(arr[i][j] == '0'){
                start = {i,j};
            }
        }
    }
    bfs(start);
    cout << print << "\n";
    return 0;
}
