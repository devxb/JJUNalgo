#include <iostream>
#include <algorithm>
#include <cmath>
#include <vector>

using namespace std;

int N, K;
int sums[1000000];

int findLongestDistanceOfEven(){
    int from = -1;
    vector<pair<int, int> > distanceVec;
    for(int to = 0; to < N; to++){
        if(sums[to] % 2 == 0 && from == -1) from = to;
        if(sums[to] % 2 == 1 && from != -1) {
            distanceVec.push_back({from, to-1});
            from = -1;
        }
    }
    if(sums[N-1] % 2 == 0) distanceVec.push_back({(from == -1 ? N-1 : from), N-1});
    if(distanceVec.size() == 0) return 0;
    sort(distanceVec.begin(), distanceVec.end());
    int ableDistance = distanceVec[0].second - distanceVec[0].first + 1;
    int longestDistance = ableDistance;
    int deletedOddCount = 0, deletedIdx = 0;
    for(int i = 1; i < distanceVec.size(); i++){
        ableDistance += distanceVec[i].second - distanceVec[i].first + 1;
        deletedOddCount += distanceVec[i].first - distanceVec[i-1].second - 1;
        while(deletedOddCount > K){
            ableDistance -= distanceVec[deletedIdx].second - distanceVec[deletedIdx].first + 1;
            deletedOddCount -= distanceVec[deletedIdx+1].first - distanceVec[deletedIdx].second - 1;
            deletedIdx++;
        }
        longestDistance = max(longestDistance, ableDistance);
    }
    longestDistance = max(longestDistance, ableDistance);
    return longestDistance;
}

void input(){
    cin >> N >> K;
    for(int i = 0; i < N; i++) cin >> sums[i];
}

int main() {
    ios::sync_with_stdio(false);
	cin.tie(NULL);
    cout.tie(NULL);
    input();
    cout << findLongestDistanceOfEven() << "\n";
    return 0;
}
