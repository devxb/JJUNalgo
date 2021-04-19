//
// xb205
// 2021.4.19
// K번째 수
//

#include <iostream>
#include <algorithm>

using namespace std;
typedef long long ll;
ll MAX = 1000000000;
ll N, K;

ll getMidNum(ll mid){
	ll ret = 0;
	for(int y = 1; y <= N; y++){
		ret += min(N, mid/y);
	}
	return ret;
}

ll getK(ll K){
	ll ret = 0;
	ll left = 1, right = min(MAX, N*N);
	ll mid = 0;
	for(int i = 1; i <= 32; i++){
		mid = (left + right) / 2;
		ll num = getMidNum(mid);
		if(num < K) left = mid+1;
		else{ 
			right = mid-1;
			ret = mid;
		}
	}
	return ret;
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> N >> K;
	cout << getK(K) << "\n";
	return 0;
}
