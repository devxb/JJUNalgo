//
//  main.cpp
//  19942 다이어트
//
//  Created by 이준영 on 2020/10/26.
//

#include <iostream>
#include <vector>
#include <utility>
#include <algorithm>

using namespace std;

int N,p,f,s,v;
vector<pair<pair<string,pair<int,int> >,pair<pair<int,int>,int > > > vec;
string arr[] = {"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17"};
vector<pair<int,pair<string,string> > > print;

string ps;
void go(int mp, int mf, int ms, int mv, int cost, int left, pair<string , string> str){
    if(mp >= p and mf >= f and ms >= s and mv >= v){
        print.push_back({cost,str});
        return;
    }
    for(int i = left+1; i < N; i++){
        string next_str = str.first + vec[i].first.first;
        string next;
        if(vec[i].first.first.size() == 2){
            next = str.second + '2';
        }
        else if(vec[i].first.first.size() == 1){
            next = str.second + '1';
        }
        go(mp+vec[i].first.second.first, mf+vec[i].first.second.second, ms+vec[i].second.first.first, mv+vec[i].second.first.second, cost+vec[i].second.second, i, {next_str,next});
    }
    return;
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    cin >> p >> f >> s >> v;
    for(int i = 1; i <= N; i++){
        int tp, tf, ts, tv, tc;
        cin >> tp >> tf >> ts >> tv >> tc;
        vec.push_back({{arr[i],{tp,tf}},{{ts,tv},tc}});
    }
    for(int i = 0; i < N; i++){
        string next_str = vec[i].first.first;
        string next;
        if(vec[i].first.first.size() == 2){
            next = '2';
        }
        else if(vec[i].first.first.size() == 1){
            next = '1';
        }
        go(vec[i].first.second.first, vec[i].first.second.second, vec[i].second.first.first, vec[i].second.first.second, vec[i].second.second, i, {next_str,next});
    }
    sort(print.begin(),print.end());
    if(print.size() == 0){
        cout << -1 << "\n";
        return 0;
    }
    cout << print.front().first << "\n";
    int j = 0;
    for(int i = 0; i < print.front().second.first.size(); i++){
        if(print.front().second.second[j] == '2'){
            cout << print.front().second.first[i] << print.front().second.first[i+1] << " ";
            j++;
            i++;
            continue;
        }
        cout << print.front().second.first[i] << " ";
        j++;
    }
    return 0;
}

