//
// xb205
// 2021.04.06
// 8980 택배
//

#include <iostream>
#include <algorithm>
#include <utility>
#include <vector>

using namespace std;

int N, C, M, stor[2005];
vector<pair<pair<int,int>,int> > vec;

int getBoxes(){
	int ret = 0;
	for(int i = 0; i < vec.size(); i++){
		int sta = vec[i].first.second, des = vec[i].first.first, boxes = vec[i].second;
		int get = boxes;
		for(int j = sta; j < des; j++) get = min(get, stor[j]);
		for(int j = sta; j < des; j++) stor[j] -= get;
		ret += get;
	}
	return ret;
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> N >> C >> M;
	for(int i = 1; i <= N; i++) stor[i] = C;
	for(int i = 1; i <= M; i++){
		int from, to, boxes;
		cin >> from >> to >> boxes;
		vec.push_back({{to,from},boxes});
	}
	sort(vec.begin(),vec.end());
	cout << getBoxes() << "\n";
	return 0;
}
