#include <iostream>
#include <algorithm>
#include <vector>
#include <deque>
#include <cmath>

using namespace std;

int n;
int floorCheck[1000];
pair<long long, long long> stand;
vector<pair<pair<long long, long long>, int>> poses;

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

    bool operator()(pair<pair<long long, long long>, int> pos1, pair<pair<long long, long long>, int> pos2){
        long long ccwResult = ccw(poses[0].first, pos1.first, pos2.first);
        if(ccwResult == 0) {
            long long ccwDir = ccw(poses[0].first, {poses[0].first.first + 1, poses[0].first.second}, pos2.first);
            if(ccwDir < 0) return pos1.first.second > pos2.first.second;
            if(ccwDir == 0) return pos1.first.first < pos2.first.first;
            return pos1.first.second < pos2.first.second;
        }
        return ccwResult > 0;
    }

} comparable;

struct{
    
    bool operator()(pair<pair<long long, long long>, int> pos1, pair<pair<long long, long long>, int> pos2){
        return floorCheck[pos1.second] < floorCheck[pos2.second];
    }

} comparableByFloor;

bool convexHull(int floor){
    deque<pair<pair<long long, long long>, int>> convex;
    if(floorCheck[poses[0].second] != -1 || floorCheck[poses[1].second] != -1) return false;
    convex.push_back({poses[0].first, poses[0].second});
    convex.push_back({poses[1].first, poses[1].second});
    for(int i = 2; i < poses.size(); i++){
        if(floorCheck[poses[i].second] != -1) continue;
        int ccwResult = ccw(convex[convex.size()-2].first, convex.back().first, poses[i].first);
        while(ccwResult == -1){
            convex.pop_back();
            ccwResult = ccw(convex[convex.size()-2].first, convex.back().first, poses[i].first);
        }
        if(ccwResult == 0) convex.pop_back();
        convex.push_back({poses[i].first, poses[i].second});
    }
    if(convex.size() <= 2) return false;
    if(ccw(convex[convex.size()-2].first, convex.back().first, convex.front().first) == 0) convex.pop_back();
    if(convex.size() <= 2) return false;
    while(!convex.empty()){
        pair<pair<long long, long long>, int> pos = convex.front();
        floorCheck[pos.second] = floor;
        convex.pop_front();
    }
    return true;
}

int getIdxOfNoneSelected(){
    for(int i = 0; i < poses.size(); i++){
        if(floorCheck[poses[i].second] != -1) return i;
    }
    return poses.size();
}

void convexHull(){
    bool makeable = true;
    int floor = 1;
    while(makeable){
        sort(poses.begin(), poses.end(), comparableByFloor);
        int idxOfNoneSelected = getIdxOfNoneSelected();
        sort(poses.begin(), poses.begin()+idxOfNoneSelected);
        if(idxOfNoneSelected <= 1) return;
        sort(poses.begin()+1, poses.begin()+idxOfNoneSelected, comparable);
        makeable = convexHull(floor);
        floor++;
    }
}

void input(){
    cin >> n;
    for(int i = 0; i < n; i++){
        long long x, y;
        cin >> x >> y;
        poses.push_back({{x, y}, i});
        floorCheck[i] = -1;
    }
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    input();
    sort(poses.begin(), poses.end());
    convexHull();
    for(int i = 0; i < poses.size(); i++) {
        cout << (floorCheck[i] == -1 ? 0 : floorCheck[i]) << " ";
    }
    return 0;
}
