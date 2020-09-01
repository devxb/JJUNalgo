//
//  main.cpp
//  ALCUK
//
//  Created by 이준영 on 14/03/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
#include <deque>
#include <cmath>
#include <functional>
#include <string>
using namespace std;

int arr[505][505];
int N,M;
int move_x[] = {-1, 0, 1, 0};
int move_y[] = {0, -1, 0, 1};
pair<int,int> wolf;
pair<int,int> home;
deque<pair<int,int> > deq;

void make_dis(){
    while(!deq.empty()){
        int Y = deq.front().first;
        int X = deq.front().second;
        deq.pop_front();
        for(int i = 0; i < 4; i++){
            int dy = Y + move_y[i];
            int dx = X + move_x[i];
            if(dy <= 0 or dy > N or dx <= 0 or dx > M or arr[dy][dx] == 0 or arr[dy][dx] != -1){
                continue;
            }
            arr[dy][dx] = arr[Y][X] + 1;
            deq.push_back({dy,dx});
        }
    }
    return;
}

void search(){
    priority_queue<pair<pair<int,int>,pair<int,int> > > pq;
    pq.push({{arr[wolf.first][wolf.second],arr[wolf.first][wolf.second]},{wolf.first,wolf.second}});
    while(!pq.empty()){
        int Y = pq.top().second.first;
        int X = pq.top().second.second;
        int now_dis = pq.top().first.first;
        int now_num = pq.top().first.second;
        pq.pop();
        if(home.first == Y and home.second == X){
            cout << now_num << "\n";
            break;
        }
        if(arr[Y][X] == -1){
            continue;
        }
        arr[Y][X] = -1;
        for(int i = 0; i < 4; i++){
            int dy = Y + move_y[i];
            int dx = X + move_x[i];
            if(dy <= 0 or dy > N or dx <= 0 or dx > M or arr[dy][dx] == -1){
                continue;
            }
            pq.push({{arr[dy][dx],min(arr[dy][dx],now_num)},{dy,dx}});
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
        for(int j = 0; j < str.size(); j++){
            arr[i][j+1] = -1;
            if(str[j] == '+'){
                arr[i][j+1] = 0;
                deq.push_back({i,j+1});
            }
            if(str[j] == 'V'){
                wolf = {i,j+1};
            }
            if(str[j] == 'J'){
                home = {i,j+1};
            }
        }
    }
    make_dis();
    search();
    return 0;
}
