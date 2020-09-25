//
//  main.cpp
//  6146 신아를 만나러
//
//  Created by 이준영 on 2020/09/25.
//

#include <iostream>
#include <deque>
#include <utility>
#include <algorithm>

using namespace std;

int X, Y, N;
int min_cnt = 2000000000;
int pond[1010][1010], check[1010][1010];
int dy[] = {-1,0,1,0};
int dx[] = {0,-1,0,1};

void BFS(){
    deque<pair<int,pair<int,int> > > deq;
    deq.push_back({0,{500,500}});
    while(!deq.empty()){
        int now_Y = deq.front().second.first;
        int now_X = deq.front().second.second;
        int now_cnt = deq.front().first;
        //cout << now_Y << " " << now_X << "\n";
        deq.pop_front();
        if((check[now_Y][now_X] <= now_cnt and check[now_Y][now_X] > 0) or now_cnt >= min_cnt){
            continue;
        }
        check[now_Y][now_X] = now_cnt;
        if(now_Y == Y and now_X == X){
            min_cnt = min(min_cnt, now_cnt);
            continue;
        }
        for(int i = 0; i < 4; i++){
            int next_Y = now_Y + dy[i];
            int next_X = now_X + dx[i];
            int next_cnt = now_cnt+1;
            if(next_Y > 1000 or next_Y < 0 or next_X > 1000 or next_X < 0 or pond[next_Y][next_X] == 1 or (next_cnt >= check[next_Y][next_X] and check[next_Y][next_X] > 0)){
                continue;
            }
            deq.push_back({next_cnt,{next_Y,next_X}});
        }
    }
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> X >> Y >> N;
    X = X + 500;
    Y = Y + 500;
    for(int i = 1; i <= N; i++){
        int a, b;
        cin >> a >> b;
        pond[b+500][a+500] = 1;
    }
    BFS();
    cout << min_cnt << "\n";
    return 0;
}
