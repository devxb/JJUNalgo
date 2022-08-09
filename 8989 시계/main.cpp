#include <iostream>
#include <algorithm> 
#include <string>
using namespace std;

int T;

double getDegree(double point1, double point2){
    return min(abs(point1 - point2), 360-abs(point1 - point2));
}

double parseMinutesDegree(double minutes){
    return 6 * minutes;
}

double parseHourDegree(double hour, double minutes){
    if(hour >= 12) hour -= 12;
    return 30 * hour + minutes / 2;
}

void inputDegreeAndTimes(pair<double,string> degreeAndTimes[]){
    for(int i = 0; i < 5; i++){
        string time = "";
        cin >> time;
        degreeAndTimes[i] = {getDegree(
            parseHourDegree(stod(time.substr(0, 2)), stod(time.substr(3, 2)))
            , parseMinutesDegree(stod(time.substr(3, 2)))
            ), time};
    }
    sort(degreeAndTimes, degreeAndTimes+5);
}

string solve(){
    string times[5];
    pair<double, string> degreeAndTimes[5];
    inputDegreeAndTimes(degreeAndTimes);
    return degreeAndTimes[2].second;
}

int main() {
    ios::sync_with_stdio(false);
	  cin.tie(NULL);
    cout.tie(NULL);
    cin >> T;
    for(int t = 0; t < T; t++) cout << solve() << "\n";
    return 0;
}
