//
// xb205
// 2021.4.30
// 1756 피자 굽기
//

#include <iostream>
#include <deque>
#include <vector>
#include <utility>

using namespace std;

int D, N;
vector<int> oven;

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> D >> N;
	int pass = 987654321;
	for(int i = 1; i <= D; i++){
		int size;
		cin >> size;
		pass = min(pass,size);
		oven.push_back(pass);
	}
	int bottom = D;
	int lastIdx = 0;
	for(int i = 1; i <= N; i++){
		int dough;
		bool put = false;
		cin >> dough;
		for(int j = bottom-1; j >= 0; j--){
			if(dough <= oven[j]){
				bottom = j;
				lastIdx = bottom;
				put = true;
				break;
			}
		}
		if(!put){
			lastIdx = -1;
			break;
		}
	}
	cout << lastIdx+1 << "\n";
	return 0;
}
