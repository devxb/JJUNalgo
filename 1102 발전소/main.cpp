//
// xb205
// 2021.04.14
// 1102 발전소
//

#include <iostream>
#include <algorithm>
#include <string>
#include <bitset>
using namespace std;

int N, P, arr[20][20], dp[1 << 20], INF = 987654321;
bool check[1 << 20];

int getPower(int cnt, int on){
	if(cnt == 0) return INF;
	if(dp[on] != INF) return dp[on];
	for(int i = 1; i <= N; i++){
		if((on & (1 << i)) == 0) continue;
		int sum = INF;
		for(int j = 1; j <= N; j++){
			if((on & (1 << j)) == 0 || i == j) continue;
			sum = min(sum, arr[j][i]);
		}
		dp[on] = min(dp[on], getPower(cnt-1, on&(~(1 << i)))+sum);
	}
	return dp[on];
}

void setCheck(int cnt, int on, int idx){
	int ret = INF;
	if(!check[on]){
		dp[on] = ret;
		check[on] = 1;
	}
	for(int i = idx; i <= N; i++){
		if((on & (1 << i)) == 0) 
			setCheck(cnt+1, on | (1 << i), i+1);
	}
}

int getComb(int cnt, int on, int idx){
	int ret = INF;
	if(cnt == P){
		return getPower(cnt, on);
	}
	for(int i = idx; i <= N; i++){
		if((on & (1 << i)) == 0) 
			ret = min(ret, getComb(cnt+1, on | (1 << i), i+1));
	}
	if(cnt == 0 && ret == INF) ret = -1;
	return ret;
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> N;
	setCheck(0,0,1);
	for(int y = 1; y <= N; y++)
		for(int x = 1; x <= N; x++)
			cin >> arr[y][x];
	string str;
	cin >> str;
	int power = 0, cnt = 0;
	bool c = false;
	for(int i = 0; i < str.size(); i++){
		if(str[i] == 'Y') {
			c = true;
			power |= (1 << (i+1));
			cnt++;
		}
	}
	for(int i = 0; i <= N; i++){
		dp[1 << i] = 123456789;
	}
	dp[power] = 0;
	check[power] = 1;
	cin >> P;
	if(!c && P != 0) cout << -1 << "\n";
	else if(P == 0 || P <= cnt) cout << 0 << "\n";
	else cout << getComb(0, 0, 1) << "\n";
	return 0;
}
