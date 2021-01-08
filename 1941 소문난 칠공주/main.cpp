//
//  main.cpp
//  1941 소문난 칠공주
//
//  Created by 이준영 on 2021/01/07.
//

#include <iostream>
#include <string>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;
int dy[] = {-1,0,1,0}, dx[] = {0,-1,0,1}, circle, path[30], check[30], princess;
char arr[30];

pair<int,int> rocol(int idx){
    return {((idx-1) / 5) + 1, ((idx-1) % 5) + 1};
}

bool adj(int idx){
    int cnt = 0;
    bool checkAdj[10][10] = {false};
    queue<pair<int,int> > q;
    vector<int> vec;
    for(int fri = idx; fri != 0; fri = path[fri]){
        checkAdj[rocol(fri).first][rocol(fri).second] = true;
        if(q.size() == 0){
            q.push(rocol(fri));
        }
        if(arr[fri] == 'S'){
            cnt++;
        }
    }
    if(cnt >= 4){
        int cntAdj = 0;
        checkAdj[q.front().first][q.front().second] = false;
        while(!q.empty()){
            cntAdj++;
            pair<int,int> now = q.front();
            q.pop();
            for(int i = 0; i < 4; i++){
                pair<int,int> next = {now.first + dy[i], now.second + dx[i]};
                if(checkAdj[next.first][next.second] == true){
                    checkAdj[next.first][next.second] = false;
                    q.push(next);
                }
            }
        }
        if(cntAdj != 7){
            return false;
        }
        return true;
    }
    return false;
}

void pick(int idx, int cnt){
    if(cnt == 7){
        if(adj(idx) == true){
            princess++;
        }
        return;
    }
    for(int i = idx+1; i <= 25; i++){
        path[i] = idx;
        pick(i, cnt+1);
        path[i] = 0;
    }
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    for(int i = 1; i <= 5; i++){
        string str;
        cin >> str;
        for(int j = 0; j < 5; j++){
            int num1D = (i-1)*5+(j+1);
            arr[num1D] = str[j];
        }
    }
    for(int i = 1; i <= 25; i++){
        pick(i, 1);
    }
    cout << princess << "\n";
    return 0;
}
