//
//  main.cpp
//  5635 생일
//
//  Created by 이준영 on 09/09/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<pair<pair<int,int>,pair<int,string>>> vec;
int N;

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N;
    for(int i = 1; i <= N; i++){
        string str;
        int day, month, year;
        cin >> str >> day >> month >> year;
        vec.push_back({{year, month},{day, str}});
    }
    sort(vec.begin(),vec.end());
    cout << vec[vec.size()-1].second.second  << "\n" << vec[0].second.second<< "\n";
    return 0;
}
