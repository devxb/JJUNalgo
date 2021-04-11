//
// xb205
// 2021.04.11
// 가장 긴 증가하는 부분 수열 2
//

#include <iostream>
#include <utility>
#include <vector>
#include <algorithm>

using namespace std;

int N;
vector<int> lis;

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> N;
	for(int i = 1; i <= N; i++){
		int num;
		cin >> num;
		if(lis.size() == 0 || lis[lis.size()-1] < num) lis.push_back(num);
		else {
			int idx = lower_bound(lis.begin(),lis.end(),num) - lis.begin();
			lis[idx] = min(lis[idx],num);
		}
	}
	cout << lis.size() << "\n";
	return 0;
}
