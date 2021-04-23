//
// xb205
// 2021.04.23
// 1305 광고
//

#include <iostream>
#include <utility>
#include <algorithm>
#include <string>
using namespace std;

int getPi(string &str){
	int size = str.size();
	int pi[size+5] = {0, };
	int p = 0, ret = 0;
	for(int i = 1; i < size; i++){
		while(str[i] != str[p] && p > 0) p = pi[p-1];
		if(str[p] == str[i]) pi[i] = ++p;
	}
	return max(size-pi[size-1],1);
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	int L;
	string str;
	cin >> L >> str;
	cout << getPi(str) << "\n";
	return 0;
}
