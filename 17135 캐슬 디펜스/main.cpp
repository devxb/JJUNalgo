//
// xb205
// 2021.4.22
// 17135 캐슬 디펜스
//

#include <iostream>
#include <deque>
#include <utility>

using namespace std;

int N, M, D, cnt;
bool arr[20][20];
bool check[20][20][2000];
deque<int> deq;

int abs(int a, int b){
	return max(a-b, b-a);
}

int getDis(int y1, int x1, int y2, int x2){
	return abs(y2-y1)+abs(x2-x1);
}

int getCastleDefense(int castle, int cnt){
	if(castle == 1) return 0;
	deque<pair<int,int> > kill;
	int ret = 0;
	for(int archer = 0; archer < 3; archer++){
		int archerY = castle;
		int archerX = deq[archer];
		int gety = 0, getx = 0, dis = 987654321;
		for(int hei = castle-1; hei >= castle-D; hei--){
			for(int enemy = 1; enemy <= M; enemy++){
				int y = hei;
				int x = enemy;
				int _dis = getDis(archerY, archerX, y, x);
				if(_dis > D || !arr[y][x] || check[y][x][cnt]) continue;
				if(_dis < dis || (_dis == dis && getx > x)){
					gety = y;
					getx = x;
					dis = _dis;
				}
			}
		}
		if(gety == 0 && getx == 0) continue;
		kill.push_back({gety,getx});
	}
	while(!kill.empty()){
		int y = kill.front().first;
		int x = kill.front().second;
		kill.pop_front();
		if(check[y][x][cnt]) continue; 
		ret++;
		check[y][x][cnt] = true;
	}
	return ret + getCastleDefense(castle-1, cnt);
}

int getKill(int idx){
	if(deq.size() == 3){
		cnt++;
		return getCastleDefense(N+1, cnt);
	}
	int ret = 0;
	for(int i = idx; i <= M; i++){
		deq.push_back(i);
		ret = max(ret, getKill(i+1));
		deq.pop_back();
	}
	return ret;
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> N >> M >> D;
	for(int i = 1; i <= N; i++)
		for(int j = 1; j <= M; j++) cin >> arr[i][j];
	
	cout << getKill(1) << "\n";
	
	return 0;
}
