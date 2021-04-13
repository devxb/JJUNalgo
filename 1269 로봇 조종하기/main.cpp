//
// xb205
// 2021.04.13
// 2169 로봇 조종하기
//

#include <iostream>
#include <algorithm>

using namespace std;

int N, M, arr[1005][1005], dp[1005][1005][2];

int calcDp(){
	dp[1][1][0] = dp[1][1][1] = arr[1][1];
	for(int x = 2; x <= M; x++) 
		for(int dir = 0; dir <= 1; dir++) dp[1][x][dir] = dp[1][x-1][dir] + arr[1][x];
	
	for(int y = 2; y <= N; y++){
		dp[y][1][0] = max(dp[y-1][1][0],dp[y-1][1][1]) + arr[y][1];
		for(int x = 2; x <= M; x++)
			dp[y][x][0] = max(dp[y][x-1][0],max(dp[y-1][x][0],dp[y-1][x][1])) + arr[y][x];
		dp[y][M][1] = max(dp[y-1][M][0],dp[y-1][M][1]) + arr[y][M];
		for(int x = M-1; x >= 1; x--)
			dp[y][x][1] = max(dp[y][x+1][1],max(dp[y-1][x][0],dp[y-1][x][1])) + arr[y][x];
	}
	return max(dp[N][M][0],dp[N][M][1]);
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> N >> M;
	for(int i = 1; i <= N; i++){
		for(int j = 1; j <= M; j++){
			cin >> arr[i][j];
		}
	}
	cout << calcDp() << "\n";
	return 0;
}
