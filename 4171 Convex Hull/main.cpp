#include <iostream>
#include <algorithm>
#include <vector>
#include <deque>
#include <cmath>

using namespace std;

int n;
pair<long long, long long> stand;
vector<pair<long long, long long>> poses;
deque<pair<long long, long long>> convex;

int ccw(pair<long long, long long> pos1,
         pair<long long, long long> pos2,
         pair<long long, long long> pos3){
    long long vecA = pos1.first * pos2.second + pos2.first * pos3.second + pos3.first * pos1.second;
    long long vecB = pos1.second * pos2.first + pos2.second * pos3.first + pos3.second * pos1.first;
    if(vecA - vecB < 0) return -1;
    if(vecA - vecB > 0) return 1;
    return 0;
}

struct{

    bool operator()(pair<long, long> pos1, pair<long, long> pos2){
        long long ccwResult = ccw(poses[0], pos1, pos2);
        if(ccwResult == 0) {
            long long ccwDir = ccw(poses[0], {poses[0].first + 1, poses[0].second}, pos2);
            if(ccwDir <= 0)
                return pos1.first < pos2.first;
            return pos1.second > pos2.second;
        }
        return ccwResult > 0;
    }

} comparable;

void convexHull(){
    convex.push_back(poses[0]);
    convex.push_back(poses[1]);
    pair<long long, long long> pos1 = poses[0];
    pair<long long, long long> pos2 = poses[1];
    for(int i = 2; i < poses.size(); i++){
        int ccwResult = ccw(pos1, pos2, poses[i]);
        if(ccwResult == -1){
            pos2 = poses[i];
            convex.pop_back();
            convex.push_back(poses[i]);
            continue;
        }
        pos1 = pos2;
        pos2 = poses[i];
        convex.push_back(poses[i]);
    }
}

void input(){
    cin >> n;
    for(int i = 0; i < n; i++){
        long long x, y;
        char c;
        cin >> x >> y >> c;
        if(c != 'Y') continue;
        poses.push_back({x, y});
    }
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    input();
    sort(poses.begin(), poses.end());
    sort(poses.begin()+1, poses.end(), comparable);
    convexHull();
    cout << convex.size() << "\n";
    while(!convex.empty()){
        pair<long long, long long> pos = convex.front();
        convex.pop_front();
        cout << pos.first << " " << pos.second << "\n";
    }
    return 0;
}
