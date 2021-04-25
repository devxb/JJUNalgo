//
// xb205
// 2021.04.25
// 5430 AC
//

#include <iostream>
#include <string>

using namespace std;

int T;

void revPrint(int l, int r, string &intArray){
	int printing = r;
	for(int i = r; i >= l; i--){
		if(intArray[i] == ','){
			int j = i+1;
			while(intArray[j] != ',' && j <= r) {
				cout << intArray[j];
				j++;
			}
			cout << ",";
			printing = i-1;
		}
	}
	for(int i = l; i <= printing; i++){
		cout << intArray[i];
	}
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> T;
	for(int tc = 1; tc <= T; tc++){
		string func, intArray;
		int n;
		cin >> func >> n >> intArray;
		bool rev = false;
		int delL = 0, delR = 0;
		for(int j = 0; j < func.size(); j++){
			if(func[j] == 'R'){
				if(rev) rev = false;
				else rev = true;
			}
			if(func[j] == 'D'){
				if(rev) delR++;
				if(!rev) delL++;
			}
		}
		int l = 1, r = intArray.size()-2;
		while(delL > 0 && l <= r){
			delL--;
			while(intArray[l] != ',' && l < r) l++;
			l++;
		}
		while(delR > 0 && r >= l){
			delR--;
			while(intArray[r] != ',' && r > l) r--;
			r--;
		}
		if(delL > 0 || delR > 0) cout << "error\n";
		else if(!rev){
			cout << "[";
			for(int i = l; i <= r; i++) cout << intArray[i];
			cout << "]\n";
			continue;
		}
		else if(rev){
			cout << "[";
				revPrint(l, r, intArray);
			cout << "]\n";
		}
	}
	return 0;
}
