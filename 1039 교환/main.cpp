//
// xb205
// 2021.04.20
// 1039 교환
//

#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
#include <utility>
#include <map>

using namespace std;

int N, K;
vector<int> num;
map<pair<int,vector<int> >, int> check;

void pushNum(int N){
	if(!N) return;
	pushNum(N/10);
	num.push_back(N%10);
}

void swap(int &a, int &b){
	int temp = a;
	a = b;
	b = temp;
}

int getNum(vector<int> &tar){
	int ret = tar[0];
	for(int i = 1; i < tar.size(); i++) ret = ret*10+tar[i];
	return ret;
}

int getMaxN(int size){
	if(size <= 1 || (size == 2 && N % 10 == 0)) return -1;
	queue<pair<int, vector<int> > > q;
	q.push({0, num});
	check[{0,q.front().second}] = 0;
	int ret = 0;
	while(!q.empty()){
		vector<int> now = q.front().second;
		int cnt = q.front().first;
		q.pop();
		int &_check = check[{cnt,now}];
		if(_check < cnt) continue;
		if(cnt == K){
			ret = max(ret, getNum(now));
			continue;
		}
		for(int i = 0; i < size; i++){
			for(int j = i+1; j < size; j++){
				if(i == 0 && now[j] == 0) continue;
				swap(now[i], now[j]);
				int &_check = check[{cnt+1, now}];
				if(_check == 0 || _check > cnt+1) {
					_check = cnt+1;
					q.push({cnt+1, now});
				}
				swap(now[i], now[j]);
			}
		}
	}
	return ret;
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> N >> K;
	pushNum(N);
	cout << getMaxN(num.size()) << "\n";
	return 0;
}
