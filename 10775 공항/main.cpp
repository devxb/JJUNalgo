//
// xb205
// 2021.04.10
// 10775 공항
//

#include <iostream>

using namespace std;

int par[100005];
int G,P, cnt;

int uni(int idx){
	if(par[idx] == idx) return idx;
	return par[idx] = uni(par[idx]);
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> G >> P;
	for(int i = 1; i <= G; i++) par[i] = i;
	for(int i = 1; i <= P; i++){
		int gi;
		cin >> gi;
		int doc = par[gi] = uni(gi);
		if(doc == 0) break;
		cnt++;
		par[doc] = uni(par[doc-1]);
	}
	cout << cnt << "\n";
	return 0;
}
