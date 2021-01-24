//
// xb205
// 2021.01.24
//

#include <iostream>
#include <queue>
#include <utility>
using namespace std;
int N, M, spend, arr[105][105], check[105][105], cnt, dy[]={-1,0,1,0}, dx[]={0,-1,0,1};
queue<pair<int,int> > q;

void airpush(int y, int x){
    for(int i = 0; i < 4; i++){
        int ny = y + dy[i];
        int nx = x + dx[i];
        if(check[ny][nx] == spend or ny < 1 or nx < 1 or ny > N or nx > M){
            continue;
        }
        if(arr[ny][nx] == 1){
            q.push({ny,nx});
            continue;
        }
        check[ny][nx] = spend;
        airpush(ny, nx);
    }
}

void meltcheese(){
    while(!q.empty()){
        int y = q.front().first;
        int x = q.front().second;
        q.pop();
        bool melt = false;
        for(int i = 0; i < 4; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(check[ny][nx] == spend){
                for(int j = 0; j < 4; j++){
                    if(i == j){
                        continue;
                    }
                    int nny = y + dy[j];
                    int nnx = x + dx[j];
                    if(check[nny][nnx] == spend){
                        arr[y][x] = 0;
                        melt = true;
                        break;
                    }
                }
            }
            if(melt == true){
                break;
            }
        }
    }
}

void gettime(int t){
    spend = t;
    check[1][1] = spend;
    airpush(1,1);
    if(q.empty()){
        cout << t-1 << "\n";
        return;
    }
    meltcheese();
    gettime(t+1);
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> M;
    for(int i = 1; i <= N; i++){
        for(int j = 1; j <= M; j++){
             cin >> arr[i][j];
        }
    }
    gettime(1);
    return 0;
}
