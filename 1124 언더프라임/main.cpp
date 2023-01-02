#include <iostream>
#include <string>
#include <algorithm>
#include <cmath>
#include <vector>

using namespace std;

int A, B;
int isUnderPrime[100001];
bool underPrimeVisited[100001];
bool isPrime[100001];
bool visited[100001];
vector<int> primes;

void markUnderPrime(int num, int cnt){
    if(underPrimeVisited[num]) return;
    underPrimeVisited[num] = true;
    if(isPrime[cnt]) isUnderPrime[num] = 1;
    else isUnderPrime[num] = -1;
    for(int i = 0; i < primes.size(); i++){
        int nextNum = num * primes[i];
        if(nextNum > B) break;
        markUnderPrime(nextNum, cnt+1);
    }
}

int calcUnderPrime(int num){
    int ordinaryNum = num;
    int cnt = 0;
    while (num > 1){
        int divi = 1;
        for(int i = 0; i < primes.size(); i++){
            if(num % primes[i] == 0){
                divi = primes[i];
                break;
            }
        }
        cnt++;
        num /= divi;
    }
    if(isPrime[cnt]) isUnderPrime[ordinaryNum] = 1;
    else isUnderPrime[ordinaryNum] = -1;
    underPrimeVisited[ordinaryNum] = true;
    for(int i = 0; i < primes.size(); i++){
        int nextNum = ordinaryNum * primes[i];
        if(nextNum > B) break;
        markUnderPrime(nextNum, cnt+1);
    }
    return isUnderPrime[ordinaryNum];
}

int getUnderPrimeCount(){
    int ans = 0;
    for(int i = A; i <= B; i++){
        if(isUnderPrime[i] == 1){
            ans++;
            continue;
        }
        if(isUnderPrime[i] == -1) continue;
        if(calcUnderPrime(i) == 1) ans++;
    }
    return ans;
}

void initPrimes(int num){
    if(num > 100000) return;
    if(visited[num]) {
        initPrimes(num+1);
        return;
    }
    isPrime[num] = true;
    primes.push_back(num);
    for(int i = num; i <= 100000; i += num) visited[i] = true;
    initPrimes(num+1);
}

void input(){
    cin >> A >> B;
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    initPrimes(2);
    input();
    cout << getUnderPrimeCount() << "\n";
}
