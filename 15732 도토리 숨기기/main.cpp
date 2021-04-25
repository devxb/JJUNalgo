//
// xb205
// 2021.04.24
// 15732 도토리 숨기기
//

#include <iostream>
#include <vector>
#include <algorithm>
#include <utility>

using namespace std;

int N, K, D;
vector<pair<int,pair<int,int> > > vec;

bool getAcorn(int boxIdx){
	long long ret = 0;
	for(int i = 0; i < K; i++)
		if(vec[i].second.first <= boxIdx) 
			ret += 1 + ((min(vec[i].second.second,boxIdx) - vec[i].second.first) / vec[i].first);
	return ret >= D;
}

int getDecision(){
	int left = 1, right = N, mid;
	for(int i = 0; i < 20; i++){
		mid = (left + right)/2;
		if(getAcorn(mid)) right = mid-1;
		else left = mid+1;
	}
	return left;
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> N >> K >> D;
	for(int i = 1; i <= K; i++){
		int left, right, range;
		cin >> left >> right >> range;
		vec.push_back({range, {left,right}});
	}
	cout << getDecision() << "\n";
	return 0;
}
