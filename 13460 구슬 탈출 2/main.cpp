//
//  main.cpp
//  13460 구슬 탈출 2
//
//  Created by 이준영 on 2021/01/03.
//

#include <iostream>
#include <string>
#include <utility>
#include <queue>
#include <algorithm>

using namespace std;
int N, M, check[15][15][15][15];
int dx[] = {-1,0,1,0};
int dy[] = {0,-1,0,1};
char arr[15][15];
bool ext = false;
queue<pair<int,pair<pair<int,int>,pair<int,int> > > > q;

pair<pair<int,int>,pair<int,int> > nextPos(pair<int,int> R, pair<int,int> B, pair<int,int> dir, int cnt){
    pair<pair<int,int>,pair<int,int> > temp = {R,B};
    bool rmc = true, bmc = true, rih = false, bih = false;
    while(true){
        int ry = temp.first.first;
        int rx = temp.first.second;
        int by = temp.second.first;
        int bx = temp.second.second;
        if((rmc == false and bmc == false) or (ry == by and rx == bx and (rih == false and bih == false))){
            break;
        }
        R = {ry,rx};
        B = {by,bx};
        if(arr[ry][rx] == 'O'){
            rih = true;
        }
        if(arr[by][bx] == 'O'){
            bih = true;
        }
        if(rih == false and arr[ry+dir.first][rx+dir.second] != '#'){
            R = {ry,rx};
            temp.first = {ry+dir.first,rx+dir.second};
        }
        else{
            rmc = false;
        }
        if(bih == false and arr[by+dir.first][bx+dir.second] != '#'){
            B = {by,bx};
            temp.second = {by+dir.first,bx+dir.second};
        }
        else{
            bmc = false;
        }
    }
    if(rih == true and bih == false){
        cout << cnt << "\n";
        ext = true;
    }
    return {R, B};
}

void BFS(){
    while(!q.empty()){
        int nowCnt = q.front().first;
        pair<int, int> nowR = q.front().second.first;
        pair<int, int> nowB = q.front().second.second;
        q.pop();
        if(check[nowR.first][nowR.second][nowB.first][nowB.second] < nowCnt or nowCnt >= 10){
            continue;
        }
        if(ext == true){
            return;
        }
        for(int i = 0; i < 4; i++){
            if(ext == true){
                continue;
            }
            pair<pair<int,int>,pair<int,int> > next = nextPos(nowR, nowB, {dy[i], dx[i]}, nowCnt+1);
            pair<int,int> nextR = next.first;
            pair<int,int> nextB = next.second;
            int nextCnt = nowCnt+1;
            if(check[nextR.first][nextR.second][nextB.first][nextB.second] <= nextCnt){
                continue;
            }
            check[nextR.first][nextR.second][nextB.first][nextB.second] = nextCnt;
            q.push({nextCnt,{next}});
        }
    }
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> M;
    for(int i = 0; i <= N+1; i++){
        for(int j = 0; j <= M+1; j++){
            for(int l = 0; l <= N+1; l++){
                for(int k = 0; k <= M+1; k++){
                    check[i][j][l][k] = 100000;
                }
            }
        }
    }
    pair<int,int> R,B;
    for(int i = 1; i <= N; i++){
        string str;
        cin >> str;
        for(int j = 0; j < str.size(); j++){
            arr[i][j+1] = str[j];
            if(arr[i][j+1] == 'R'){
                arr[i][j+1] = '.';
                R = {i,j+1};
            }
            if(arr[i][j+1] == 'B'){
                arr[i][j+1] = '.';
                B = {i,j+1};
            }
        }
    }
    q.push({0,{R,B}});
    check[R.first][R.second][B.first][B.second] = 0;
    BFS();
    if(ext == false){
        cout << -1 << "\n";
    }
    return 0;
}
