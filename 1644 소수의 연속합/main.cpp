//
//  main.cpp
//  1644 소수의 연속합
//
//  Created by 이준영 on 2021/01/04.
//

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N, primeCheck[4000005], cnt;
vector<int> prime;

void makePrime(){
    for(int idx = 2; idx <= 4000000; idx++){
        if(primeCheck[idx] == 0){
            prime.push_back(idx);
            for(int i = idx; i <= 4000000; i = i + idx){
                primeCheck[i] = 1;
            }
        }
    }
}

void go(){
    int lp = 0, rp = 0, num = prime[0];
    while(lp <= prime.size() and rp <= prime.size()){
        if(num > N){
            num -= prime[lp];
            lp++;
            continue;
        }
        else if(num == N){
            cnt++;
        }
        if(num <= N){
            rp++;
            num += prime[rp];
            continue;
        }
    }
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    makePrime();
    go();
    cout << cnt << "\n";
    return 0;
}
