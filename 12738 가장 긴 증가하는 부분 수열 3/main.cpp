//
// xb205
// 2021.01.28
//

#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;
int N, arr[1000005];
vector<int> dp;

int getidx(int num){
    return lower_bound(dp.begin(),dp.end(),num) - dp.begin();
}

int getdp(int idx){
    if(idx > N){
        return dp.size();
    }
    if(dp.back() < arr[idx]){
        dp.push_back(arr[idx]);
    }
    else{
        dp[getidx(arr[idx])] = arr[idx];
    }
    return getdp(idx+1);
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    for(int i = 1; i <= N; i++){
        cin >> arr[i];
    }
    dp.push_back(arr[1]);
    cout << getdp(2) << "\n";
    return 0;
}
