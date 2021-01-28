//
// xb205
// 2021.01.28
//

#include <iostream>
#include <algorithm>
#include <vector>
#include <utility>

using namespace std;
int N, arr[1000005], par[1000005], INF=-1000000005;
vector<pair<int,int> > dp;

pair<int,int> getidx(int num){
    pair<int,int> idx = {0,1};
    int left = 0, right = dp.size()-1;
    while(true){
        int mid = (left + right) / 2;
        idx = {mid, dp[mid].second};
        if(left > right){
            break;
        }
        if(dp[mid].first > num){
            right = mid-1;
        }
        else{
            left = mid+1;
        }
    }
    while(dp[idx.first].first < num){
        idx = {idx.first+1, dp[idx.first+1].second};
    }
    return idx;
}

void setdp(){
    for(int idx = 2; idx <= N; idx++){
        if(dp.back().first < arr[idx]){
            dp.push_back({arr[idx],idx});
            par[idx] = dp[dp.size()-2].second;
        }
        else{
            pair<int,int> _idx = getidx(arr[idx]);
            dp[_idx.first] = {arr[idx], idx};
            if(_idx.first > 0 and dp[_idx.first-1].second < idx){
                par[idx] = dp[_idx.first-1].second;
            }
        }
    }
}

void getpar(int idx){
    if(par[idx] == INF){
        cout << arr[idx] << " ";
        return;
    }
    getpar(par[idx]);
    cout << arr[idx] << " ";
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    for(int i = 1; i <= N; i++){
        cin >> arr[i];
        par[i] = INF;
    }
    dp.push_back({arr[1],1});
    setdp();
    cout << dp.size() << "\n";
    getpar(dp.back().second);
    return 0;
}
