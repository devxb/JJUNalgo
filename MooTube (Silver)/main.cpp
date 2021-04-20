//
// xb205
// 2021.4.20
// 15591 MooTube (Silver)
//

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N, Q;
bool watched[5005];
vector<vector<pair<int,int> > > vec(5005);

int getUsado(int idx, int usado, int K){
	int ret = 1;
	watched[idx] = K%2;
	for(int i = 0; i < vec[idx].size(); i++){
		int _idx = vec[idx][i].second;
		int _w = vec[idx][i].first;
		if(_w >= usado && watched[_idx] != K%2) ret += getUsado(_idx, usado, K);
	}
	return ret;
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> N >> Q;
	for(int i = 0; i < N-1; i++){
		int from,to,w;
		cin >> from >> to >> w;
		vec[from].push_back({w,to});
		vec[to].push_back({w,from});
	}
	for(int i = 1; i <= Q; i++){
		for(int j = 0; j <= N; j++) watched[j] = (i+1)%2;
		int k,v;
		cin >> k >> v;
		cout << getUsado(v, k, i)-1 << "\n";
	}
	return 0;
}
