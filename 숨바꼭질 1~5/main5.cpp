//
//  main.cpp
//  17087 숨바꼭질 5
//
//  Created by 이준영 on 2020/12/27.
//

#include <iostream>
#include <queue>
#include <utility>
#include <vector>
#include <cmath>

using namespace std;
int N, K, dx[] = {1,-1}, bro[500005], pt = 1000000000;
pair<int,int> check[500005]; // odd, even
priority_queue<pair<int,int>, vector<pair<int,int> >, greater<pair<int,int> > > pq;

void set(){
    for(int i = 0; i <= 500000; i++){
        bro[i] = -1;
        check[i] = {1000000000,1000000000};
    }
    return;
}

void go(){
    int i = K, j = 1, t = 0;
    while(true){
        if(i > 500000){
            break;
        }
        bro[i] = t;
        if(bro[i] == check[i].first or bro[i] == check[i].second){
            pt = min(pt,bro[i]);
        }
        if(((bro[i] - check[i].first) % 2 == 0 and bro[i] > check[i].first) or ((bro[i] - check[i].second) % 2 == 0 and bro[i] > check[i].second)){
            pt = min(pt,bro[i]);
        }
        if((i > 0 and (bro[i] - check[i-1].first) % 2 == 1 and bro[i] > check[i-1].first) or (i > 0 and (bro[i] - check[i-1].second) % 2 == 1 and bro[i] > check[i-1].second)){
            pt = min(pt,bro[i]);
        }
        if((i < 500000 and (bro[i] - check[i+1].first) % 2 == 1 and bro[i] > check[i+1].first) or ( i < 500000 and (bro[i] - check[i+1].second) % 2 == 1 and bro[i] > check[i+1].second)){
            pt = min(pt,bro[i]);
        }
        i += j;
        j += 1;
        t++;
    }
    return;
}

void BFS(){
    while(!pq.empty()){
        int nowNum = pq.top().first;
        int nowIdx = pq.top().second;
        pq.pop();
        if(nowNum % 2 == 0){
            if(check[nowIdx].second <= nowNum){
                continue;
            }
            check[nowIdx].second = nowNum;
        }
        else{
            if(check[nowIdx].first <= nowNum){
                continue;
            }
            check[nowIdx].first = nowNum;
        }
        for(int i = 0; i <= 2; i++){
            int nextIdx = nowIdx;
            int nextNum = nowNum+1;
            if(i == 2){
                nextIdx *= 2;
            }
            else{
                nextIdx += dx[i];
            }
            if(nextIdx >= 0 and nextIdx <= 500000){
                if(nextNum % 2 == 0 and check[nextIdx].second > nextNum){
                    pq.push({nextNum,nextIdx});
                }
                else if(nextNum % 2 == 1 and check[nextIdx].first > nextNum){
                    pq.push({nextNum,nextIdx});
                }
            }
        }
    }
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> K;
    set();
    pq.push({0,N});
    BFS();
    go();
    if(pt == 1000000000){
        cout << -1 << "\n";
    }
    else{
        cout << pt << "\n";
    }
    return 0;
}
