//
//  main.cpp
//  16724 피리 부는 사나이
//
//  Created by 이준영 on 2021/01/06.
//

#include <iostream>
#include <string>
using namespace std;

int N, M, cnt;
int arr[1005][1005], check[1005][1005], safe[1005][1005], dy[] = {-1,1,0,0}, dx[] = {0,0,-1,1};
char dir[] = {'U', 'D', 'L', 'R'};

void DFS(int y, int x, int cnt){
    if(y + dy[arr[y][x]] > N or y + dy[arr[y][x]] < 1 or x + dx[arr[y][x]] > M or x + dx[arr[y][x]] < 1 or check[y][x] == cnt){
        safe[y][x] = 1;
        return;
    }
    if(check[y][x] != cnt and check[y][x] != 0){
        return;
    }
    check[y][x] = cnt;
    DFS(y + dy[arr[y][x]], x + dx[arr[y][x]], cnt);
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> M;
    for(int i = 1; i <= N; i++){
        string str;
        cin >> str;
        for(int j = 0; j < str.size(); j++){
            for(int l = 0; l < 4; l++){
                if(dir[l] == str[j]){
                    arr[i][j+1] = l;
                    break;
                }
            }
        }
    }
    int c = 1;
    for(int i = 1; i <= N; i++){
        for(int j = 1; j <= M; j++){
            if(check[i][j] == 0){
                DFS(i, j, c);
                c++;
            }
        }
    }
    for(int i = 1; i <= N; i++){
        for(int j = 1; j <= M; j++){
            if(safe[i][j] == 1){
                cnt++;
            }
        }
    }
    cout << cnt << "\n";
    return 0;
}
