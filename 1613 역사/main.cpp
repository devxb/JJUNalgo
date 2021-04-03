//
// xb205
// 2021.04.03
// 1613 역사
//

#include <iostream>
#include <utility>
#include <algorithm>

using namespace std;
int n, k, s;
bool path[405][405];
int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> n >> k;
	for(int i = 1; i <= k; i++){
		int fir, sec;
		cin >> fir >> sec;
		path[fir][sec] = true;
	}
	for(int i = 1; i <= n; i++) path[i][i] = true;
	for(int mid = 1; mid <= n; mid++){
		for(int from = 1; from <= n; from++){
			for(int to = 1; to <= n; to++){
				if(path[from][mid] && path[mid][to]) path[from][to] = true;
			}
		}
	}
	cin >> s;
	for(int i = 1; i <= s; i++){
		int fir, sec;
		cin >> fir >> sec;
		if(path[fir][sec]) cout << -1 << "\n";
		else if(path[sec][fir]) cout << 1 << "\n";
		else cout << 0 << "\n";
	}
	return 0;
}
