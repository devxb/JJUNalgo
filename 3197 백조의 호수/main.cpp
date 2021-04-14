//
// xb205
// 2021.04.14
// 3197 백조의 호수
//

#include <iostream>
#include <algorithm>
#include <string>
#include <deque>
#include <utility>
#include <queue>

using namespace std;
int R, C, INF = 987654321;
char lake[1505][1505];
int waterCheck[1505][1505], swanCheck[1505][1505][3];
deque<pair<int,pair<int,int> > > water;
priority_queue<pair<pair<int,int>,pair<int,int> >, vector<pair<pair<int,int>,pair<int,int> > >, greater<pair<pair<int,int>,pair<int,int> > > > swan;
int dy[] = {-1, 0, 1, 0};
int dx[] = {0, -1, 0, 1};

void init(){
	for(int i = 1; i <= R; i++)
		for(int j = 1; j <= C; j++){
			waterCheck[i][j] = INF;
			swanCheck[i][j][0] = INF;
			swanCheck[i][j][1] = INF;
		}
	return;
}

void flow(){
	while(!water.empty()){
		int cnt = water.front().first;
		int y = water.front().second.first;
		int x = water.front().second.second;
		water.pop_front();
		if(waterCheck[y][x] < cnt) continue;
		for(int dir = 0; dir < 4; dir++){
			int _y = y+dy[dir];
			int _x = x+dx[dir];
			if(_y > R || _y < 1 || _x > C || _x < 1 || waterCheck[_y][_x] <= cnt+1) continue;
			waterCheck[_y][_x] = cnt+1;
			water.push_back({cnt+1,{_y,_x}});
		}
	}
}

int swim(){
	while(!swan.empty()){
		int cnt = swan.top().first.first;
		int name = swan.top().first.second;
		int y = swan.top().second.first;
		int x = swan.top().second.second;
		swan.pop();
		if(swanCheck[y][x][name] < cnt) continue;
		for(int dir = 0; dir < 4; dir++){
			int _y = y+dy[dir];
			int _x = x+dx[dir];
			int _cnt = max(cnt,waterCheck[_y][_x]);
			if(_y > R || _y < 1 || _x > C || _x < 1 || swanCheck[_y][_x][name] <= _cnt) continue;
			swanCheck[_y][_x][name] = _cnt;
			swan.push({{_cnt,name},{_y,_x}});
		}
	}
	int ret = INF;
	for(int y = 1; y <= R; y++){
		for(int x = 1; x <= C; x++){
			ret = min(ret, max(swanCheck[y][x][0],swanCheck[y][x][1]));
		}
	}
	return ret;
}

int getDay(){
	flow();
	return swim();
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> R >> C;
	init();
	int s=0;
	for(int i = 1; i <= R; i++){
		string str;
		cin >> str;
		for(int j = 1; j <= C; j++){
			lake[i][j] = str[j-1];
			if(lake[i][j] == '.' || lake[i][j] == 'L'){
				waterCheck[i][j] = 0;
				water.push_back({0,{i,j}});
			}
			if(lake[i][j] == 'L'){
				swanCheck[i][j][s] = 0;
				swan.push({{0,s},{i,j}});
				s++;
			}
		}
	}
	cout << getDay() << "\n";
	return 0;
}
