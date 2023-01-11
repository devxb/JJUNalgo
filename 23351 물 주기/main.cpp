#include <iostream>
#include <algorithm>
#include <string>
#include <vector>
#include <deque>
#include <queue>

using namespace std;

int pots, waters, feedWater, increaseWater;
vector<int> vec;

int feedUntilDie(int day){
    int lessHealthIdx = 0;
    int lessHealth = vec[0];
    for(int i = 0; i < vec.size(); i++){
        if(vec[i] < lessHealth){
            lessHealthIdx = i;
            lessHealth = vec[i];
        }
    }
    for(int i = lessHealthIdx; i < lessHealthIdx+feedWater && i < vec.size(); i++){
        vec[i] += increaseWater;
    }
    for(int i = 0; i < vec.size(); i++){
        vec[i] -= 1;
        if(vec[i] <= 0) return day;
    }
    return feedUntilDie(day+1);
}

void input(){
    cin >> pots >> waters >> feedWater >> increaseWater;
    for(int i = 0; i < pots; i++) vec.push_back(waters);
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    input();
    cout << feedUntilDie(1) << "\n";
}
