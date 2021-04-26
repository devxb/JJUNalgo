//
// xb205
// 2021.04.26
// 1747 소수&팰린드롬
//

#include <iostream>
#include <utility>
#include <string>

using namespace std;

int N;
bool check[2000005];

void setEratos(int i, int f){
	for(int j = i; j <= 1003001; j += f){
		check[j] = true;
	}
}

bool checkPalindrome(string target){
	int size = target.size();
	for(int i = 0; i < size/2; i++){
		if(target[i] != target[size-(i+1)]) return 0;
	}
	return 1;
}

string intToString(int num){
	if(num == 0) return "";
	return intToString(num/10)+char(num%10 + '0');
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> N;
	for(int i = 2; i != 0; i++){
		if(!check[i]){
			setEratos(i, i);
			if(i >= N && checkPalindrome(intToString(i))){
				cout << i << "\n";
				break;
			}
		}
	}
	return 0;
}
