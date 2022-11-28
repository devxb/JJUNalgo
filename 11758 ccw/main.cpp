#include <iostream>
#include <algorithm>
#include <vector>
#include <sstream>
#include <unordered_set>
#include <unordered_map>
#include <utility>
#include <cmath>
#include <deque>

using namespace std;

pair<long long, long long> pos[3];

int ccw(){
    long long vecA = pos[0].first * pos[1].second + pos[1].first * pos[2].second + pos[2].first * pos[0].second;
    long long vecB = pos[0].second * pos[1].first + pos[1].second * pos[2].first + pos[2].second * pos[0].first;
    if(vecA-vecB < 0) return -1;
    if(vecA-vecB > 0) return 1;
    return 0;
}

void input(){
    for(int i = 0; i < 3; i++) 
        cin >> pos[i].first >> pos[i].second;
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    input();
    cout << ccw() << "\n";
    return 0;
}
