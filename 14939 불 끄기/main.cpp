//
// xb205
// 2021.04.10
// 14939 불 끄기
//

#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
#include <bitset>

using namespace std;

vector<int> board;
int dy[] = {0, 1, 0, -1, 0};
int dx[] = {0, 0, 1, 0, -1};
int INF = 987654321;

void toggle(vector<int> &tar, int y, int x){
	for(int i = 0; i < 5; i++){
		int ny = y + dy[i];
		int nx = x + dx[i];
		if(ny > 9 || nx > 9 || ny < 0 || nx < 0) continue;
		tar[ny] = tar[ny] ^ (1 << nx);
	}
}

int pushSwitch(int bit){
	int ret = 0;
	vector<int> _board = board;
	for(int x = 0; x < 10; x++){
		if((bit & (1 << x)) == 0) continue;
		toggle(_board, 0, x);
		ret++;
	}
	for(int y = 1; y < 10; y++){
		if(_board[y-1] == 0) continue;
		int &bitBoard = _board[y-1];
		for(int x = 0; x < 10; x++){
			if((bitBoard & (1 << x)) == 0) continue;
			toggle(_board, y, x);
			ret++;
		}
	}
	if(_board[9] != 0) return INF;
	return ret;
}

int pushCons(int idx, int bit){
	int ret = pushSwitch(bit);
	for(int i = idx; i < 10; i++){
		ret = min(ret, pushCons(i+1, bit | (1 << i)));
	}
	if(bit == 0 && ret == INF) ret = -1; 
	return ret;
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	for(int i = 0; i < 10; i++){
		string str;
		cin >> str;
		int bit = 0;
		for(int j = 0; j < str.size(); j++)
			if(str[str.size()-1-j] == 'O') bit |= (1 << j);
		board.push_back(bit);
	}
	cout << pushCons(0,0) << "\n";
	return 0;
}
