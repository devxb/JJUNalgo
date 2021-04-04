//
// xb205
// 2021.04.04
// 1248 맞춰봐
//

#include <iostream>
#include <utility>
#include <string>
#include <deque>

using namespace std;

int N;
string str;
int arr[15][15];
deque<int> cand;

bool jud(int num, int i){
	int judNum = num, u = 0;
	for(int jud = cand.size()-1; jud >= 0; jud--){
		num += cand[jud];
		u++;
		if(num == 0 && arr[i-u][i] == 0
		  || num > 0 && arr[i-u][i] > 0
		  || num < 0 && arr[i-u][i] < 0) continue;
		return false;
	}
	return true;
}

void recur(int i){
	if(i > N){
		while(!cand.empty()){
			cout << cand.front() << " ";
			cand.pop_front();
		}
		exit(0);
	}
	int posi = arr[i][i];
	for(int canNum = 0; canNum <= 10; canNum++){
		int _num = canNum*posi;
		if(arr[i][i] != 0 && _num == 0) continue;
		if(jud(_num,i)){
			cand.push_back(_num);
			recur(i+1);
			cand.pop_back();
		}
	}
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> N;
	cin >> str;
	int idx = 0;
	for(int i = 1; i <= N; i++){
		for(int j = i; j <= N; j++){
			int pu;
			if(str[idx] == '+') pu = 1;
			else if(str[idx] == '-') pu = -1;
			else pu = 0;
			arr[i][j] = pu;
			idx++;
		}
	}
	recur(1);
	return 0;
}
