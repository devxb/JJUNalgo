//
// xb205
// 2021.04.07
// 16985 Maaaaaaaaaze
//

#include <iostream>
#include <utility>
#include <algorithm>
#include <queue>
#include <deque>

using namespace std;

int maze[10][10][10][10];
int mazeForm[10], INF = 987654321;
int mazeCheck[10][10][10];
int mazeCnt;
deque<int> floor;
int checkFloor[10];
pair<int,pair<int,int>> sPos[10], ePos[10];
int dx[] = {0, -1, 0, 1, 0, 0};
int dy[] = {1, 0, -1, 0, 0, 0};
int dl[] = {0, 0, 0, 0, 1, -1};

void turnMaze(int layer, int turn){
	for(int y = 1; y <= 5; y++){
		int _idx = y+20;
		for(int x = 1; x <= 5; x++){
			int _y = ((_idx-1)/5)+1;
			int _x = ((_idx-1)%5)+1;
			maze[layer][turn+1][y][x] = maze[layer][turn][_y][_x];
			_idx -= 5;
		}
	}
	return;
}

int escape(int sl, int sy, int sx, int el, int ey, int ex){
	if(!maze[floor[sl]][mazeForm[floor[sl]]][sy][sx] || !maze[floor[el]][mazeForm[floor[el]]][ey][ex]) return INF;
	mazeCnt++;
	queue<pair<pair<int,int>,pair<int,int> > > q;
	q.push({{sl,0},{sy,sx}});
	mazeCheck[floor[sl]][sy][sx] = mazeCnt;
	while(!q.empty()){
		int layer = q.front().first.first;
		int move = q.front().first.second;
		int y = q.front().second.first;
		int x = q.front().second.second;
		q.pop();
		if(y == ey && x == ex && floor[layer] == floor[el]) return move;
		for(int i = 0; i < 6; i++){
			int ny = y + dy[i];
			int nx = x + dx[i];
			int nl = layer + dl[i];
			if(nl < 1 || nl > 5 || ny < 1 || ny > 5 || nx < 1 || nx > 5 || mazeCheck[floor[nl]][ny][nx] == mazeCnt || !maze[floor[nl]][mazeForm[floor[nl]]][ny][nx]) continue;
			mazeCheck[floor[nl]][ny][nx] = mazeCnt;
			q.push({{nl,move+1},{ny,nx}});
		}
	}
	return INF;
}

int getMove(int layer){
	int ret = INF;
	if(layer > 5){
		for(int i = 0; i < 4; i++) 
			ret = min(ret, escape(sPos[i].first, sPos[i].second.first, sPos[i].second.second,
								  ePos[i].first, ePos[i].second.first, ePos[i].second.second));
		return ret;
	}
	for(int turn = 1; turn <= 4; turn++){
		mazeForm[floor[layer]] = turn;
		ret = min(ret,getMove(layer+1));
	}
	return ret;
}

int getFloor(deque<int> &_floor, int cnt){
	int ret = INF;
	if(cnt == 6){
		ret = min(ret, getMove(1));
		return ret;
	}
	for(int f = 1; f <= 5; f++){
		if(checkFloor[f]) continue;
		checkFloor[f] = 1;
		floor.push_back(f);
		ret = min(ret, getFloor(floor, cnt+1));
		floor.pop_back();
		checkFloor[f] = 0;
	}
	if(cnt == 1 && ret == INF) ret = -1;
	return ret;
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	sPos[0] = {1,{1,1}};
	sPos[1] = {1,{1,5}};
	sPos[2] = {1,{5,1}};
	sPos[3] = {1,{5,5}};
	ePos[0] = {5,{5,5}};
    ePos[1] = {5,{5,1}};
	ePos[2] = {5,{1,5}};
    ePos[3] = {5,{1,1}};
	for(int layer = 1; layer <= 5; layer++){
		for(int y = 1; y <= 5; y++){
			for(int x = 1; x <= 5; x++){
				cin >> maze[layer][1][y][x];
			}
		}
		for(int t = 1; t <= 3; t++) turnMaze(layer, t);
	}
	floor.push_back(-1);
	cout << getFloor(floor,1) << "\n";
	return 0;
}
