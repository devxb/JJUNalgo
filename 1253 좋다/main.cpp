// 
// xb205
// 2021.04.11
// 1253 좋다
//

#include <iostream>
#include <algorithm>
#include <vector>
#include <map>
#include <utility>

using namespace std;
int N;
vector<int> vec;
map<int,int> numCnt;
map<int,pair<int,int>> numRan;

bool Good(int targetNum){
	return (vec[lower_bound(vec.begin(), vec.end(), targetNum) - vec.begin()] == targetNum);
}

int getGood(){
	int ret = 0;
	for(int i = N-1; i >= 0; i--){
		int num = vec[i];
		pair<int,int> &ran = numRan[num];
		int ranCnt = ran.second - ran.first + 1;
		for(int j = N-1; j >= 0; j--){
			if(i == j) continue;
			if(num == 0 && vec[j] == 0){
				if(ranCnt > 2){
					ret += ranCnt;
					break;
				}
				continue;
			}
			if(vec[j] == 0){
				if(ranCnt > 1) {
					ret += ranCnt;
					break;
				}
				continue;
			}
			if(num-vec[j] == vec[j]){
				pair<int,int> &_ran = numRan[vec[j]];
				int _ranCnt = _ran.second - _ran.first + 1;
				if(_ranCnt > 1){
					ret += ranCnt;
					break;
				}
				continue;
			}
			if(num == vec[j]){
				pair<int,int> &zeroRan = numRan[0];
				if(zeroRan.first != 0){
					ret += ranCnt;
					break;
				}
				continue;
			}
			if(Good(num-vec[j])) {
				ret += ranCnt;
				break;
			}
		}
		i = ran.first;
	}
	return ret;
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> N;
	for(int i = 0; i < N; i++){
		int num;
		cin >> num;
		vec.push_back(num);
		numCnt[num]++;
		numRan[num] = {-1,-1};
	}
	sort(vec.begin(), vec.end());
	for(int i = 0; i < N; i++){
		pair<int,int> &ran = numRan[vec[i]];
		if(ran.first == -1) ran.first = i;
		ran.second = i;
	}
	cout << getGood() << "\n";
	return 0;
}
