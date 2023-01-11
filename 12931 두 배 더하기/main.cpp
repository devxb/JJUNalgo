#include <iostream>
#include <algorithm>
#include <string>
#include <vector>
#include <deque>
#include <queue>

using namespace std;

int N;
vector<int> vecB;

bool isAlreadyAllZero(){
    for(int i = 0; i < N; i++){
        if(vecB[i] > 0) return false;
    }
    return true;
}

int turnVecBToZero(){
    if(isAlreadyAllZero()) return 0;
    int ans = 0;
    while(true){
        bool isDoubleDecreaseable = true;
        for(int i = 0; i < N; i++){
            if(vecB[i] % 2 != 0) {
                isDoubleDecreaseable = false;
                break;
            }
        }
        if(isDoubleDecreaseable){
            for(int i = 0; i < N; i++) vecB[i] /= 2;
            ans++;
            continue;
        }
        for(int i = 0; i < N; i++){
            if(vecB[i] % 2 != 0) {
                vecB[i] -= 1;
                ans++;
            }
        }
        bool escape = true;
        for(int i = 0; i < N; i++){
            if(vecB[i] > 0) {
                escape = false;
                break;
            }
        }
        if(escape) return ans;
    }
    return ans;
}

void input(){
    cin >> N;
    for(int i = 0; i < N; i++){
        int num;
        cin >> num;
        vecB.push_back(num);
    }
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    input();
    cout << turnVecBToZero() << "\n";
}
