//
//  main.cpp
//  2056 작업
//
//  Created by 이준영 on 2021/01/02.
//

#include <iostream>
#include <algorithm>
#include <vector>
#include <deque>
using namespace std;

int N, arr[10005], check[10005], pri;
vector<vector<int> > pre;

int work(int idx){
    if(check[idx] != 0){
        return check[idx];
    }
    int num = 0;
    for(int i = 0; i < pre[idx].size(); i++){
        if(check[pre[idx][i]] == 0){
            num = max(num, work(pre[idx][i]));
            continue;
        }
        num = max(num,check[pre[idx][i]]);
    }
    return check[idx] = num + arr[idx];
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    pre.resize(N+5);
    for(int i = 1; i <= N; i++){
        cin >> arr[i];
        int M = 0;
        cin >> M;
        for(int j = 0; j < M; j++){
            int num;
            cin >> num;
            pre[i].push_back(num);
        }
    }
    for(int i = 1; i <= N; i++){
        if(check[i] == 0){
            work(i);
        }
        pri = max(pri,check[i]);
    }
    cout << pri << "\n";
    return 0;
}
