//
// xb205
// 2021.04.11
// 10159 저울
//

#include <iostream>
#include <utility>

using namespace std;

int N, M, arr[105][105];

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> N >> M;
	for(int i = 1; i <= N; i++) arr[i][i] = 1;
	for(int i = 1; i <= M; i++){
		int p,s;
		cin >> p >> s;
		arr[p][s] = 1;
	}
	for(int mid = 1; mid <= N; mid++){
		for(int from = 1; from <= N; from++){
			for(int to = 1; to <= N; to++){
				if(arr[from][mid] && arr[mid][to]) arr[from][to] = 1;
			}
		}
	}
	for(int i = 1; i <= N; i++){
		int cnt = 0;
		for(int j = 1; j <= N; j++){
			if(!arr[i][j] && !arr[j][i]) cnt++;
		}
		cout << cnt << "\n";
	}
	return 0;
}
