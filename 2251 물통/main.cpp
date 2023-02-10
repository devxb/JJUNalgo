#include <iostream>
#include <algorithm>
#include <string>
#include <vector>
#include <deque>
#include <cmath>
#include <queue>
#include <unordered_map>

using namespace std;

int A, B, C;
bool check[201][201][201];
bool visited[201];
vector<int> vec;

void solve(int a, int b, int c){
    if(check[a][b][c]) return;
    check[a][b][c] = true;
    if(a == 0 && !visited[c]){
        vec.push_back(c);
        visited[c] = true;
    }
    int capA = A - a;
    int capB = B - b;
    int capC = C - c;
    solve(max(0, a - capC), b, c + min(a, capC)); // a to c
    solve(max(0, a - capB), b + min(a, capB), c); // a to b
    solve(a, max(0, b - capC), c + min(b, capC)); // b to c
    solve(a + min(b, capA), max(0, b - capA), c); // b to a
    solve(a + min(c, capA), b, max(0, c - capA)); // c to a
    solve(a, b + min(c, capB), max(0, c - capB)); // c to b
}

void input(){
    cin >> A >> B >> C;
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    input();
    solve(0, 0, C);
    sort(vec.begin(), vec.end());
    for(int i = 0; i < vec.size(); i++){
        cout << vec[i] << " ";
    }
}
