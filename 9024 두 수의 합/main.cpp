#include <iostream>
#include <algorithm>
#include <string>

using namespace std;

int INF = 987654321;

pair<int, int> findClosestPairSet(int stand, int n, int k, int arr[]){
    int left = stand+1, right = n-1;
    int closest = INF, pairCount = 0;
    for(int i = 0; (i < 20) && (left <= right); i++){
        int mid = (left + right) / 2;
        int diff = abs(k - (arr[stand] + arr[mid]));
        if(diff < closest){
            closest = diff;
            pairCount = 1;
        }
        else if(diff == closest) pairCount++;
        if(arr[stand] + arr[mid] > k) right = mid-1;
        if(arr[stand] + arr[mid] <= k) left = mid+1; 
    }
    return {closest, pairCount};
}

void initArr(int n, int arr[]){
    for(int i = 0; i < n; i++) cin >> arr[i];
    sort(arr, arr + n);
}

void solve(){
    int n, k;
    cin >> n >> k;
    int arr[n];
    initArr(n, arr);
    int closestNum = INF, closestPairCount = 0;
    for(int i = 0; i < n; i++){
        pair<int ,int> closestPair = findClosestPairSet(i, n, k, arr);
        if(closestPair.first < closestNum){
            closestNum = closestPair.first;
            closestPairCount = closestPair.second;
            continue;
        }
        if(closestPair.first == closestNum) closestPairCount += closestPair.second;
    }
    cout << closestPairCount << "\n";
}

int main() {
    ios::sync_with_stdio(false);
	cin.tie(NULL);
    cout.tie(NULL);
    int T;
    cin >> T;
    for(int t = 0; t < T; t++) solve();
    return 0;
}
