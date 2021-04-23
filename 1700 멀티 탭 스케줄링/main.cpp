//
// xb205
// 2021.04.23          
// 1700 멀티 탭 스케줄링
//

#include <iostream>

using namespace std;

int N, K, arr[105], check[105], tap, change;

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> N >> K;
	for(int i = 1; i <= K; i++) cin >> arr[i];
	for(int i = 1; i <= K; i++){
		int num = arr[i];
		if(check[num] == 1) continue;
		if(tap < N) {
			tap++;
			check[num] = 1;
			continue;
		}
		int plugged[K+5] = {0, };
		for(int j = i+1; j <= K; j++){
			int _num = arr[j];
			if(!check[_num]) continue;
			if(!plugged[_num]) plugged[_num] = j;
			plugged[_num] = min(plugged[_num], j);
		}
		int solv, appear = 0;
		for(int j = 1; j <= K; j++){
			int _num = arr[j];
			if(check[_num] && !plugged[_num]){
				solv = _num;
				break;
			}
			if(appear < plugged[_num]){
				solv = _num;
				appear = plugged[_num];
			}
		}
		check[solv] = 0;
		check[num] = 1;
		change++;
	}
	cout << change << "\n";
	return 0;
}
