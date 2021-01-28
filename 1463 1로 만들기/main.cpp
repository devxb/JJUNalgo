//
// xb205
// 2021.01.28
//

#include <iostream>
#include <queue>
using namespace std;
int N, check[1000005];

int getcnt(int N){
    int cnt = 0;
    queue<pair<int,int> > q;
    q.push({0,N});
    check[N] = 0;
    while(!q.empty()){
        int _cnt = q.front().first;
        int _num = q.front().second;
        q.pop();
        if(check[_num] > _cnt){
            continue;
        }
        check[_num] = _cnt;
        if(_num == 1){
            cnt = _cnt;
            break;
        }
        if(_num%3 == 0 and (check[_num/3] > _cnt+1 or check[_num/3] == 0)){
            q.push({_cnt+1, _num/3});
        }
        if(_num%2 == 0 and (check[_num/2] > _cnt+1 or check[_num/2] == 0)){
            q.push({_cnt+1, _num/2});
        }
        if(check[_num-1] > _cnt or check[_num-1] == 0){
            q.push({_cnt+1, _num-1});
        }
    }
    return cnt;
}

int main(){
    ios::sync_with_stdio();
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    cout << getcnt(N) << "\n";
    return 0;
}
