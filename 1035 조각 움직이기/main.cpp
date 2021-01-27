//
// xb205
// 2021.01.27
//

#include <iostream>
#include <vector>
#include <utility>
#include <string>
#include <map>
using namespace std;
char arr[7][7];
int conn[7][7], cnt = 13, dy[] = {-1, 0, 1, 0}, dx[] = {0, -1, 0, 1}, mainBit = 1 << 26;
map<int, int> check;
vector<pair<int,int> > piece;

int getconn(int y, int x){
    conn[y][x] = 1;
    int num = 1;
    for(int i = 0; i < 4; i++){
        int ny = y + dy[i];
        int nx = x + dx[i];
        if(arr[ny][nx] != '*' or ny > 5 or nx > 5 or ny < 1 or nx < 1 or conn[ny][nx] != 0){
            continue;
        }
        num += getconn(ny, nx);
    }
    return num;
}

void getpiece(int _cnt, int bit){
    for(int i = 1; i <= 5; i++){
        for(int j = 1; j <= 5; j++){
            conn[i][j] = 0;
        }
    }
    if(getconn(piece[0].first, piece[0].second) == piece.size()){
        cnt = _cnt;
        return;
    }
    if(_cnt >= cnt){
        return;
    }
    for(int i = 0; i < piece.size(); i++){
        int y = piece[i].first;
        int x = piece[i].second;
        for(int j = 0; j < 4; j++){
            int ny = y+dy[j];
            int nx = x+dx[j];
            if(ny < 1 or nx < 1 or ny > 5 or nx > 5 or arr[ny][nx] == '*'){
                continue;
            }
            int pos2d = bit | (1 << ((5*(ny-1))+nx));
            pos2d = pos2d & ~(1 << (5*(y-1)+x));
            if(check.count(pos2d) != 0 and check[pos2d] <= _cnt+1){
                continue;
            }
            check[pos2d] = _cnt+1;
            piece[i] = {ny,nx};
            arr[ny][nx] = '*';
            arr[y][x] = '.';
            getpiece(_cnt+1, pos2d);
            arr[ny][nx] = '.';
            arr[y][x] = '*';
            piece[i] = {y,x};
        }
    }
}
int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    for(int i = 1; i <= 5; i++){
        string str;
        cin >> str;
        for(int j = 1; j <= 5; j++){
            arr[i][j] = str[j-1];
            if(arr[i][j] == '*'){
                piece.push_back({i,j});
                mainBit |= (1 << ((5*(i-1))+j));
            }
        }
    }
    check[mainBit] = 0;
    getpiece(0, mainBit);
    cout << cnt << "\n";
    return 0;
}
