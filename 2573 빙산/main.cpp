//
// xb205
// 2021.04.18
// 2573 빙산
//

#include <iostream>
#include <algorithm>
#include <deque>
#include <utility>

using namespace std;
int N,M,Y;
int arr[305][305][15];
int dy[] = {-1,0,1,0};
int dx[] = {0,-1,0,1};

int getWater(int y, int x, int year){
	int ret = 0;
	for(int i = 0; i < 4; i++){
		int _y = y + dy[i];
		int _x = x + dx[i];
		if(_y > 0 && _y <= N && _x > 0 && _x <= M && !arr[y+dy[i]][x+dx[i]][year]) ret++;
	}
	return ret;
}

void melt(int year){
	for(int i = 1; i <= N; i++){
		for(int j = 1; j <= M; j++){
			arr[i][j][year] = max(0, arr[i][j][year-1] - getWater(i,j,year-1));
		}
	}
}

bool separate(int year){
	deque<pair<int,int> > deq;
	for(int i = 1; i <= N; i++){
		bool b = false;
		for(int j = 1; j <= M; j++){
			if(arr[i][j][year] != 0){
				deq.push_back({i,j});
				b = true;
				break;
			}
		}
		if(b) break;
	}
	bool check[305][305] = {{0, }, };
	while(!deq.empty()){
		int y = deq.front().first;
		int x = deq.front().second;
		deq.pop_front();
		if(check[y][x]) continue;
		check[y][x] = true;
		for(int i = 0; i < 4; i++){
			int _y = y + dy[i];
			int _x = x + dx[i];
			if(_y > N || _y < 1 || _x > M || _x < 1 || check[_y][_x] || !arr[_y][_x][year]) continue;
			deq.push_back({_y,_x});
		}
	}
	for(int i = 1; i <= N; i++){
		for(int j = 1; j <= M; j++){
			if(arr[i][j][year] != 0 && !check[i][j]) return true;
		}
	}
	return false;
}
bool emptyField(int year){
	for(int i = 1; i <= N; i++){
		for(int j = 1; j <= M; j++){
			if(arr[i][j][year] > 0) return false;
		}
	}
	return true;
}
int countYears(){
	if(separate(0)) return 0;
	for(int year = 1; year < 1e9; year++){
		melt(year);
		if(emptyField(year)) return 0;
		if(separate(year)) return year;
	}
	return 0;
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);	
	cin >> N >> M;
	int zero = 0;
	for(int i = 1; i <= N; i++){
		for(int j = 1; j <= M; j++) {
			cin >> arr[i][j][0];
			if(arr[i][j][0] == 0) zero++;
		}
	}
	if(zero == 0) cout << 0 << "\n"; 
	else cout << countYears() << "\n";
	return 0;
}
