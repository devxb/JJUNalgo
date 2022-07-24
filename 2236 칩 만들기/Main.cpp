#include <iostream>
#include <algorithm>
#include <string>

using namespace std;

int N, K; 

void inputArr(pair<int,int> arr[]){
    for(int i = 0; i < N; i++){
        int priority = 0;
        cin >> priority;
        arr[i] = {priority, i};
    }
    sort(arr, arr+N);
}

int main() {
    ios::sync_with_stdio(false);
	cin.tie(NULL);
    cout.tie(NULL);
    cin >> N >> K;
    pair<int, int> arr[N];
    inputArr(arr);
    for(int i = 0; i < K; i++) {
        if(i >= N) cout << 0 << "\n";
        else cout << arr[N-(i+1)].second+1 << "\n";
    }
    for(int i = 0; i < N; i++){
        for(int j = 0; j < N; j++){
            if(arr[j].second == i && j >= N-K) cout << arr[j].second+1 << "\n";
            else if(arr[j].second == i) cout << 0 << "\n";
        }
    }
    return 0;
}
