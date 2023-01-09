#include <iostream>
#include <algorithm>
#include <string>
#include <vector>

using namespace std;

int length;
string footPrint;
pair<int, int> south = {1, 0};
pair<int, int> north = {-1, 0};
pair<int, int> east = {0, 1};
pair<int, int> west = {0, -1};
pair<int, int> rightDirs[] = {east, south, west, north};
pair<int, int> leftDirs[] = {east, north, west, south};
char arr[150][150];
vector<pair<int, int>> moved;
int cornerY = 150, cornerX = 150;
int endCornerY = -1, endCornerX = -1;

void updateMazeCorners(){
    for(int i = 0; i < moved.size(); i++){
        cornerY = min(cornerY, moved[i].first);
        endCornerY = max(endCornerY, moved[i].first);
        cornerX = min(cornerX, moved[i].second);
        endCornerX = max(endCornerX, moved[i].second);
    }
}

bool comparePair(pair<int, int> a, pair<int, int> b){
    return a.first == b.first && a.second == b.second;
}

pair<int, int> turn(pair<int, int> currentDir, pair<int, int> *turnDir){
    for(int i = 0; i < 4; i++)
        if(comparePair(turnDir[i], currentDir))
            return i == 3 ? turnDir[0] : turnDir[i+1];
}

void recoverMaze(){
    for(int i = 0; i < 150; i++)
        for(int j = 0; j < 150; j++) arr[i][j] = '#';
    int y = 75, x = 75;
    moved.push_back({y, x});
    arr[y][x] = '.';
    pair<int, int> head = south;
    for(int i = 0; i < length; i++){
        if(footPrint[i] == 'F'){
            y += head.first;
            x += head.second;
            moved.push_back({y, x});
            arr[y][x] = '.';
            continue;
        }
        if(footPrint[i] == 'R') {
            head = turn(head, rightDirs);
            continue;
        }
        head = turn(head, leftDirs);
    }
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> length >> footPrint;
    recoverMaze();
    updateMazeCorners();
    for(int i = cornerY; i <= endCornerY; i++){
        for(int j = cornerX; j <= endCornerX; j++){
            cout << arr[i][j];
        }
        cout << "\n";
    }
}
