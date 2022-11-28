#include <iostream>
#include <algorithm>
#include <vector>
#include <sstream>
#include <unordered_set>
#include <unordered_map>
#include <cmath>
#include <deque>

using namespace std;

pair<long long, long long> pos[4];

int ccw(pair<long long, long long> pos1,
         pair<long long, long long> pos2,
         pair<long long, long long> pos3){
    long long vecA = pos1.first * pos2.second + pos2.first * pos3.second + pos3.first * pos1.second;
    long long vecB = pos1.second * pos2.first + pos2.second * pos3.first + pos3.second * pos1.first;
    if(vecA - vecB < 0) return 1;
    if(vecA - vecB > 0) return -1;
    return 0;
}

int isCross(){
    return ccw(pos[0], pos[1], pos[2]) * ccw(pos[0], pos[1], pos[3]) < 0 
            && ccw(pos[2], pos[3], pos[0]) * ccw(pos[2], pos[3], pos[1]) < 0;
}

void input(){
    for(int i = 0; i < 4; i++) cin >> pos[i].first >> pos[i].second;
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    input();
    cout << isCross() << "\n";
    return 0;
}
