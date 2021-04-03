//
// xb205
// 2021.04.03
// 1474
// 

#include <iostream>
#include <algorithm>
#include <string>
#include <vector>

using namespace std;
int N, M, words, divi, res;
vector<string> dict;

string getstr(int idx){
	if(idx >= N) return "\n";
	string hypo = "";
	for(int i = 1; i <= divi; i++){
		hypo += '_';
	}
	if((dict[idx][0] > '_' && res > 0) || N - idx <= res){
		res--;
		hypo += '_';
	}
	return hypo + dict[idx] + getstr(idx+1);
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> N >> M;
	for(int i = 1; i <= N; i++){
		string str;
		cin >> str;
		dict.push_back(str);
		words += str.size();
	}
	divi = (M-words) / (N-1);
	res = (M-words) % (N-1);
	cout << dict[0]+getstr(1);
	return 0;
}
