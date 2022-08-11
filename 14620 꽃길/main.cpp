#include <iostream>
#include <algorithm>
#include <cmath>
#include <string>
#include <vector>

using namespace std;

int N;
int arr[10][10];
bool planted[10][10];

bool isOutOfBounds(int y, int x){
    return y <= 0 || x <= 0 || y >= N-1 || x >= N-1;
}

bool isPlanted(int y, int x){
    return planted[y][x] || planted[y-1][x] || planted[y][x-1] || planted[y][x+1] || planted[y+1][x];
}

int pickedUp(int y, int x){
    int cost = -1 * (arr[y][x] + arr[y-1][x] + arr[y][x-1] + arr[y][x+1] + arr[y+1][x]);
    planted[y][x] = false;
    planted[y-1][x] = false;
    planted[y][x-1] = false;
    planted[y][x+1] = false;
    planted[y+1][x] = false;
    return cost;
}

int plant(int y, int x){
    int cost = arr[y][x] + arr[y-1][x] + arr[y][x-1] + arr[y][x+1] + arr[y+1][x];
    planted[y][x] = true;
    planted[y-1][x] = true;
    planted[y][x-1] = true;
    planted[y][x+1] = true;
    planted[y+1][x] = true;
    return cost;
}

int plant(int y, int x, bool trig){
    if(trig) return plant(y, x);
    return pickedUp(y, x);
}

int plantFlowers(int flowers){
    if(flowers == 0) return 0;
    int cost = 987654321;
    for(int y = 0; y < N; y++){
        for(int x = 0; x < N; x++){
            if(isPlanted(y, x) || isOutOfBounds(y, x)) continue;
            int nowPlantCost = plant(y, x, true);
            int nextPlantCost = plantFlowers(flowers-1);
            cost = min(nowPlantCost + nextPlantCost, cost);
            plant(y, x, false);
        }
    }
    return cost;
}

void inputGarden(){
    for(int y = 0; y < N; y++)
        for(int x = 0; x < N; x++) cin >> arr[y][x];
}

int main() {
    ios::sync_with_stdio(false);
	cin.tie(NULL);
    cout.tie(NULL);
    cin >> N;
    inputGarden();
    cout << plantFlowers(3) << "\n";
    return 0;
}
