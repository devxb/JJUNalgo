//
// xb205
// 2021.04.04
// 1256 사전
//

#include <iostream>
#include <algorithm>
#include <string>

typedef long long ll;
using namespace std;

int N,M,K;
int comb[215][215];

void setComb(){
	int INF = 1000000000;
	comb[0][0] = 1;
	for(int i = 1; i <= N+M+5; i++){
		comb[i][0] = 1;
		for(int j = 1; j <= i; j++){
			comb[i][j] = min(INF, comb[i-1][j] + comb[i-1][j-1]);
		}
	}
}

string getDic(int N, int M, int K){
	if(N == 0 || M == 0){
		string rest = "";
		while(N > 0 || M > 0){
			if(N > 0) rest += "a";
			else rest += "z";
			N--;
			M--;
		}
		return rest + "\n";
	}
	int geta = comb[N+M-1][N-1];
	int getz = comb[N+M-1][M-1];
	if(K > geta) return "z"+getDic(N,M-1,K-geta);
	return "a"+getDic(N-1,M,K);
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> N >> M >> K;
	setComb();
	if(comb[N+M][N] < K) cout << -1 << "\n";
	else cout << getDic(N,M,K);
	return 0;
}
