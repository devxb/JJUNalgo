#include <iostream>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

int N, K;
int arr[105][1005];
int siz[105];

bool kmp(int idx, int virus[], int pi[]){
    int p = 0;
    for(int i = 0; i < siz[idx]; i++){
        while(arr[idx][i] != virus[p] && p > 0) p = pi[p-1];
        if(arr[idx][i] == virus[p]) p++;
        if(p == K) return true;
    }
    return false;
}

void initPi(int virus[], int pi[]){
    int p = 0;
    for(int i = 1; i < K; i++){
        while(virus[i] != virus[p] && p > 0) p = pi[p-1];
        if(virus[p] == virus[i]) pi[i] = ++p;
    }
}

bool findVirus(){
    for(int i = 0; i < siz[0]; i++){
        if(i >= K-1){
            int virus[K];
            int revVirus[K];
            for(int j = 0; j < K; j++) {
                virus[j] = arr[0][i-j];
                revVirus[j] = virus[j];
            }
            reverse(revVirus, revVirus+K);
            int pi[K];
            int revPi[K];
            for(int j = 0; j < K; j++) revPi[j] = pi[j] = 0;
            initPi(virus, pi);
            initPi(revVirus, revPi);
            int hitCount = 1;
            for(int j = 1; j < N; j++){
                if(kmp(j, virus, pi) || kmp(j, revVirus, revPi)) hitCount++;
                else break;
            }
            if(hitCount == N) return true;
        }
    }
    return false;
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> K;
    for(int i = 0; i < N; i++){
        int M;
        cin >> M;
        siz[i] = M;
        for(int j = 0; j < M; j++) cin >> arr[i][j];
    }
    cout << (findVirus() ? "YES" : "NO") << "\n";
}
