//
//  main.cpp
//  12933 오리
//
//  Created by 이준영 on 11/09/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <algorithm>
#include <utility>
#include <string>
#include <deque>

using namespace std;

string str;
deque<int> deq;
int check[2505], duck;
int sound[] = {'q', 'u', 'a', 'c', 'k'};

void go(int quack, int idx, int c){
    if(idx >= str.size()){
        return;
    }
    if(str[idx] == sound[quack] and check[idx] == 0){
        deq.push_back(idx);
        if(sound[quack] == 'k'){
            if(c == 0){
                duck = duck + 1;
                c = 1;
            }
            while(!deq.empty()){
                check[deq.front()] = 1;
                deq.pop_front();
            }
        }
        if(quack == 4){
            quack = 0;
        }
        else{
            quack = quack + 1;
        }
    }
    go(quack, idx+1, c);
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> str;
    for(int i = 0; i < str.size(); i++){
        if(str[i] == 'q' and check[i] != 1){
            go(0, i, 0);
        }
    }
    for(int i = 0; i < str.size(); i++){
        if(check[i] == 0){
            cout << -1 << "\n";
            return 0;
        }
    }
    if(duck == 0 or str.size() % 5 != 0){
        cout << -1 << "\n";
        return 0;
    }
    cout << duck << "\n";
    return 0;
}
