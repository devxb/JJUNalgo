//
// xb205
// 2021.04.15
// 13907 세금
//

#include <iostream>
#include <algorithm>
#include <vector>
#include <utility>
#include <algorithm>
#include <queue>

using namespace std;
typedef pair<int,int> pii;

int N, M, K, S, D, INF = 987654321;
vector<vector<pii> > road(1005);
vector<pii> candRoad;
int check[1005][1005];

vector<pii> candFilter(){
	vector<pair<int,int> > ret;
	ret.push_back(candRoad[0]);
	for(int i = 1; i < candRoad.size(); i++){
		if(candRoad[i].second >= candRoad[i-1].second) continue;
		ret.push_back(candRoad[i]);
	}
	return ret;
}

void candQuery(int num){
	for(int i = 0; i < candRoad.size(); i++) candRoad[i].first += candRoad[i].second*num;
	sort(candRoad.begin(),candRoad.end());
	candRoad = candFilter();
	return;
}

int setRoad(){
	int ret = INF;
	for(int i = 1; i <= N; i++)
		for(int j = 1; j <= N; j++) check[i][j] = INF;
	
	priority_queue<pair<pii,int>, vector<pair<pii, int> >, greater<pair<pii,int> > > pq;
	pq.push({{0,0},S});
	check[0][S] = 0;
	while(!pq.empty()){
		int cost = pq.top().first.first;
		int cnt = pq.top().first.second;
		int idx = pq.top().second;
		pq.pop();
		if(check[cnt][idx] < cost || idx == D) continue;
		for(int i = 0; i < road[idx].size(); i++){
			int _idx = road[idx][i].second;
			int _cost = cost + road[idx][i].first;
			if(check[cnt+1][_idx] <= _cost) continue;
			check[cnt+1][_idx] = _cost;
			pq.push({{_cost,cnt+1},_idx});
		}
	}
	
	for(int i = 1; i <= N; i++){
		if(check[i][D] == INF) continue;
		ret = min(ret, check[i][D]);
		candRoad.push_back({check[i][D],i});
	}
	sort(candRoad.begin(), candRoad.end());
	candRoad = candFilter();
	return ret;
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> N >> M >> K;
	cin >> S >> D;
	for(int i = 1; i <= M; i++){
		int from, to, cost;
		cin >> from >> to >> cost;
		road[from].push_back({cost,to});
		road[to].push_back({cost,from});
	}
	cout << setRoad() << "\n";
	for(int i = 1; i <= K; i++){
		int num;
		cin >> num;
		candQuery(num);
		cout << candRoad.front().first << "\n";
	}
	return 0;
}
