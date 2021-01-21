// 
// xb205
// 2021.1.21
//

#include <iostream>
#include <queue>
#include <utility>
#include <algorithm>

using namespace std;
int N, M, arr[10][10], num;
int dy[] = {0, -1, 0, 1}, dx[] = {-1, 0, 1, 0};
queue<pair<int,int> > q;

int BFS(){
    int answer = 0;
    queue<pair<int,int> > temp = q;
    int arr2[10][10];
    for(int i = 1; i <= N; i++){
        for(int j = 1; j <= M; j++){
            arr2[i][j] = arr[i][j];
        }
    }
    while(!temp.empty()){
        int y = temp.front().first;
        int x = temp.front().second;;
        temp.pop();
        if(arr2[y][x] == 1){
            continue;
        }
        arr2[y][x] = 2;
        for(int i = 0; i < 4; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(arr2[ny][nx] == 0 and ny <= N and nx <= M and ny > 0 and nx > 0){
                temp.push({ny,nx});
            }
        }
    }
    for(int i = 1; i <= N; i++){
        for(int j = 1; j <= M; j++){
            if(arr2[i][j] == 0){
                answer++;
            }
        }  
    }
    return answer;
}

void go(int cnt){
    if(cnt > 3){
        num = max(num, BFS());
        return;
    }
    for(int i = 1; i <= N; i++){
        for(int j = 1; j <= M; j++){
            if(arr[i][j] == 0){
                arr[i][j] = 1;
                go(cnt+1);
                arr[i][j] = 0;
            }
        }
    }
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> M;
    for(int i = 1; i <= N; i++){
        for(int j = 1; j <= M; j++){
            cin >> arr[i][j];
            if(arr[i][j] == 2){
                q.push({i,j});
            }
        }
    }
    go(1);
    cout << num << "\n";
    return 0;
}
