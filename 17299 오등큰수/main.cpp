//
// xb205
// 2021.04.09
// 17299 오등큰수
//

#include <iostream>
#include <algorithm>
#include <queue>
#include <utility>

using namespace std;

int N,A[1000005];
vector<pair<int,int> > NGF;
priority_queue<pair<int,int>, vector<pair<int,int> >, greater<pair<int,int> > > cand;
vector<int> pr;
void getNGF(){
	for(int i = N-1; i >= 0; i--){
		int cnt = NGF[i].first;
		int idx = NGF[i].second;
		while(cand.size() > 0 && cand.top().first <= cnt) cand.pop();
		if(cand.size() > 0) pr.push_back(cand.top().second);
		cand.push({cnt,idx});
		if(cand.size() == 1) pr.push_back(-1);
	}
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> N;
	for(int i = 0; i < N; i++){
		int num;
		cin >> num;
		NGF.push_back({0,num});
		A[num]++;
	}
	for(int i = 0; i < N; i++){
		NGF[i].first = A[NGF[i].second];
	}
	getNGF();
	for(int i = pr.size()-1; i >= 0; i--) cout << pr[i] << " ";
	return 0;
}
