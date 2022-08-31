#include <iostream>
#include <utility>
#include <cmath>

using namespace std;

int N;
int fields[1001][1001];
pair<int, int> border[6];

bool isRunableDistance(){
    int xDistance = border[5].second - border[2].second;
    int yDistance = border[5].first - border[2].first;
    return sqrt(pow(xDistance, 2) + pow(yDistance, 2)) >= 5;
}

void changeBorderX(){
    int temp = border[2].second;
    border[2].second = border[5].second;
    border[5].second = temp;
}

void changeBorderY(){
    int temp = border[2].first;
    border[2].first = border[5].first;
    border[5].first = temp;
}

void changeBorder(){
    if(border[2].first > border[5].first) changeBorderY();
    if(border[2].second > border[5].second) changeBorderX();
}

bool isRunable(){
    changeBorder();
    if(!isRunableDistance()) return false;
    int anotherPeoples = 0;
    for(int y = border[2].first; y <= border[5].first; y++)
        for(int x = border[2].second; x <= border[5].second; x++) if(fields[y][x] == 1) anotherPeoples++;
    return anotherPeoples >= 3;
}

void setBorder(){
    for(int y = 0; y < N; y++)
        for(int x = 0; x < N; x++) border[fields[y][x]] = {y, x};
}

void input(){
    cin >> N;
    for(int y = 0; y < N; y++)
        for(int x = 0; x < N; x++) cin >> fields[y][x];
}

int main() {
    ios::sync_with_stdio(false);
	cin.tie(NULL);
    cout.tie(NULL);
    input();
    setBorder();
    cout << (isRunable() ? 1 : 0) << "\n";
    return 0;
}
