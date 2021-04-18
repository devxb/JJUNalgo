//
// xb205
// 2021.04.18
// 1087 쥐 잡기
//

#include <iostream>
#include <algorithm>
#include <cmath>

using namespace std;
typedef pair<double,double> pdd;
int N;
pdd pos[55];
pdd speed[55];

double abs(double num1, double num2){
	return max(num1-num2, num2-num1);
}

pair<pdd,pdd> getPos(int idx1, int idx2, double sec){
	return {
		{pos[idx1].first + speed[idx1].first*sec, pos[idx1].second + speed[idx1].second*sec},
		{pos[idx2].first + speed[idx2].first*sec, pos[idx2].second + speed[idx2].second*sec}
		};
}

double getFenceSize(double sec){
	double ret = 0;
	for(int idx1 = 0; idx1 < N; idx1++){
		for(int idx2 = idx1+1; idx2 < N; idx2++){
			pair<pdd,pdd> pos = getPos(idx1, idx2, sec);
			ret = max(ret, max(abs(pos.first.first,pos.second.first),abs(pos.first.second,pos.second.second)));
		}
	}
	return ret;
}

double getFence(){
	double l = 0, r = 5000, p1, p2;
	for(int i = 0; i < 100; i++){
		p1 = (2.0*l+r)/3.0;
		p2 = (l+2.0*r)/3.0;
		double _dis1 = getFenceSize(p1), _dis2 = getFenceSize(p2);
		if(_dis1 <= _dis2) r = p2;
		else l = p1;
	}
	return getFenceSize(l);
}

int main(){
	ios::sync_with_stdio(0);
	cout.tie(0);
	cin.tie(0);
	cin >> N;
	for(int i = 0; i < N; i++) cin >> pos[i].first >> pos[i].second >> speed[i].first >> speed[i].second;
	cout << fixed;
	cout.precision(10);
	cout << getFence() << "\n";
	return 0;
}
