#include <iostream>
#include <algorithm>
#include <string>
#include <vector>
#include <deque>

using namespace std;

int N, X;
bool isMakeable = true;
deque<char> word;

char costToWord(int length){
    return (char)(length + (int)'A'-1);
}

int getCost(char a){
    return a - (int)'A' + 1;
}

void makeWord(int length, int cost){
    if(length == 0){
        if(cost < X){
            isMakeable = false;
            return;
        }
        return;
    }
    if(length == X-cost) {
        word.push_front('A');
        makeWord(length-1, cost+getCost('A'));
        return;
    }
    char c = 'Z';
    if(X-cost < 26) c = costToWord(X-cost);
    for(; c >= 'A'; c--){
        int sumCost = getCost(c);
        int nextCost = cost + sumCost;
        if(X - nextCost < 0 || X - nextCost < length-1) continue;
        word.push_front(c);
        makeWord(length-1, nextCost);
        return;
    }
    isMakeable = false;
    return;
}

void input(){
    cin >> N >> X;
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    input();
    makeWord(N, 0);
    if(!isMakeable) {
        cout << "!" << "\n";
        return 0;
    }
    while(!word.empty()){
        char c = word.front();
        word.pop_front();
        cout << c;
    }
}
