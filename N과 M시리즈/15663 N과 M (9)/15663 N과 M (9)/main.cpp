//
//  main.cpp
//  15663 N과 M (9)
//
//  Created by 이준영 on 2021/01/02.
//
#include <iostream>
#include <deque>
#include <vector>
#include <algorithm>

using namespace std;

int N, M, check[10], checkBef[10005][10];

vector<int> vec;
deque<int> deq;

void go(int cnt, int startNum, int idx){
    if(cnt >= M){
        for(int i = 0; i < deq.size(); i++){
            cout << deq[i] << " ";
        }
        cout << "\n";
        return;
    }
//    cout << cnt << " : ";
//    for(int i = 0; i < deq.size(); i++){
//        cout << deq[i] << " ";
//    }
//    cout << "\n";
    for(int i = idx; i < vec.size(); i++){
        if(check[i] == 1 or checkBef[startNum][cnt+1] == vec[i]){
            continue;
        }
        deq.push_back(vec[i]);
        check[i] = 1;
        checkBef[startNum][cnt+1] = vec[i];
        go(cnt+1, startNum, i);
        checkBef[startNum][cnt+2] = 0;
        check[i] = 0;
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
        if(check[i] == 1 or checkBef[vec[i]][1] == vec[i]){
            continue;
        }
        check[i] = 1;
        checkBef[vec[i]][1] = vec[i];
        deq.push_back(vec[i]);
        go(1, vec[i], i);
        checkBef[vec[i]][2] = 0;
        check[i] = 0;
        deq.pop_back();
    }
    return 0;
}
