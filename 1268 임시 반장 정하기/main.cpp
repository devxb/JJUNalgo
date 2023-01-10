#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int N;
int arr[1000][1000];
bool visited[1000][1000];

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    for(int i = 0; i < N; i++){
        for(int j = 0; j < 5; j++) cin >> arr[i][j];
    }
    for(int i = 0; i < 5; i++){
        for(int j = 0; j < N; j++){
            for(int k = j+1; k < N; k++){
                if(arr[j][i] == arr[k][i]){
                    visited[j][k] = true;
                    visited[k][j] = true;
                }
            }
        }
    }
    int resultIdx = 0, resultCnt = -1;
    for(int i = 0; i < N; i++){
        int cnt = 0;
        for(int j = 0; j < N; j++){
            if(visited[i][j] == true) cnt++;
        }
        if(cnt > resultCnt) {
            resultIdx = i+1;
            resultCnt = cnt;
        }
    }
    cout << resultIdx << "\n";
}
