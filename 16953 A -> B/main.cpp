//
//  main.cpp
//  16953 A -> B
//
//  Created by 이준영 on 2021/01/17.
//

#include <iostream>
#include <queue>
#include <utility>
using namespace std;
long long A, B, cnt = -1;
int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> A >> B;
    queue<pair<long long, long long> > q;
    q.push({1, A});
    while(!q.empty()){
        long long nowNum = q.front().second;
        long long nowCnt = q.front().first;
        q.pop();
        if(nowNum == B){
            cnt = nowCnt;
            break;
        }
        if(nowNum > B){
            continue;
        }
        q.push({nowCnt+1, nowNum*2});
        q.push({nowCnt+1, nowNum*10+1});
    }
    cout << cnt << "\n";
    return 0;
}
