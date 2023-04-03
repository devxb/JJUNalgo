#include <iostream>
#include <algorithm>
#include <string>
#include <unordered_set>
#include <cmath>
#include <vector>
#include <queue>
#include <deque>

using namespace std;

priority_queue<int> pq;

int N, M;
priority_queue<int, vector<int>, greater<int>> chargers;

int getChargeTime(){
    for(int i = 0; i < M && !pq.empty(); i++){
        chargers.push(pq.top());
        pq.pop();
    }
    while(!pq.empty()){
        int topNum = chargers.top();
        while(!chargers.empty() && chargers.top() == topNum){
            chargers.pop();
        }
        while(!pq.empty() && chargers.size() < M){
            chargers.push(topNum + pq.top());
            pq.pop();
        }
    }
    int currentTime = 0;
    while(!chargers.empty()){
        currentTime = max(currentTime, chargers.top());
        chargers.pop();
    }
    return currentTime;
}

void input(){
    cin >> N >> M;
    for(int i = 0; i < N; i++){
        int num;
        cin >> num;
        pq.push(num);
    }
}

int main() {
    ios::sync_with_stdio(false);
	cin.tie(NULL);
    cout.tie(NULL);
    input();
    cout << getChargeTime() << "\n";
    return 0;
}
