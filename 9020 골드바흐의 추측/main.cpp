#include <iostream>
#include <string>
#include <algorithm>
#include <cmath>
#include <vector>

using namespace std;

int T;
bool visited[100000];
vector<int> primes;
bool isPrime[100000];

void printGoldBah(int num){
    int left = 0, right = 987654321;
    for(int prime : primes){
        if(prime >= num) break;
        if(!isPrime[num - prime]) continue;
        if(right - left > abs(prime - (num-prime))){
            left = min(prime, num - prime);
            right = max(prime, num - prime);
        }
    }
    cout << left << " " << right << "\n";
}

void initPrime(){
    for(int i = 2; i < 100000; i++){
        if(visited[i]) continue;
        isPrime[i] = true;
        primes.push_back(i);
        for(int j = i; j < 100000; j += i) visited[j] = true;
    }
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    initPrime();
    cin >> T;
    for(int i = 0; i < T; i++){
        int num;
        cin >> num;
        printGoldBah(num);
    }
}
