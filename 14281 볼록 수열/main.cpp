#include <iostream>
#include <algorithm>
#include <string>
#include <unordered_set>
#include <cmath>
#include <vector>

using namespace std;

int N;
long long A[50];
long long ans = 0;

bool makeConvex(){
    bool isConvex = true;
    for(int i = 1; i < N-1; i++){
        if(A[i-1] + A[i+1] < A[i]*2){
            long long diff = (A[i-1] + A[i+1]) / 2;
            ans += (A[i] - diff);
            A[i] = diff;
            isConvex = false;
        }
    }
    return isConvex;
}

void input(){
    cin >> N;
    for(int i = 0; i < N; i++) {
        cin >> A[i];
    }
}

int main() {
    ios::sync_with_stdio(false);
	cin.tie(NULL);
    cout.tie(NULL);
    input();
    while(true){
        if(makeConvex()){
            break;
        }
    }
    cout << ans << "\n";
    return 0;
}
