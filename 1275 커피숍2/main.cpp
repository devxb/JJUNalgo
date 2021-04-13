//
// xb205
// 2021.04.13 
// 1275 커피숍2
//

#include <iostream>
#include <algorithm>

typedef long long int ll;
using namespace std;

int N, Q;
ll seg[100000*4+5];

ll setSeg(int num, int t, int idx, int left, int right){
	if(left == right && left == t) return seg[idx] = num;
	if(left > t || right < t) return seg[idx];
	int mid = (left + right) / 2;
	return seg[idx] = setSeg(num, t, idx*2, left, mid) + setSeg(num, t, idx*2+1, mid+1, right);
}

ll getSeg(int idx, int left, int right, int tl, int tr){
	if(tr < left || tl > right) return 0;
	if(tl <= left && tr >= right) return seg[idx];
	int mid = (left + right) / 2;
	return getSeg(idx*2, left, mid, tl, tr) + getSeg(idx*2+1, mid+1, right, tl, tr);
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> N >> Q;
	for(int i = 1; i <= N; i++){
		int num;
		cin >> num;
		setSeg(num, i, 1, 1, N);
	}
	for(int i = 1; i <= Q; i++){
		int x,y,a,b;
		cin >> x >> y >> a >> b;
		cout << getSeg(1, 1, N, min(x,y), max(x,y)) << "\n";
		setSeg(b, a, 1, 1, N);
	}
	return 0;
}
