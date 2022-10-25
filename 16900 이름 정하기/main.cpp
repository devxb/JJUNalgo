#include <iostream>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

string S;
long long K;
long long pi[500000];

void initPi(){
    int p = 0;
    for(int i = 1; i < S.length(); i++){
        while(S[i] != S[p] && p > 0) p = pi[p-1];
        if(S[p] == S[i]) pi[i] = ++p;
    }
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> S >> K;
    initPi();
    long long maxPiNum = 0;
    for(int i = S.length()-1; i >= 0; i--){ 
        if(pi[i] == 0) break;
        maxPiNum = max(maxPiNum, pi[i]);
    }
    cout << ((long long)S.length())*((long long)K) - ((long long)maxPiNum)*((long long)(K-1)) << "\n";
}
