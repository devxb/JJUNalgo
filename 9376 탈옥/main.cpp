//
// xb205
// 2021.04.16
// 9376 탈옥
//

#include <iostream>
#include <algorithm>
#include <utility>
#include <vector>
#include <queue>

using namespace std;
typedef pair<int,pair<int,int> > piii;
typedef pair<pair<int,int>, pair<int,int> > piiii;
int T, h, w, INF = 987654321;
int dy[] = {-1, 0, 1, 0};
int dx[] = {0, -1, 0, 1};
char jail[105][105];
int escape[105][105];
pair<int,int> pri[2];
priority_queue<piii, vector<piii>, greater<piii> > pq;

void getExit(){
	for(int x = 1; x <= w; x++) {
		if(jail[1][x] == '.' || jail[1][x] == '$'){ 
			pq.push({0,{1,x}});
			escape[1][x] = 0;
		}
		if(jail[1][x] == '#'){ 
			pq.push({1,{1,x}});
			escape[1][x] = 1;
		}
		if(jail[h][x] == '.' || jail[h][x] == '$'){
			pq.push({0,{h,x}});
			escape[h][x] = 0;
		}
		if(jail[h][x] == '#') {
			pq.push({1,{h,x}});
			escape[h][x] = 1;
		}
	}
	for(int y = 1; y <= h; y++){
		if(jail[y][1] == '.' || jail[y][1] == '$'){ 
			pq.push({0,{y,1}});
			escape[y][1] = 0;
		}
		if(jail[y][1] == '#'){ 
			pq.push({1,{y,1}});
			escape[y][1] = 1;
		}
		if(jail[y][w] == '.' || jail[y][w] == '$'){ 
			pq.push({0,{y,w}});
			escape[y][w] = 0;
		}
		if(jail[y][w] == '#'){
			pq.push({1,{y,w}});
			escape[y][w] = 1;
		}
	}
	return;
}
void setEscape(){
	for(int y = 1; y <= h; y++)
		for(int x = 1; x <= w; x++) escape[y][x] = INF;
	getExit();
	while(!pq.empty()){
		int cnt = pq.top().first;
		int y = pq.top().second.first;
		int x = pq.top().second.second;
		pq.pop();
		if(escape[y][x] < cnt) continue;
		for(int i = 0; i < 4; i++){
			int _y = y + dy[i];
			int _x = x + dx[i];
			int _cnt = cnt;
			if(jail[_y][_x] == '#') _cnt++;
			if(jail[_y][_x] == '*' || escape[_y][_x] <= _cnt || _y > h || _y < 1 || _x > w || _x < 1) continue;
			escape[_y][_x] = _cnt;
			pq.push({_cnt,{_y,_x}});
		}
	}
}

int prisonBreak(){
	int open[h+5][w+5][3];
	int minPri[3];
	minPri[0] = minPri[1] = INF;
	
	for(int y = 1; y <= h; y++)
		for(int x = 1; x <= w; x++) open[y][x][0] = open[y][x][1] = INF;
		
	priority_queue<piiii, vector<piiii>, greater<piiii> > pq;
	pq.push({{0,0},{pri[0].first, pri[0].second}});
	pq.push({{0,1},{pri[1].first, pri[1].second}});
	open[pri[0].first][pri[0].second][0] = 0;
	open[pri[1].first][pri[1].second][1] = 1;
	while(!pq.empty()){
		int cnt = pq.top().first.first;
		int prisoner = pq.top().first.second;
		int y = pq.top().second.first;
		int x = pq.top().second.second;
		if(y == 1 || x == 1 || y == h || x == w) minPri[prisoner] = min(minPri[prisoner],cnt);
		pq.pop();
		if(open[y][x][prisoner] < cnt) continue;
		for(int i = 0; i < 4; i++){
			int _y = y + dy[i];
			int _x = x + dx[i];
			int _cnt = cnt;
			if(jail[_y][_x] == '#') _cnt++;
			if(jail[_y][_x] == '*' || open[_y][_x][prisoner] <= _cnt || _y > h || _y < 1 || _x > w || _x < 1) continue;
			open[_y][_x][prisoner] = _cnt;
			pq.push({{_cnt,prisoner},{_y,_x}});
		}
	}
	int ret = minPri[0]+minPri[1];
	for(int y = 1; y <= h; y++){
		for(int x = 1; x <= w; x++){
			if(escape[y][x] == INF) continue;
			int comp = escape[y][x]+open[y][x][0]+open[y][x][1];
			if(jail[y][x] == '#') comp -= 2;
			ret = min(ret, comp);
		}
	}
	return ret;
}

int openDoor(){
	setEscape();
	return prisonBreak();
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> T;
	for(int tc = 0; tc < T; tc++){
		int prisoner = 0;
		cin >> h >> w;
		for(int height = 1; height <= h; height++){
			for(int width = 1; width <= w; width++) {
				cin >> jail[height][width];
				if(jail[height][width] == '$'){
					pri[prisoner] = {height, width};
					prisoner++;
				}
			}
		}
		cout << openDoor() << "\n";
	}
	return 0;
}
