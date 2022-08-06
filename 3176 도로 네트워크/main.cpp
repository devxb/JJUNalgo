#include <iostream>
#include <algorithm>
#include <cmath>
#include <string>
#include <vector>

using namespace std;

int N;
vector<pair<int, int> > twoWayRoads[100001];
pair<int, pair<int, int> > oneWayRoads[100001][18];
int maxHeight[100005];
int farFromRoot[100005];

pair<int, int> balanceNodeHeight(int &node1, int &node2){
    pair<int, int> lowAndHigh = {987654321, 0};
    while(abs(farFromRoot[node1] - farFromRoot[node2]) != 0){
        if(farFromRoot[node1] < farFromRoot[node2]) {
            lowAndHigh = {
                min(lowAndHigh.first, oneWayRoads[node2][maxHeight[farFromRoot[node2] - farFromRoot[node1]]].second.first),
                max(lowAndHigh.second, oneWayRoads[node2][maxHeight[farFromRoot[node2] - farFromRoot[node1]]].second.second)
            };
            node2 = oneWayRoads[node2][maxHeight[farFromRoot[node2] - farFromRoot[node1]]].first;
        }
        if(farFromRoot[node1] > farFromRoot[node2]){
            lowAndHigh = {
                min(lowAndHigh.first, oneWayRoads[node1][maxHeight[farFromRoot[node1] - farFromRoot[node2]]].second.first),
                max(lowAndHigh.second, oneWayRoads[node1][maxHeight[farFromRoot[node1] - farFromRoot[node2]]].second.second)
            };
            node1 = oneWayRoads[node1][maxHeight[farFromRoot[node1] - farFromRoot[node2]]].first;
        }
    }
    return lowAndHigh;
}

int liftUp(int node1, int node2){
    int height = 0;
    while(height <= maxHeight[farFromRoot[node1]] && oneWayRoads[node1][height].first != oneWayRoads[node2][height].first) height++;
    return height;
}

pair<int, int> findLowAndHighCost(int node1, int node2){
    pair<int, int> lowAndHigh = balanceNodeHeight(node1, node2);
    while(node1 != node2){
        int height = liftUp(node1, node2);
        if(height > 0 && oneWayRoads[node1][height].first == oneWayRoads[node2][height].first) height--;
        lowAndHigh = {
            min(lowAndHigh.first, min(oneWayRoads[node1][height].second.first, oneWayRoads[node2][height].second.first)),
            max(lowAndHigh.second, max(oneWayRoads[node1][height].second.second, oneWayRoads[node2][height].second.second))
        };
        node1 = oneWayRoads[node1][height].first;
        node2 = oneWayRoads[node2][height].first;
    }
    return lowAndHigh;
}

void inputQuery(){
    int K;
    cin >> K;
    for(int i = 0; i < K; i++){
        int node1, node2;
        cin >> node1 >> node2;
        pair<int, int> result = findLowAndHighCost(node1, node2);
        cout << result.first << " " << result.second << "\n";
    }
}

pair<int, pair<int, int> > updateParAncestorElement(int idx, int dist){
    if(idx == 1) return oneWayRoads[1][0];
    if(dist == 0 || oneWayRoads[idx][dist].first != -1) return oneWayRoads[idx][dist];
    pair<int, pair<int, int> > result = updateParAncestorElement(oneWayRoads[idx][dist-1].first, dist-1);
    return oneWayRoads[idx][dist] = {result.first ,
        {min(result.second.first, oneWayRoads[idx][dist-1].second.first), 
        max(result.second.second, oneWayRoads[idx][dist-1].second.second)}
    };
}

void updateParAncestors(){
    for(int j = 1; j <= maxHeight[N]; j++)
        for(int i = 2; i <= N; i++) updateParAncestorElement(i, j);
}

int updateFarFromRootElement(int idx){
    if(idx == 1) return 0;
    if(farFromRoot[idx] != 0) return farFromRoot[idx];
    return farFromRoot[idx] = updateFarFromRootElement(oneWayRoads[idx][0].first)+1;
}

void updateFarFromRoot(){
    for(int i = N; i >= 2; i--) updateFarFromRootElement(i);
}

void convertRoadTwoWayToOneWay(int idx, int parent, int cost){
    oneWayRoads[idx][0] = {parent, {cost, cost}};
    for(int i = 0; i < twoWayRoads[idx].size(); i++){
        int son = twoWayRoads[idx][i].first;
        if(parent == son) continue;
        convertRoadTwoWayToOneWay(son, idx, twoWayRoads[idx][i].second);
    }
}

void inputRoads(){
    for(int i = 1; i < N; i++){
        int node1, node2, cost;
        cin >> node1 >> node2 >> cost;
        twoWayRoads[node1].push_back({node2, cost});
        twoWayRoads[node2].push_back({node1, cost});
    }
    convertRoadTwoWayToOneWay(1, 0, 0);
}

void initOneWayRoads(){
    for(int i = 2; i <= 100000; i++)
        for(int j = 0; j <= 17; j++) oneWayRoads[i][j] = {-1, {987654321, 0}};
}

void initDiffFar(){
    maxHeight[0] = maxHeight[1] = 0;
    int two = 2, height = 1;
    for(int i = 2; i <= 100000; i++){
        if(two*2 <= i) {
            two *= 2;
            height++;
        }
        maxHeight[i] = height;
    }
}

int main() {
    ios::sync_with_stdio(false);
	cin.tie(NULL);
    cout.tie(NULL);
    initDiffFar();
    initOneWayRoads();
    cin >> N;
    inputRoads();
    updateFarFromRoot();
    updateParAncestors();
    inputQuery();
    return 0;
}
