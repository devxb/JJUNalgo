//
//  main.cpp
//  2342 Dance Dance Revolution
//
//  Created by 이준영 on 2021/01/05.
//

#include <iostream>
#include <algorithm>
#include <queue>
#include <utility>

using namespace std;

int DDR[100005], check[6][6][100005], step[10][10];
int pr = 2000000000;
queue<pair<pair<int, int>,pair<int,int> > > q;

void BFS(){
    while(!q.empty()){
        int idx = q.front().first.first;
        int cnt = q.front().first.second;
        int lf = q.front().second.first;
        int rf = q.front().second.second;
        q.pop();
        if(DDR[idx+1] == 0){
            pr = min(pr,cnt);
        }
        if(check[lf][rf][idx] < cnt or pr <= cnt){
            continue;
        }
        int nf = DDR[idx+1];
        int ncnt = cnt + step[lf][nf];
        if(nf != rf and (check[nf][rf][idx+1] > ncnt or check[nf][rf][idx+1] == 0)){
            check[nf][rf][idx+1] = ncnt;
            q.push({{idx+1,ncnt},{nf,rf}});
        }
        ncnt = cnt + step[rf][nf];
        if(lf != nf and (check[lf][nf][idx+1] > ncnt or check[lf][nf][idx+1] == 0)){
            check[lf][nf][idx+1] = ncnt;
            q.push({{idx+1,ncnt},{lf,nf}});
        }
    }
}

void set(){
    for(int i = 0; i <= 4; i++){
        step[0][i] = 2;
    }
    step[0][0] = 1;
    for(int i = 1; i <= 4; i++){
        for(int j = 1; j <= 4; j++){
            if(i == j){
                step[i][j] = 1;
                continue;
            }
            step[i][j] = 3;
        }
    }
    step[1][3] = 4;
    step[3][1] = 4;
    step[2][4] = 4;
    step[4][2] = 4;
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    set();
    int idx = 1;
    cin >> DDR[idx];
    while(DDR[idx] != 0){
        cin >> DDR[idx+1];
        idx++;
    }
    q.push({{0,0},{0,0}});
    BFS();
    cout << pr << "\n";
    return 0;
}
