#include <iostream>
#include <algorithm>
#include <cmath>
#include <string>
#include <vector>

using namespace std;

int N;

int countStandCards(int arr[]){
    int needToDelete = N;
    int nextCard = 1;
    for(int i = 0; i < N; i++){
        if(nextCard == arr[i]){
            needToDelete--;
            nextCard++;
        }
    }
    return needToDelete;
}

void inputArr(int arr[]){
    for(int i = 0; i < N; i++) cin >> arr[i];
}

int main() {
    ios::sync_with_stdio(false);
	cin.tie(NULL);
    cout.tie(NULL);
    cin >> N;
    int arr[N];
    inputArr(arr);
    cout << countStandCards(arr) << "\n";
    return 0;
}
