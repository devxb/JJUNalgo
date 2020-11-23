//
//  main.cpp
//  5427 불
//
//  Created by 이준영 on 2020/11/23.
//

#include <iostream>
#include <string>
#include <deque>
#include <utility>
#include <algorithm>

using namespace std;

int T, w, h, print;
string arr[1005][1005];
int map[1005][1005], check[1005][1005];
deque<pair<int,pair<int,int> > > Fire, deq;
int dy[] = {-1, 0, 1, 0};
int dx[] = {0, -1, 0, 1};

void set(){
    for(int i = 1; i <= 1000; i++){
        for(int j = 1; j <= 1000; j++){
            arr[i][j] = "";
            map[i][j] = -1;
            check[i][j] = -1;
        }
    }
}

void BFS(){
    while(!Fire.empty()){
        int nowY = Fire.front().second.first;
        int nowX = Fire.front().second.second;
        int nowC = Fire.front().first;
        Fire.pop_front();
        if(map[nowY][nowX] > -1 and map[nowY][nowX] <= nowC){
            continue;
        }
        map[nowY][nowX] = nowC;
        for(int i = 0; i < 4; i++){
            int nextY = nowY + dy[i];
            int nextX = nowX + dx[i];
            int nextC = nowC + 1;
            if(nextY > h or nextY < 1 or nextX > w or nextX < 1 or arr[nextY][nextX] == "#"){
                continue;
            }
            Fire.push_back({nextC,{nextY,nextX}});
        }
    }
    while(!deq.empty()){
        int nowY = deq.front().second.first;
        int nowX = deq.front().second.second;
        int nowC = deq.front().first;
        deq.pop_front();
        if((map[nowY][nowX] <= nowC and map[nowY][nowX] > -1) or (check[nowY][nowX] > -1 and check[nowY][nowX] <= nowC)){
            continue;
        }
        check[nowY][nowX] = nowC;
        for(int i = 0; i < 4; i++){
            int nextY = nowY + dy[i];
            int nextX = nowX + dx[i];
            int nextC = nowC + 1;
            if(nextY > h or nextY < 1 or nextX > w or nextX < 1){
                print = min(print,nextC);
                continue;
            }
            if(arr[nextY][nextX] == "#"){
                continue;
            }
            deq.push_back({nextC,{nextY,nextX}});
        }
    }
}

int main(int argc, const char * argv[]) {
    // insert code here...
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> T;
    for(int i = 1; i <= T; i++){
        set();
        cin >> w >> h;
        print = 1000000000;
        for(int j = 1; j <= h; j++){
            string str;
            cin >> str;
            for(int l = 1; l <= w; l++){
                arr[j][l] = str[l-1];
                if(arr[j][l] == "*"){
                    Fire.push_back({0,{j,l}});
                }
                if(arr[j][l] == "@"){
                    deq.push_back({0,{j,l}});
                }
            }
        }
        BFS();
        if(print == 1000000000){
            cout << "IMPOSSIBLE" << "\n";
        }
        else{
            cout << print << "\n";
        }
    }
    return 0;
}

