#include <iostream>
#include <string>
#include <algorithm>
#include <cmath>
#include <vector>

using namespace std;

int K;
bool visited[10000000];

int findPrime(){
    int finded = 0;
    for(int i = 2; i < 10000000; i++){
        if(visited[i]) continue;
        finded++;
        if(finded == K) return i;
        for(int j = i; j < 10000000; j += i) visited[j] = true;
    }
    return -1;
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> K;
    cout << findPrime() << "\n";
}
