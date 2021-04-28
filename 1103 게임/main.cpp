//
// xb205
// 2021.04.28
// 1103 게임
//

#include <iostream>
#include <string>

using namespace std;

int N,M;
int arr[55][55], dp[55][55], path[55][55], p;
int INF = 987654321;
int dy[] = {-1, 0, 1, 0};
int dx[] = {0, -1, 0, 1};

int outOfBound(int y, int x){
	return (y < 1 || y > N || x < 1 || x > M);
}

bool getPath(int y, int x, int ty, int tx){
	if(outOfBound(y,x) || arr[y][x] == 0) return 0;
	if(y == ty && x == tx) return 1;
	bool ret = 0;
	if(path[y][x] == p) return 0;
	path[y][x] = p;
	for(int i = 0; i < 4; i++){
		int _y = y + dy[i]*arr[y][x];
		int _x = x + dx[i]*arr[y][x];
		ret = max(ret,getPath(_y,_x,ty,tx));
	}
	return ret;
}

int getCoinMove(int y, int x, int by, int bx){
	if(outOfBound(y,x) || arr[y][x] == 0) return 0;
	p++;
	if((by != 1 || bx != 1) && getPath(y, x, by, bx))return INF;
	int &ret = dp[y][x];
	if(ret != 0) return ret;
	for(int i = 0; i < 4; i++){
		int _y = y + dy[i]*arr[y][x];
		int _x = x + dx[i]*arr[y][x];
		ret = max(ret,getCoinMove(_y, _x, y, x)+1);
	}
	if(y == 1 && x == 1 && by == 1 && bx == 1 && ret >= INF) ret = -1;
	return ret;
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> N >> M;
	for(int i = 1; i <= N; i++){
		string str;
		cin >> str;
		for(int j = 1; j <= M; j++){
			if(str[j-1] == 'H') arr[i][j] = 0;
			else arr[i][j] = (int)(str[j-1] - '0');
		}
	}
	cout << getCoinMove(1,1,1,1) << "\n";
	return 0;
}
