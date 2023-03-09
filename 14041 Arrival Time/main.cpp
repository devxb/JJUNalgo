#include <iostream>
#include <algorithm>
#include <string>
#include <unordered_set>
#include <cmath>
#include <vector>

using namespace std;

int N;
int hour;
int minutes;

void increaseTime(){
    if(hour == 24) hour = 0;
    if(minutes >= 60){
        hour += 1;
        minutes -= 60;
    }
    if(hour == 24) hour = 0;
}

bool isRushHourTraffic(){
    return ((hour >= 7) && (hour < 10))
            || ((hour >= 15) && (hour < 19));
}

void arrivalTime(){
    int meter = 0;
    while(meter < 120){
        if(isRushHourTraffic()){
            minutes += 1;
        }
        minutes += 1;
        meter += 1;
        increaseTime();
    }
    if(hour < 10){
        cout << "0" << hour << ":";
    }
    else{
        cout << hour << ":";
    }
    if(minutes < 10){
        cout << "0" << minutes << "\n";
    }
    else{
        cout << minutes << "\n";
    }
}

void input(){
    string time;
    cin >> time;
    hour = stoi(time.substr(0, 2));
    minutes = stoi(time.substr(3, 5));
}

int main() {
    ios::sync_with_stdio(false);
	cin.tie(NULL);
    cout.tie(NULL);
    input();
    arrivalTime();
    return 0;
}
