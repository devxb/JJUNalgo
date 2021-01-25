//
// xb205
// 2021.01.25
//

#include <iostream>

using namespace std;
int N, arr[20][20], dow[20][20], dia[20][20], lef[20][20];
int getdp(){
    lef[1][2] = 1;
    for(int i = 1; i <= N; i++){
        for(int j = 3; j <= N; j++){
            if(arr[i][j] != 1){
                lef[i][j] = dia[i][j-1] + lef[i][j-1];
                dow[i][j] = dia[i-1][j] + dow[i-1][j];
            }
            if(arr[i][j] != 1 and arr[i-1][j] != 1 and arr[i][j-1] != 1){
                dia[i][j] = dia[i-1][j-1] + lef[i-1][j-1] + dow[i-1][j-1];
            }
        }
    }
    return dow[N][N] + dia[N][N] + lef[N][N];
}
int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    for(int i = 1; i <= N; i++){
        for(int j = 1; j <= N; j++){
            cin >> arr[i][j];
        }
    }
    cout << getdp() << "\n";
    return 0;
}
