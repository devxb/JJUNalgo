//
//  main.cpp
//  2174 로봇 시뮬레이션
//
//  Created by 이준영 on 2020/11/04.
//

#include <iostream>
#include <algorithm>
#include <map>
using namespace std;

int arr[110][110];
map<char,char> leftt;
map<char,char> rightt;
pair<char,pair<int,int> > robot[110];
int A,B,N,M;
int force_exit = 0;

void move(int rot, int cnt){
    if(cnt == 0){
        return;
    }
    int temp = arr[robot[rot].second.first][robot[rot].second.second];
    arr[robot[rot].second.first][robot[rot].second.second] = 0;
    if(robot[rot].first == 'E'){
        robot[rot].second.first = robot[rot].second.first + 1;
    }
    if(robot[rot].first == 'W'){
        robot[rot].second.first = robot[rot].second.first - 1;
    }
    if(robot[rot].first == 'S'){
        robot[rot].second.second = robot[rot].second.second - 1;
    }
    if(robot[rot].first == 'N'){
        robot[rot].second.second = robot[rot].second.second + 1;
    }
    if(arr[robot[rot].second.first][robot[rot].second.second] != 0){
        cout << "Robot " << rot << " crashes into robot " << arr[robot[rot].second.first][robot[rot].second.second] << "\n";
        force_exit = 1;
        return;
    }
    if(robot[rot].second.first > A or robot[rot].second.first <= 0 or robot[rot].second.second > B or robot[rot].second.second <= 0){
        cout << "Robot " << rot << " crashes into the wall" << "\n";
        force_exit = 1;
        return;
    }
    arr[robot[rot].second.first][robot[rot].second.second] = temp;
    move(rot,cnt-1);
}

void turn_left(int rot, int cnt){
    for(int i = cnt; i > 0; i--){
        robot[rot].first = leftt.find(robot[rot].first)->second;
    }
    return;
}

void turn_right(int rot, int cnt){
    for(int i = cnt; i > 0; i--){
        robot[rot].first = rightt.find(robot[rot].first)->second;
    }
}

void set(){
    leftt.insert(make_pair('N','W'));
    leftt.insert(make_pair('W','S'));
    leftt.insert(make_pair('S','E'));
    leftt.insert(make_pair('E','N'));
    rightt.insert(make_pair('N','E'));
    rightt.insert(make_pair('E','S'));
    rightt.insert(make_pair('S','W'));
    rightt.insert(make_pair('W','N'));
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> A >> B;
    cin >> N >> M;
    set();
    for(int i = 1; i <= N; i++){
        cin >> robot[i].second.first >> robot[i].second.second >> robot[i].first;
        arr[robot[i].second.first][robot[i].second.second] = i;
    }
    for(int i = 1; i <= M; i++){
        if(force_exit == 1){
            return 0;
        }
        char dir;
        int rot, f;
        cin >> rot >> dir >> f;
        if(force_exit == 1){
            return 0;
        }
        if(dir == 'F'){
            move(rot,f);
        }
        if(dir == 'L'){
            turn_left(rot, f);
        }
        if(dir == 'R'){
            turn_right(rot, f);
        }
    }
    if(force_exit == 0){
        cout << "OK" << "\n";
    }
    return 0;
}
