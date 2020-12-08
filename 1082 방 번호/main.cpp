//
//  main.cpp
//  1082 방 번호
//
//  Created by 이준영 on 2020/12/08.
//

#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

int N,money;
int price[55];
string dp[55];
string ArrayNum[] = {"0", "1", "2", "3", "4" ,"5", "6" ,"7", "8", "9", "10"};

string maxstr(string a, string b){
    if(a.length() > b.length()){
        return a;
    }
    else if(a.length() < b.length()){
        return b;
    }
    else{
        for(int i = 0; i < a.length(); i++){
            if(int(a[i]) - 48 > int(b[i]) - 48){
                return a;
            }
            else if(int(a[i]) - 48 < int(b[i]) - 48){
                return b;
            }
        }
    }
    return a;
}

void go(int nMoney){
    if(nMoney > money){
        return;
    }
    string M = "0";
    for(int i = N-1; i > 0; i--){
        if(nMoney < price[i]){
            continue;
        }
        if(dp[nMoney - price[i]].front() == 'X'){
            M = maxstr(M, ArrayNum[i]);
            continue;
        }
        string num = ArrayNum[i];
        string zero = ArrayNum[i];
        for(int j = 0; j < (nMoney - price[i]) / price[0]; j++){
            zero = zero + '0';
        }
        for(int j = 0; j < dp[nMoney - price[i]].length(); j++){
            num = num + dp[nMoney - price[i]][j];
        }
        if(nMoney == 1){
        }
        M = maxstr(M, maxstr(num,zero));
    }
    dp[nMoney] = M;
    go(nMoney+1);
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    int fMoney = 51;
    for(int i = 0; i < N; i++){
        cin >> price[i];
        fMoney = min(fMoney,price[i]);
    }
    cin >> money;
    for(int i = 0; i < fMoney; i++){
        dp[i] = "X";
    }
    go(fMoney);
    cout << dp[money] << "\n";
    return 0;
}
