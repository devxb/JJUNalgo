//
//  main.cpp
//  15665 N과 M (11)
//
//  Created by 이준영 on 2021/01/02.
//
#include <iostream>
#include <deque>
#include <vector>
#include <algorithm>

using namespace std;

int N, M, checkBef[10005][10];

vector<int> vec;
deque<int> deq;

void go(int cnt, int startNum){
    if(cnt >= M){
        for(int i = 0; i < deq.size(); i++){
            cout << deq[i] << " ";
        }
        cout << "\n";
        return;
    }
    for(int i = 0; i < vec.size(); i++){
        if(checkBef[startNum][cnt+1] == vec[i]){
            continue;
        }
        deq.push_back(vec[i]);
        checkBef[startNum][cnt+1] = vec[i];
        go(cnt+1, startNum);
        checkBef[startNum][cnt+2] = 0;
        deq.pop_back();
    }
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> M;
    for(int i = 1; i <= N; i++){
        int num;
        cin >> num;
        vec.push_back(num);
    }
    sort(vec.begin(), vec.end());
    for(int i = 0; i < vec.size(); i++){
        if(checkBef[vec[i]][1] == vec[i]){
            continue;
        }
        checkBef[vec[i]][1] = vec[i];
        deq.push_back(vec[i]);
        go(1, vec[i]);
        checkBef[vec[i]][2] = 0;
        deq.pop_back();
    }
    return 0;
}
