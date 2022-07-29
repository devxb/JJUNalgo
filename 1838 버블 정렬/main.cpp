#include <iostream>
#include <algorithm>
#include <string>

using namespace std;

int N;

int getSortedCount(pair<int, int> arr[], int size){
    int sortedCount = 0;
    for(int i = 0; i < size; i++) 
        sortedCount = max(sortedCount, max(arr[i].second - i, 0));
    return sortedCount;
}

void inputArr(pair<int, int> arr[], int size){
    for(int i = 0; i < size; i++) {
        int num;
        cin >> num;
        arr[i] = {num, i};
    }
    sort(arr, arr + size);
}

int main() {
    ios::sync_with_stdio(false);
	  cin.tie(NULL);
    cout.tie(NULL);
    int N;
    cin >> N;
    pair<int, int> arr[N];
    inputArr(arr, N);
    cout << getSortedCount(arr, N) << "\n";
    return 0;
}
