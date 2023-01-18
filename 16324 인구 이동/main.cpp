#include <iostream>
#include <algorithm>
#include <string>
#include <vector>
#include <deque>
#include <queue>

using namespace std;

int N, L, R;
int border[55][55];
int dy[] = {0, 1, 0, -1};
int dx[] = {1, 0, -1, 0};
vector<deque<int>> connected(2500);

pair<int, int> idx1dTo2d(int idx){
    return {idx / N, idx % N};
}

void moveToBorder(pair<int, vector<int>> unions){
    int pep = unions.first / unions.second.size();
    for(int i = 0; i < unions.second.size(); i++){
        pair<int, int> idx2d = idx1dTo2d(unions.second[i]);
        border[idx2d.first][idx2d.second] = pep;
    }
}

int idx2dTo1d(int y, int x){
    return y * N + x;
}

pair<int, vector<int>> getUnion(int y, int x, bool visited[2500]){
    int ans = 0;
    vector<int> ansVec;
    int idx = idx2dTo1d(y, x);
    deque<int> nextIdxes;
    nextIdxes.push_back(idx);
    while(!nextIdxes.empty()){
        int currentIdx = nextIdxes.front();
        nextIdxes.pop_front();
        if(!visited[currentIdx]){
            ansVec.push_back(currentIdx);
            pair<int, int> idx2d = idx1dTo2d(currentIdx);
            ans += border[idx2d.first][idx2d.second];
        }
        visited[currentIdx] = true;
        while(!connected[currentIdx].empty()){
            int nextIdx = connected[currentIdx].front();
            connected[currentIdx].pop_front();
            nextIdxes.push_back(nextIdx);
        }
    }
    return {ans, ansVec};
}

bool isConnectable(int y, int x, int ty, int tx){
    if(y >= N || y < 0 || x < 0 || x >= N) return false;
    if(ty >= N || ty < 0 || tx < 0 || tx >= N) return false;
    return L <= abs(border[y][x] - border[ty][tx]) && abs(border[y][x] - border[ty][tx]) <= R;
}

bool connectBorders(){
    bool isConnectedLeastOne = false;
    for(int i = 0; i < N; i++){
        for(int j = 0; j < N; j++){
            for(int d = 0; d < 4; d++){
                int nextY = i + dy[d];
                int nextX = j + dx[d];
                if(isConnectable(i, j, nextY, nextX)) {
                    connected[idx2dTo1d(i, j)].push_back(idx2dTo1d(nextY, nextX));
                    isConnectedLeastOne = true;
                }
            }
        }
    }
    return isConnectedLeastOne;
}

void input(){
    cin >> N >> L >> R;
    for(int y = 0; y < N; y++){
        for(int x = 0; x < N; x++){
            cin >> border[y][x];
        }
    }
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    input();
    int result = 0;
    while(connectBorders()){
        result++;
        bool visited[2500];
        for(int i = 0; i < 2500; i++) visited[i] = false;
        for(int y = 0; y < N; y++){
            for(int x = 0; x < N; x++){
                if(visited[idx2dTo1d(y, x)]) continue;
                pair<int, vector<int>> unions = getUnion(y, x, visited);
                moveToBorder(unions);
            }
        }
    }
    cout << result << "\n";
}
