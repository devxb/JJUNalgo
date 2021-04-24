//
// xb205
// 2021.04.24
// 1427 소트인사이드
//

#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

string str;
vector<char> vec;

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> str;
	for(int i = 0; i < str.size(); i++) vec.push_back(str[i]);
	sort(vec.begin(),vec.end(),greater());
	for(int i = 0; i < vec.size(); i++) cout << vec[i];
	return 0;
}
