#include <iostream>
#include <string>
#include <algorithm>
#include <cmath>
#include <vector>

using namespace std;

int n, k;
vector<string> results;

void getComb(int num, string comb){
    if(num == n){
        results.push_back(comb.substr(1, comb.size()));
        return;
    }
    if(num > n) return;
    for(int i = 1; i <= 3; i++){
        int nextNum = num + i;
        if(nextNum > n) continue;
        getComb(nextNum, comb + "+" + to_string(i));
    }
}

void input(){
    cin >> n >> k;
}

int main(void){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    input();
    getComb(0, "");
    sort(results.begin(), results.end());
    if(results.size() <= k-1) cout << -1 << "\n";
    else cout << results[k-1] << "\n";
}
