//
// xb205
// 2021.04.21
// 14500 테트로미노
//

#include <iostream>
#include <algorithm>

using namespace std;

int N, M;
int paper[505][505];
bool check[505][505];
int dy[] = {-1, 0, 1, 0};
int dx[] = {0, -1, 0, 1};

int getNum(int y, int x, int c){
	if(c > 4) return 0;
	int ret = 0;
	for(int i = 0; i < 4; i++){
		int _y = y+dy[i];
		int _x = x+dx[i];
		if(_y > N || _y < 1 || _x > M || _x < 1 || check[_y][_x] == 1) continue;
		check[_y][_x] = 1;
		ret = max(ret, getNum(_y,_x,c+1));
		check[_y][_x] = 0;
	}
	return ret+paper[y][x];
}

int getCross(int y, int x){
	int ret = paper[y][x];
	for(int i = 0; i < 4; i++){
		ret += paper[y+dy[i]][x+dx[i]];
	}
	return ret = max(max(ret-paper[y+dy[0]][x+dx[0]],ret-paper[y+dy[1]][x+dx[1]]),max(ret-paper[y+dy[2]][x+dx[2]],ret-paper[y+dy[3]][x+dx[3]]));
}

int getTetromino(){
	int ret = 0, ch = 1;
	for(int i = 1; i <= N; i++)
		for(int j = 1; j <= M; j++) {
			check[i][j] = 1;
			ret = max(max(ret, getNum(i, j, 1)),getCross(i,j));
			check[i][j] = 0;
			ch++;
		}
	return ret;
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> N >> M;
	for(int i = 1; i <= N; i++)
		for(int j = 1; j <= M; j++) cin >> paper[i][j];
	
	cout << getTetromino() << "\n";
	return 0;
}
