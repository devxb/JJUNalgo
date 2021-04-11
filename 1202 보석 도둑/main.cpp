//
// xb205
// 1202 보석도둑
// 2021.04.10
//

#include <iostream>
#include <vector>
#include <utility>
#include <algorithm>
#include <map>

typedef long long ll;
using namespace std;

int N, K;
vector<pair<int,int> > vec;
vector<int> bag;
map<int,int> uni;
map<int,int> bagCnt;

int unionFind(int idx){
	int &u = uni[idx];
	if(u == idx) return idx;
	return u = unionFind(u);
}

ll getSteal(){
	ll ret = 0;
	for(int i = vec.size()-1; i >= 0; i--){
		int value = vec[i].first;
		int weight = unionFind(vec[i].second);
		if(K == 0) break;
		while(bagCnt[weight] == 0){
			int _weight = bag[lower_bound(bag.begin(), bag.end(), weight+1) - bag.begin()];
			if(_weight == 0){
				weight = -1;
				break;
			}
			int &uniWeight = uni[weight];
			uniWeight = unionFind(_weight);
			weight = uniWeight;
		}
		if(weight == -1) continue;
		bagCnt[weight]--;
		ret += value;
		K--;
	}
	return ret;
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> N >> K;
	for(int i = 0; i < N; i++){
		int weight, value;
		cin >> weight >> value;
		uni[weight] = weight;
		vec.push_back({value,weight});
	}
	for(int i = 0; i < K; i++){
		int size;
		cin >> size;
		uni[size] = size;
		bagCnt[size]++;
		bag.push_back(size);
		
	}
	sort(vec.begin(), vec.end());
	sort(bag.begin(), bag.end());
	cout << getSteal() << "\n";
	return 0;
}
