#include <iostream>
#include <algorithm>
#include <cmath>
#include <string>
#include <vector>

using namespace std;

int L, S;
long long arr[100];
vector<pair<long long, long long> > goodNums;

void printResult(){
    long long printedCount = 0;
    vector<long long> printed;
    for(long long i = 0; i < min(S, (int)goodNums.size()); i++){
        cout << goodNums[i].second << " ";
        printed.push_back(goodNums[i].second);
        printedCount++;
    }
    long long num = 0;
    while(printedCount < S){
        num++;
        bool isPrinted = false;
        for(long long i = 0; i < printed.size(); i++){
            if(num == printed[i]) {
                isPrinted = true;
                break;
            }
        }
        if(isPrinted) continue;
        cout << num << " ";
        printed.push_back(num);
        printedCount++;
    }
}

void findGoodNums(long long left, long long right){
    long long rangeCount = right - left;
    long long inc = rangeCount-1;
    long long pushCount = 0;
    for(long long i = 0; i <= (left + right) / 2; i++){
        if(pushCount > S || right - i < left + i) break;
        goodNums.push_back({rangeCount, left + i});
        pushCount++;
        if(right - i != left + i){
            goodNums.push_back({rangeCount, right - i});
            pushCount++;
        }
        rangeCount += inc;
        inc -= 2;
    }
}

void inputArr(){
    for(long long i = 0; i < L; i++) {
        cin >> arr[i];
        goodNums.push_back({0, arr[i]});
    }
}

int main() {
    ios::sync_with_stdio(false);
	cin.tie(NULL);
    cout.tie(NULL);
    cin >> L;
    inputArr();
    sort(arr, arr + L);
    cin >> S;
    findGoodNums(1, arr[0]-1);
    for(long long i = 0; i < L-1; i++) findGoodNums(arr[i]+1, arr[i+1]-1);
    sort(goodNums.begin(), goodNums.end());
    printResult();
    return 0;
}
