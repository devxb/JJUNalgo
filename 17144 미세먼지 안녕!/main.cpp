//
// xb205
// 2021.01.25
//

#include <iostream>
#include <algorithm>
#include <utility>
#include <queue>
#include <functional>
using namespace std;
int R, C, T, dust[55][55], upcl, downcl;
int dy[] = {-1,0,1,0}, dx[] = {0,-1,0,1};
queue<pair<int,pair<int,int> > > diffusion;

void diff(){
    for(int i = 1; i <= R; i++){
        for(int j = 1; j <= C; j++){
            if(dust[i][j] > 0){
                queue<pair<int,int> > tempQ;
                for(int l = 0; l < 4; l++){
                    int ny = i + dy[l];
                    int nx = j + dx[l];
                    if(ny > 0 and ny <= R and nx > 0 and nx <= C and dust[ny][nx] != -1){
                        tempQ.push({ny,nx});
                    }
                }
                int numberDir = tempQ.size();
                while(!tempQ.empty()){
                    diffusion.push({dust[i][j]/5,{tempQ.front().first, tempQ.front().second}});
                    tempQ.pop();
                }
                dust[i][j] = dust[i][j] - ((dust[i][j]/5)*numberDir);
            }
        }
    }
    while(!diffusion.empty()){
        int diffnum = diffusion.front().first;
        int y = diffusion.front().second.first;
        int x = diffusion.front().second.second;
        diffusion.pop();
        dust[y][x] += diffnum;
    }
    return;
}

void workingUpCleaner(){
    queue<pair<int,pair<int,int> > > up;
    for(int i = 2; i < C; i++){
        up.push({dust[upcl][i],{upcl,i+1}});
        dust[upcl][i] = 0;
    }
    for(int i = upcl; i > 1; i--){
        up.push({dust[i][C],{i-1,C}});
        dust[i][C] = 0;
    }
    for(int i = C; i > 1; i--){
        up.push({dust[1][i],{1,i-1}});
        dust[1][i] = 0;
    }
    for(int i = 1; i < upcl; i++){
        up.push({dust[i][1],{i+1,1}});
        dust[i][1] = 0; 
    }
    while(!up.empty()){
        int num = up.front().first;
        int y = up.front().second.first;
        int x = up.front().second.second;
        up.pop();
        if(x == 1 and upcl == y){
            continue;
        }
        dust[y][x] = num;
    }
    return;
}

void workingDownCleaner(){
    queue<pair<int,pair<int,int> > > down;
    for(int i = 2; i < C; i++){
        down.push({dust[downcl][i],{downcl,i+1}});
        dust[downcl][i] = 0;
    }
    for(int i = downcl; i < R; i++){
        down.push({dust[i][C],{i+1,C}});
        dust[i][C] = 0;
    }
    for(int i = C; i > 1; i--){
        down.push({dust[R][i],{R,i-1}});
        dust[R][i] = 0;
    }
    for(int i = R; i > downcl; i--){
        down.push({dust[i][1],{i-1,1}});
        dust[i][1] = 0;
    }
    while(!down.empty()){
        int num = down.front().first;
        int y = down.front().second.first;
        int x = down.front().second.second;
        down.pop();
        if(x == 1 and y == downcl){
            continue;
        }
        dust[y][x] = num; 
    }
    return;
}

void work(int t){
    diff();
    workingUpCleaner();
    workingDownCleaner();
    if(t < T){
        work(t+1);
    }
    return;
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> R >> C >> T;
    for(int i = 1; i <= R; i++){
        for(int j = 1; j <= C; j++){
            cin >> dust[i][j];
            if(dust[i][j] == -1){
                if(upcl == 0){
                    upcl = i;
                }
                else{
                    downcl = i;
                }
            }
        }
    }
    work(1);
    int num = 0;
    for(int i = 1; i <= R; i++){
        for(int j = 1; j <= C; j++){
            if(dust[i][j] > 0){
                num += dust[i][j];
            }
        }
    }
    cout << num << "\n";
    return 0;
}
