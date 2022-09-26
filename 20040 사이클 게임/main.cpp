#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
#include <utility>
#include <map>
#include <unordered_map>

using namespace std;

int n, m;
int par[500005];

int disjoint(int idx){
    if(par[idx] == -1) return idx;
    return par[idx] = disjoint(par[idx]);
}

int input(){
    cin >> n >> m;
    for(int i = 0; i < m; i++){
        int from, to;
        cin >> from >> to;
        int parIdx = disjoint(min(from, to));
        int sonIdx = disjoint(max(from, to));
        if(parIdx == sonIdx) return i+1;
        par[sonIdx] = parIdx;
    }
    return 0;
}

int main() {
    ios::sync_with_stdio(false);
	cin.tie(NULL);
    cout.tie(NULL);
    for(int i = 0; i < 500000; i++) par[i] = -1;
    cout << input() << "\n";
    return 0;
}
