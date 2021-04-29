//
// xb205
// 2021.4.29
// 1114 통나무 자르기
//

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int L, K, C, cut;
vector<int> cuttingPoints;

int cuttingWoods(int cuttingLength){
	int lastCuttingLength = L, cuttingCnt = 0, minimumCuttingPoint = 0;
	for(int i = K-1; i >= 0; i--){
		if(lastCuttingLength - cuttingPoints[i] > cuttingLength){
			if(i == K-1) return 0;
			lastCuttingLength = cuttingPoints[i+1];
			if(lastCuttingLength - cuttingPoints[i] > cuttingLength) return 0;
			cuttingCnt++;
			minimumCuttingPoint = cuttingPoints[i+1];
		}
		if(cuttingCnt >= C) break;
	}
	if(cuttingCnt < C){
		minimumCuttingPoint = cuttingPoints[0];
	}
	if(minimumCuttingPoint > cuttingLength) return 0;
	return minimumCuttingPoint;
}

int getWoodLength(){
	int l = 1, r = L, ret = 0;
	for(int i = 0; i < 30; i++){
		int minimumCuttingLength = (l + r) / 2;
		int minimumCuttingPoint = cuttingWoods(minimumCuttingLength);
		if(!minimumCuttingPoint) l = minimumCuttingLength + 1;
		else{
			ret = minimumCuttingLength;
			cut = minimumCuttingPoint;
			r = minimumCuttingLength - 1;
		}
		
	}
	return ret;
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> L >> K >> C;
	int cuttingPos;
	for(int i = 1; i <= K; i++){
		cin >> cuttingPos;
		cuttingPoints.push_back(cuttingPos);
	}
	sort(cuttingPoints.begin(),cuttingPoints.end());
	cout << getWoodLength() << " " << cut << "\n";
	return 0;
}
