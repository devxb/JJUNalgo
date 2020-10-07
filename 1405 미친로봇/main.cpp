//
//  main.cpp
//  1405 미친 로봇
//
//  Created by 이준영 on 2020/10/07.
//

#include <iostream>
#include <utility>
#include <vector>
#include <cmath>

using namespace std;

int N, num;
int dy[] = {0, 0, 1, -1};
int dx[] = {1, -1, 0, 0};
int arr[50][50], d[5];
double perc;

void DFS(int Y, int X, int cnt, double per){
    if(cnt == N){
        perc = perc + per;
        return;
    }
    for(int i = 0; i < 4; i++){
        int next_Y = Y + dy[i];
        int next_X = X + dx[i];
        if(arr[next_Y][next_X] == 1){
            continue;
        }
        arr[Y][X] = 1;
        DFS(next_Y, next_X, cnt+1, (per * ((double)d[i] / (double)100)));
        arr[Y][X] = 0;
    }
    return;
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    scanf("%d",&N);
    for(int i = 0; i < 4; i++){
        scanf("%d",&d[i]);
    }
    DFS(14, 14, 0, 1);
    printf("%.10f\n",perc);
    return 0;
}
