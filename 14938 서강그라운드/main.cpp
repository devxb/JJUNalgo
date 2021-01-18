//
//  main.cpp
//  14938 서강그라운드
//
//  Created by 이준영 on 2021/01/18.
//

#include <iostream>
#include <vector>
#include <utility>
#include <queue>
#include <algorithm>

using namespace std;

int n, m, r, items[105], road[105][105], num = 0;

void set(int n, int m){
    for(int i = 1; i <= n+1; i++){
        for(int j = 1; j <= n+1; j++){
            if(i == j){
                road[i][j] = 0;
                continue;
            }
            road[i][j] = m+1;
        }
    }
}

void floyd(){
    for(int mid = 1; mid <= n; mid++){
        for(int from = 1; from <= n; from++){
            for(int to = 1; to <= n; to++){
                if(from == to){
                    continue;
                }
                road[from][to] = min(road[from][to], road[from][mid]+road[mid][to]);
            }
        }
    }
}

int getItems(){
    int num = 0;
    for(int i = 1; i <= n; i++){
        int temp = items[i];
        for(int j = 1; j <= n; j++){
            if(road[i][j] <= m and i != j){
                temp += items[j];
            }
        }
        num = max(num, temp);
    }
    return num;
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> n >> m >> r;
    for(int i = 1; i <= n; i++){
        cin >> items[i];
    }
    set(n, m);
    for(int i = 1; i <= r; i++){
        int from, to, length;
        cin >> from >> to >> length;
        road[from][to] = length;
        road[to][from] = length;
    }
    floyd();
    cout << getItems() << "\n";
    return 0;
}
