#include <iostream>
#include <string>
#include <algorithm>
#include <cmath>
#include <vector>

using namespace std;

int T;
bool visited[10000000];
vector<int> primes;

int getPrimeCount(int num){
    int ans = 0;
    int left = num, right = num*2;
    for(int prime : primes){
        if(prime > right) break;
        if(prime > left && prime <= right) ans++;
    }
    return ans;
}

void initPrime(){
    for(int i = 2; i < 10000000; i++){
        if(visited[i]) continue;
        primes.push_back(i);
        for(int j = i; j < 10000000; j += i) visited[j] = true;
    }
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    initPrime();
    while(true){
        int num;
        cin >> num;
        if(num == 0) break;
        cout << getPrimeCount(num) << "\n";
    }
}
