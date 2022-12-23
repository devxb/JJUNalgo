#include <iostream>
#include <string>
#include <algorithm>
#include <cmath>

using namespace std;

int N, K;
pair<pair<int, int>, pair<int, int>> arr[1000];

bool isSameRank(pair<pair<int, int>, pair<int, int>> teamA,
                pair<pair<int, int>, pair<int, int>> teamB){
    return teamA.first.first == teamB.first.first
        && teamA.first.second == teamB.first.second
        && teamA.second.first == teamB.second.first;
}

int getRank(){
    int currentRank = 1;
    if(K == arr[0].second.second) return 1;
    for(int i = 1; i < N; i++){
        if(arr[i].second.second == K){
            if(isSameRank(arr[i], arr[i-1])) return currentRank;
            return i+1;
        }
        if(isSameRank(arr[i], arr[i-1])) continue;
        currentRank = i+1;
    }
    return currentRank;
}

void input(){
    cin >> N >> K;
    for(int i = 0; i < N; i++){
        int team, gold, silver, bronze;
        cin >> team >> gold >> silver >> bronze;
        arr[i] = {{gold, silver}, {bronze, team}};
    }
    sort(arr, arr+N, greater<>());
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    input();
    cout << getRank() << "\n";
}
