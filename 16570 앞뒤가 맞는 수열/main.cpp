#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int N;
int arr[1000000];
int pi[1000000];

void initPi(){
    int p = 0;
    for(int i = 1; i < N; i++){
        while(arr[i] != arr[p] && p > 0) p = pi[p-1];
        if(arr[p] == arr[i]) pi[i] = ++p;
    }
}

void reverseArr(){
    int temp[1000000];
    for(int i = 0; i < N; i++) temp[i] = arr[N-i-1];
    for(int i = 0; i < N; i++) arr[i] = temp[i];
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    for(int i = 0; i < N; i++) cin >> arr[i];
    reverseArr();
    initPi();
    int maxPi = 0, cnt = 0;
    for(int i = 0; i < N; i++){
        if(maxPi < pi[i]){
            maxPi = pi[i];
            cnt = 1;
            continue;
        }
        if(pi[i] > 0 && maxPi == pi[i]) cnt++;
    }
    if(maxPi == 0) cout << -1 << "\n";
    else cout << maxPi << " " << cnt << "\n";
}
