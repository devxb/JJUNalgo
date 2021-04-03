//
// xb205
// 2021.04.03
// 10875
//

#include <iostream>
#include <algorithm>
#include <utility>
#include <vector>
#include <deque>
typedef long long int ll;
using namespace std;
ll L, N, boundary[2], histSize; // boundary = [음, 양]
pair<ll,ll> dir[100];
pair<char,pair<ll,ll> > snake; // dir, {x, y}
vector<pair<pair<ll,ll>, pair<ll,ll> > > movHist;
deque<pair<ll,char> > mov;
bool crush = false;
void set(int l){
	dir[(int)'U'] = {0,1};
	dir[(int)'D'] = {0,-1};
	dir[(int)'L'] = {-1,0};
	dir[(int)'R'] = {1,0};
	// set snake
	snake = {'R',{0,0}};
	// set boundary
	boundary[0] = -1*l-1;
	boundary[1] = l+1;
}

char getTurn(char now, char next){
	if((now == 'U' && next == 'L') || (now == 'D' && next == 'R')) return 'L';
	if((now == 'U' && next == 'R') || (now == 'D' && next == 'L')) return 'R';
	if((now == 'L' && next == 'R') || (now == 'R' && next == 'L')) return 'U';
	if((now == 'L' && next == 'L') || (now == 'R' && next == 'R')) return 'D';
	return 'X';
}

ll absl(ll num){
	return max(num, num*-1);
}

ll die(ll fromX, ll fromY, ll toX, ll toY, ll pre){ // 뱀 도착시간
	// 자신의 몸에 걸린다면
	ll ret = pre;
	for(int i = 0; i < histSize-1; i++){
		ll histX = movHist[i].first.first, _histX = movHist[i].second.first;
		ll histY = movHist[i].first.second, _histY = movHist[i].second.second;
		// 가로선분 가로선분 : fromY와 toY가 같으며, histY와 _histY가 같음
		if(fromY == toY && histY == _histY) {
			if(fromY != histY) continue; // 절대 만날일이 없다면
			if((histX <= fromX && fromX <= _histX) || (histX <= toX && toX <= _histX) || 
			  (min(fromX,toX) <= histX && histX <= max(fromX,toX)) || (min(fromX,toX) <= _histX && _histX <= max(fromX,toX))){
				ret = min(ret, min(absl(fromX-histX),absl(fromX-_histX))); 
				crush = true;
			}
				// 더 일찍 죽을경우가 있으므로 바로 return하면 안됨
		} 
		// 세로선분 세로선분 : fromX와 toX가 같으며, histX와 _histX가 같음
		else if(fromX == toX && histX == _histX) {
			if(fromX != histX) continue;
			if((histY <= fromY && fromY <= _histY) || (histY <= toY && toY <= _histY) || 
			  (min(fromY,toY) <= histY && histY <= max(fromY,toY)) || (min(fromY,toY) <= _histY && _histY <= max(fromY,toY))){
				ret = min(ret, min(absl(fromY-histY),absl(fromY-_histY)));
				crush = true;
			}
		}
		// 가로선분 세로선분 : fromY와 toY가 같으며, histX와 _histX가 같음
		else if(fromY == toY && histX == _histX){
			if(histY <= fromY && fromY <= _histY && min(fromX,toX) <= histX && histX <= max(fromX,toX)){ // 교차한다면
				ret = min(ret, absl(histX - fromX));
				crush = true;
			}
		}
		// 세로선분 가로선분 : fromX와 toX가 같으며, histY와 _histY가 같음
		else if(fromX == toX && histY == _histY){
			if(histX <= fromX && fromX <= _histX && min(fromY,toY) <= histY && histY <= max(fromY,toY)){ // 교차한다면
				ret = min(ret, absl(histY - fromY));
				crush = true;
			}
		}
	}
	// 위 경우를 전부 통과했다면 범위 벗어나는지 판정
	// 가로로 벗어났다면
	if(toX <= boundary[0]){ 
		ret = min(ret, absl(fromX-boundary[0]));
		crush = true;
	}
	else if(toX >= boundary[1]){
		ret = min(ret, absl(fromX-boundary[1]));
		crush = true;
	}
	else if(toY <= boundary[0]){
		ret = min(ret, absl(fromY-boundary[0]));
		crush = true;
	}
	else if(toY >= boundary[1]){
		ret = min(ret, absl(fromY-boundary[1]));
		crush = true;
	}
	return ret;
}

void updateHist(pair<ll,ll> from, pair<ll,ll> to){
	movHist.push_back({min(from,to),max(from,to)});
	histSize++;
	return;
}

ll simul(pair<char,pair<ll,ll> > _snake){
	ll _time = 0; 
	if(mov.empty()) _time = 9876543210000;
	else _time = mov.front().first;
	ll x = _snake.second.first, y = _snake.second.second;
	ll _x = x + _time*dir[_snake.first].first, _y = y + _time*dir[_snake.first].second;
	ll movTime = die(x , y, _x, _y, _time);
	if(crush == true){
		return movTime;
	}
	updateHist({x, y}, {_x, _y}); // 머리위치는 제외하고 저장
	ll _head = getTurn(_snake.first, mov.front().second);
	mov.pop_front();
	return movTime + simul({_head, {_x, _y}});
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> L >> N;
	set(L);
	for(int i = 1; i <= N; i++){
		ll t;
		char d;
		cin >> t >> d;
		mov.push_back({t,d});
	}
	cout << simul(snake) << "\n";
	return 0;
}
