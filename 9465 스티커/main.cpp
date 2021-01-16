//
//  main.cpp
//  9465 스티커
//
//  Created by 이준영 on 2021/01/16.
//

#include <iostream>
#include <algorithm>

using namespace std;
int T;
int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> T;
    for(int i = 1; i <= T; i++){
        int n = 0;
        cin >> n;
        int arr[5][n+5];
        arr[1][0] = 0;
        arr[2][0] = 0;
        for(int j = 1; j <= 2; j++){
            for(int l = 1; l <= n; l++){
                cin >> arr[j][l];
            }
        }
        for(int j = 1; j <= n; j++){
            if(j < 3){
                arr[1][j] += arr[2][j-1];
                arr[2][j] += arr[1][j-1];
                continue;
            }
            arr[1][j] += max(arr[2][j-1], arr[2][j-2]);
            arr[2][j] += max(arr[1][j-1], arr[1][j-2]);
        }
        cout << max(arr[1][n], arr[2][n]) << "\n";
    }
    return 0;
}
