//
//  main.cpp
//  1799 비숍
//
//  Created by 이준영 on 2021/01/04.
//

#include <iostream>
#include <algorithm>
#include <vector>
#include <utility>

using namespace std;

int N, cnt, chess[15][15], check[15][15], check2[15][15], num, dy[] = {-1,1,-1,1}, dx[] = {-1,1,1,-1};

vector<vector<pair<int,int> > > vec;

bool possible(int y, int x){
    for(int i = 0; i < 4; i++){
        int tempy = y;
        int tempx = x;
        while(true){
            if(tempy < 1 or tempy > N or tempx < 1 or tempx > N){
                break;
            }
            if(check[tempy][tempx] == -1){
                return false;
            }
            tempy += dy[i];
            tempx += dx[i];
        }
    }
    return true;
}

void go(int cnt, int start, int color){
    num = max(num, cnt);
    for(int i = start; i < vec[color].size(); i++){
        int ny = vec[color][i].first;
        int nx = vec[color][i].second;
        if(check[ny][nx] == -1 or possible(ny,nx) == false or chess[ny][nx] == 0){
            continue;
        }
        check[ny][nx] = -1;
        go(cnt+1, i+1, color);
        check[ny][nx] = 0;
    }
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    vec.resize(5);
    for(int i = 1; i <= N; i++){
        for(int j = 1; j <= N; j++){
            cin >> chess[i][j];
            if(chess[i][j] == 1){
                if((i + j) % 2 == 0){
                    vec[1].push_back({i,j});
                    continue;
                }
                vec[2].push_back({i,j});
            }
        }
    }
    for(int i = 1; i <= 2; i++){
        num = 0;
        go(0, 0, i);
        cnt += num;
    }
    cout << cnt << "\n";
    return 0;
}
