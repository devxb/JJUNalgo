//
//  main.cpp
//  11404 플로이드
//
//  Created by 이준영 on 2021/01/16.
//

#include <iostream>

using namespace std;
int n, m, arr[105][105], INF = 1000000000;

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> n >> m;
    for(int i = 1; i <= n; i++){
        for(int j = 1; j <= n; j++){
            arr[i][j] = INF;
            if(i==j){
                arr[i][j] = 0;
            }
        }
    }
    for(int i = 1; i <= m; i++){
        int from, to, cost;
        cin >> from >> to >> cost;
        arr[from][to] = min(arr[from][to],cost);
    }
    for(int mid = 1; mid <= n; mid++){
        for(int from = 1; from <= n; from++){
            for(int to = 1; to <= n; to++){
                if(arr[from][mid] + arr[mid][to] < arr[from][to]){
                    arr[from][to] = arr[from][mid] + arr[mid][to];
                }
            }
        }
    }
    for(int i = 1; i <= n; i++){
        for(int j = 1; j <= n; j++){
            if(arr[i][j] == INF){
                arr[i][j] = 0;
            }
            cout << arr[i][j] << " ";
        }
        cout << "\n";
    }
    return 0;
}
