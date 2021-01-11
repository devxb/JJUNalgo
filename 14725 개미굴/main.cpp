//
//  main.cpp
//  14725 개미굴
//
//  Created by 이준영 on 2021/01/11.
//

#include <iostream>
#include <string>
#include <vector>
#include <utility>
#include <algorithm>

using namespace std;

int N, K, sta, idx;

struct anthole{
    char c;
    bool finish;
    struct anthole *next[130] = {NULL, };
    anthole(char c, bool finish){
        this -> c = c;
        this -> finish = finish;
    }
};

anthole *exist(anthole *go, string str){
    int i = 0;
    while(true){
        idx = i;
        if(i == str.size()){
            break;
        }
        if(go -> next[(int)str[i]] != NULL){
            go = go -> next[(int)str[i]];
            i++;
        }
        else{
            break;
        }
    }
    return go;
}

void find(anthole *go, int layer, string str){
    if(go -> finish == true){
        for(int i = 1; i <= layer; i++){
            cout << "--";
        }
        cout << str << "\n";
        str = "";
        layer++;
    }
    for(int i = 1; i <= 127; i++){
        if(go -> next[i] != NULL){
            find(go -> next[i], layer, str + go -> next[i] -> c);
        }
    }
    return;
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    anthole *queen = new anthole('q', false);
    for(int i = 1; i <= N; i++){
        int K;
        cin >> K;
        anthole *ant = queen;
        for(int j = 0; j < K; j++){
            string str;
            cin >> str;
            idx = 0;
            ant = exist(ant, str);
            while(idx < str.size()){
                bool finish = false;
                if(idx == str.size()-1){
                    finish = true;
                }
                anthole *food = new anthole(str[idx], finish);
                ant -> next[(int)str[idx]] = food;
                ant = food;
                idx++;
            }
        }
    }
    find(queen, 0, "");
    return 0;
}
