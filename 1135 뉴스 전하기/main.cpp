//
// xb205
// 2921.4.17
// 1135 뉴스 전하기
//

#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int N;
vector<vector<int> > company(55);

int getNews(int boss){
	int ret = 0;
	vector<int> call;
	for(int i = 0; i < company[boss].size(); i++) call.push_back(getNews(company[boss][i]));
	sort(call.begin(),call.end(),greater<>());
	for(int i = 0; i < call.size(); i++){
		ret = max(ret, call[i]+i+1);
	}
	return ret;
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> N;
	for(int i = 1; i <= N; i++){
		int boss;
		cin >> boss;
		company[boss+1].push_back(i);
	}
	cout << getNews(0)-1 << "\n";
	return 0;
}
