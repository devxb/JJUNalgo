//
// xb205
// 2021.04.12
// 2560 짚신벌레
//

#include <iostream>
#include <algorithm>

using namespace std;

int getIns(int a, int b, int d, int N){
	int ins[1000005] = {1, 0}, mod = 1000;
	for(int day = 1; day <= N; day++){
		if(day - a < 0){
			ins[day] = ins[day-1];
			continue;
		}
		ins[day] = ins[day-a]%mod+ins[day-1]%mod;
		if(day-b >= 0) ins[day] = ins[day]%mod-ins[day-b]%mod+mod;
		ins[day] %= mod;
	}
	return (ins[N]%mod-ins[N-d]%mod+mod)%mod;
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	int a,b,d,N;
	cin >> a >> b >> d >> N;
	cout << getIns(a,b,d,N) << "\n";
	return 0;
}
