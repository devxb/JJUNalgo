//
//  main.cpp
//  9935 문자열 폭발
//
//  Created by 이준영 on 2021/01/15.
//

#include <iostream>
#include <string>
#include <algorithm>
#include <deque>

using namespace std;

string str, bomb;
deque<char> deq;

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> str >> bomb;
    for(int i = 0; i < str.size(); i++){
        deq.push_back(str[i]);
        if(deq.size() >= bomb.size() and str[i] == bomb[bomb.size()-1]){
            bool boom = true;
            for(int j = 1; j <= bomb.size(); j++){
                if(bomb[bomb.size() - j] != deq[deq.size() - j]){
                    boom = false;
                }
            }
            if(boom == true){
                for(int j = 0; j < bomb.size(); j++){
                    deq.pop_back();
                }
            }
        }
    }
    if(deq.size() == 0){
        cout << "FRULA" << "\n";
        return 0;
    }
    for(int i = 0; i < deq.size(); i++){
        cout << deq[i];
    }
    cout << "\n";
    return 0;
}
