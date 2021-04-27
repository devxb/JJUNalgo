//
// xb205
// 2021.4.27
// 1938 통나무 옮기기
//

#include <iostream>
#include <string>
#include <queue>
#include <vector>
#include <utility>
#include <map>
#include <algorithm>

using namespace std;

int N;
char land[55][55];
map<vector<pair<int,int> >,int> check;
queue<pair<int,vector<pair<int,int> > > > wood; // cnt, y, x
int dy[] = {-1, 0, 1, 0};
int dx[] = {0, -1, 0, 1};

int abs(int a, int b){
	return max(a-b,b-a);
}

bool outOfBounds(int y, int x){
	return (y <= N && y >= 1 && x <= N && x >= 1);
}

bool compareEEE(vector<pair<int,int> > &wood){
	for(int i = 0; i < 3; i++){
		if(land[wood[i].first][wood[i].second] != 'E') return 0;
	}
	return 1;
}

bool turnWood(vector<pair<int,int> > &woodPos){
	int centerY = woodPos[1].first;
	int centerX = woodPos[1].second;
	pair<int,int> leftTop = {centerY-1,centerX-1};
	pair<int,int> rightDown = {centerY+1,centerX+1};
	for(int i = leftTop.first; i <= rightDown.first; i++){
		for(int j = leftTop.second; j <= rightDown.second; j++)
			if(land[i][j] == '1') return false;
	}
	if(abs(woodPos[1].second - woodPos[0].second)){ // 가로 통나무라면 세로로 돌리기
		woodPos[0] = {woodPos[1].first-1, woodPos[1].second};
		woodPos[2] = {woodPos[1].first+1, woodPos[1].second};
	}
	else if(abs(woodPos[0].first - woodPos[1].first)){ // 세로 통나무라면 가로로 돌리기
		woodPos[0] = {woodPos[1].first, woodPos[1].second-1};
		woodPos[2] = {woodPos[1].first, woodPos[1].second+1};
	}
	if(!outOfBounds(woodPos[0].first, woodPos[0].second) || !outOfBounds(woodPos[2].first, woodPos[2].second) || !outOfBounds(woodPos[1].first, woodPos[1].second))
		return false;
	return true;
}

int carryWood(){
	int ret = 0;
	check[wood.front().second] = 0;
	while(!wood.empty()){
		int cnt = wood.front().first;
		vector<pair<int,int> > woodPos = wood.front().second;
		wood.pop();
		if(check[woodPos] < cnt) continue;
		if(compareEEE(woodPos)){
			ret = cnt;
			break;
		}
		for(int i = 0; i < 4; i++){
			int _cnt = cnt+1;
			bool block = false;
			vector<pair<int,int> > _woodPos;
			for(int j = 0; j < 3; j++){
				_woodPos.push_back({woodPos[j].first + dy[i], woodPos[j].second + dx[i]});
				int _y = _woodPos[j].first, _x = _woodPos[j].second;
				if((!outOfBounds(_y,_x)) || land[_y][_x] == '1') block = true;
			}
			int &checking = check[_woodPos];
			if((checking != 0 && checking <= _cnt) || block) continue;
			checking = _cnt;
			wood.push({_cnt,_woodPos});
		}
		if(turnWood(woodPos)){
			int &checking = check[woodPos];
			if(checking == 0 || checking > cnt+1){
				checking = cnt+1;
				wood.push({cnt+1, woodPos});
			}
		}
	}
	return ret;
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> N;
	vector<pair<int,int> > branch;
	for(int i = 1; i <= N; i++){
		string str;
		cin >> str;
		for(int j = 1; j <= N; j++){
			land[i][j] = str[j-1];
			if(land[i][j] == 'B') branch.push_back({i,j});
		}
	}
	wood.push({0,branch});
	cout << carryWood() << "\n";
	return 0;
}
