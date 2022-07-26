#include <iostream>
#include <algorithm>
#include <string>

using namespace std;

int N, P;

int main() {
    ios::sync_with_stdio(false);
	cin.tie(NULL);
    cout.tie(NULL);
    cin >> N >> P;
    pair<int, int> planets[N];
    for(int i = 1; i <= N; i++){
        int fuel, travelCost;
        cin >> fuel >> travelCost;
        planets[i] = {travelCost, fuel};
    }
    int visitPlanetCount = 1, collectedFuelCells = planets[P].second;
    planets[P] = {987654321, 1};
    sort(planets+1, planets+N);
    for(int i = 1; i <= N; i++) 
        if(planets[i].first <= collectedFuelCells && planets[i].second - planets[i].first >= 0){
            visitPlanetCount++;
            collectedFuelCells += (planets[i].second - planets[i].first);
        }
    cout << collectedFuelCells << "\n" << visitPlanetCount << "\n";
    return 0;
}
