#include <iostream>
#include <algorithm>
#include <string>

using namespace std;

int N;

long long findFloid(long long twoFloid, int left, int right, long long *arr){
    long long mid = -1;
    long long closest = 9876543210, ans = 98765432100;
    for(int i = 0; i < 13 && (left <= right); i++){
        mid = (left + right) / 2;
        if(twoFloid + arr[mid] > 0) right = mid-1;
        if(twoFloid + arr[mid] < 0) left = mid+1;
        if(abs(twoFloid + arr[mid]) < closest){
            ans = arr[mid];
            closest = abs(twoFloid + arr[mid]);
        }
    }
    return mid == -1 ? 9876543210 : ans;
}

void findThreeFloid(long long *arr, long long *resultArr){
    long long minFloidSum = 98765432100;
    for(int floid1 = 0; floid1 < N-1; floid1++){
        for(int floid2 = floid1+1; floid2 < N; floid2++){
            long long twoFloid = arr[floid1] + arr[floid2];
            long long floidCandidates[] = {
                findFloid(twoFloid, floid2+1, N-1, arr),
                findFloid(twoFloid, floid1+1, floid2-1, arr)
            };
            long long sum = 98765432100, selected = -1;
            for(int s = 0; s < 2; s++){
                if(abs(twoFloid + floidCandidates[s]) < sum){
                    sum = abs(twoFloid + floidCandidates[s]);
                    selected = floidCandidates[s];
                }
            }
            if(abs(twoFloid + selected) < minFloidSum){
                resultArr[0] = arr[floid1];
                resultArr[1] = arr[floid2];
                resultArr[2] = selected;
                minFloidSum = abs(twoFloid + selected);
            }
        }
    }
    sort(resultArr, resultArr+3);
}

void inputArr(long long *arr){
    for(int i = 0; i < N; i++) cin >> arr[i];
    sort(arr, arr+N);
}

int main() {
    ios::sync_with_stdio(false);
	cin.tie(NULL);
    cout.tie(NULL);
    cin >> N;
    long long arr[N];
    inputArr(arr);
    long long resultArr[3];
    findThreeFloid(arr, resultArr);
    cout << resultArr[0] << " " << resultArr[1] << " " << resultArr[2] << "\n";
    return 0;
}
