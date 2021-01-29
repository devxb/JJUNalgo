//
// xb205
// 2021.01.29
//
#include <iostream>
#include <queue>
#include <utility>
using namespace std;
int N, check[17][1 << 17], arr[17][17];

int getcost(){
    queue<pair<pair<int,int>,pair<int,int> > > q;
    q.push({{1,0},{0,0}});
    int cost = 2000000000;
    while(!q.empty()){
        int idx = q.front().first.first;
        int num = q.front().first.second;
        int bit = q.front().second.first;
        int cnt = q.front().second.second;
        q.pop();
        if(check[idx][bit] > 0 and check[idx][bit] < num or num >= cost) continue;
        if(cnt == N) cost=num;
        for(int i = 1; i <= N; i++){
            int _bit = bit | (1 << (i-1));
            int _num = check[idx][bit] + arr[idx][i];
            if((cnt < N-1 and i == 1) or arr[idx][i] == 0 or bit & (1 << (i-1)) or (check[i][_bit] > 0 and check[i][_bit] <= _num)) continue;
            check[i][_bit] = _num;
            q.push({{i,_num},{_bit,cnt+1}});
        }
    }
    return cost;
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
    cout << getcost() << "\n";
    return 0;
}
