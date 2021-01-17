//
//  main.cpp
//  9251 LCS
//
//  Created by 이준영 on 2021/01/17.
//

#include <iostream>
#include <string>
#include <algorithm>
using namespace std;
string str1, str2;
int dp[1005][1005];
void go(int idx){
    if(idx > str2.size()){
        return;
    }
    for(int i = 1; i <= str1.size(); i++){
        int num = 0;
        if(str1[i-1] == str2[idx-1]){
            num = 1;
        }
        dp[idx][i] = max(dp[idx-1][i], max(dp[idx][i-1], dp[idx-1][i-1]+num));
        if(dp[idx][i] > i or dp[idx][i] > idx){
            dp[idx][i] = min(i, idx);
        }
    }
    go(idx+1);
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> str1 >> str2;
    go(1);
    cout << dp[str2.size()][str1.size()] << "\n";
    return 0;
}
