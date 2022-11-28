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

pair<long long, long long> minPair(pair<long long, long long> pos1, 
                                pair<long long, long long> pos2){
    if(pos1.first == pos2.first){
        if(pos1.second > pos2.second) return pos2;
        return pos1;
    }
    if(pos1.first > pos2.first) return pos2;
    return pos1;
}


pair<long long, long long> maxPair(pair<long long, long long> pos1, 
                                pair<long long, long long> pos2){
    if(pos1.first == pos2.first){
        if(pos1.second > pos2.second) return pos1;
        return pos2;
    }
    if(pos1.first > pos2.first) return pos1;
    return pos2;
}

bool isFar(){
    pair<long long, long long> maxA = maxPair(pos[0], pos[1]);
    pair<long long, long long> minA = minPair(pos[0], pos[1]);
    pair<long long, long long> maxB = maxPair(pos[2], pos[3]);
    pair<long long, long long> minB = minPair(pos[2], pos[3]);
    if(minB == maxPair(maxA, minB)){
        if(maxA != minB && maxA != maxPair(maxA, minB)) return true;
    }
    if(minA == maxPair(maxB, minA)){
        if(minA != maxB && maxB != maxPair(minA, maxB)) return true;
    }
    return false; 
}

int isCross(){
    if(pos[0] == pos[2] || pos[0] == pos[3] || pos[1] == pos[2] || pos[1] == pos[3]) return true;
    long long degree1 = ccw(pos[0], pos[1], pos[2]) * ccw(pos[0], pos[1], pos[3]);
    long long degree2 = ccw(pos[2], pos[3], pos[0]) * ccw(pos[2], pos[3], pos[1]);
    if(degree1 == 0 && degree2 == 0 && isFar()) return 0;
    return degree1 <= 0 
            && degree2 <= 0;
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
