#include <iostream>
#include <algorithm>
#include <cmath>
#include <string>
#include <vector>

using namespace std;

int N, S;
pair<int, int> cars[1000002];
bool connected[1000002];

void connectCars(int idx){
    connected[idx] = true;
    for(int i = idx; i <= N; i++){
        if(cars[idx].first + cars[idx].second < cars[i].first) break;
        if(connected[i]) continue;
        connectCars(i);
    }
    for(int i = idx; i >= 0; i--){
        if(cars[idx].first - cars[idx].second > cars[i].first) break;
        if(connected[i]) continue;
        connectCars(i);
    }
}

void inputFuels(){
    for(int i = 1; i <= N; i++) cin >> cars[i].second;
}

void inputCars(){
    for(int i = 1; i <= N; i++) cin >> cars[i].first;
}

int main() {
    ios::sync_with_stdio(false);
	cin.tie(NULL);
    cout.tie(NULL);
    cin >> N >> S;
    inputCars();
    inputFuels();
    connectCars(S);
    for(int i = 1; i <= N; i++) if(connected[i]) cout << i << " ";
    return 0;
}
