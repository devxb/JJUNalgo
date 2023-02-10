#include <iostream>
#include <algorithm>
#include <string>
#include <vector>
#include <deque>
#include <cmath>
#include <queue>
#include <unordered_map>

using namespace std;

int N;
int ord[48];
int P[48];
int S[48];
int ans;

bool valid(){
    for(int i = 0; i < N; i++){
        if(P[ord[i]] != i % 3) return false;
    }
    return true;
}

void move(){
    int temp[48];
    for(int i = 0; i < N; i++) temp[S[i]] = ord[i];
    for(int i = 0; i < N; i++) ord[i] = temp[i];
}

void solve(int cycle){
    if(valid()){
        ans = cycle;
        return;
    }
    if(cycle > N*2550) {
        ans = -1;
        return;
    }
    move();
    solve(cycle+1);
}


void input(){
    cin >> N;
    for(int i = 0; i < N; i++) ord[i] = i;
    for(int i = 0; i < N; i++) cin >> P[i];
    for(int i = 0; i < N; i++) cin >> S[i];
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    input();
    solve(0);
    cout << ans << "\n";
}
