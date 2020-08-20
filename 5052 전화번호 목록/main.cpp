//
//  main.cpp
//  5052 전화번호 목록
//
//  Created by 이준영 on 27/07/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

int t, n, check;

struct Number{
    int rock;
    int num;
    struct Number *next_number[15];
};

void put(struct Number *find, string num, int idx){
    if(check == 1 or idx == num.size()){
        return;
    }
    if(find -> rock == -1){
        check = 1;
        return;
    }
    if(find -> next_number[(int)num[idx] - 48] == 0){
        Number *New_number = new Number();
        New_number -> num = (int)num[idx] - 48;
        find -> next_number[(int)num[idx] - 48] = New_number;
        if(idx == num.size()-1){
            New_number -> rock = -1;
            return;
        }
        put(New_number, num, idx+1);
    }
    else{
        put(find -> next_number[(int)num[idx] - 48], num, idx+1);
    }
}

void del(struct Number *find){
    for(int i = 0; i < 10; i++){
        if(find -> next_number[i] != 0){
            del(find -> next_number[i]);
        }
    }
    delete find;
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> t;
    for(int i = 1; i <= t; i++){
        Number *start = new Number();
        start -> num = 0;
        check = 0;
        vector<string> str;
        cin >> n;
        for(int j = 1; j <= n; j++){
            string tmp;
            cin >> tmp;
            str.push_back(tmp);
        }
        sort(str.begin(),str.end());
        for(int j = 0; j < n; j++){
            put(start, str[j], 0);
        }
        if(check == 1){
            cout << "NO" << "\n";
        }
        else{
            cout << "YES" << "\n";
        }
        del(start);
    }
    return 0;
}
