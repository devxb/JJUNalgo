//
// xb205
// 2021.04.20
// 14501 퇴사
//

#include <iostream>
#include <utility>

using namespace std;

int N, ans;
pair<int,int> arr[20];
int dp[20];

void getProfit(int idx){
	if(idx > N) return;
	for(int i = idx-1; i >= 1; i--){
		int due = i+arr[i].first-1;
		if(due >= idx) continue;
		dp[idx] = max(dp[idx],dp[i]);
	}
	dp[idx] += arr[idx].second;
	if(idx+arr[idx].first-1 > N) dp[idx] -= arr[idx].second;
	ans = max(dp[idx],ans);
	getProfit(idx+1);
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> N;
	for(int i = 1; i <= N; i++) cin >> arr[i].first >> arr[i].second;
	getProfit(1);
	cout << ans << "\n";
	return 0;
}
