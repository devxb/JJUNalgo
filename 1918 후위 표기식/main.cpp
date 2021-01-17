//
//  main.cpp
//  1918 후위 표기식
//
//  Created by 이준영 on 2021/01/17.
//

#include <iostream>
#include <string>
#include <deque>
#include <utility>
#include <algorithm>
using namespace std;
string str;
int check[105];

void calc(int left, int right){
    for(int i = left; i <= right; i++){
        if(check[i] != 0){
            continue;
        }
        if(str[i] == '*' or str[i] == '/'){
            int change = i+1;
            check[i-1] = 1;
            while(true){
                if(change == str.size()){
                    break;
                }
                char a = str[change-1];
                str[change-1] = str[change];
                str[change] = a;
                check[change] = 1;
                check[change-1] = 1;
                change++;
                if(check[change] == 0){
                    break;
                }
            }
        }
    }
    for(int i = left; i <= right; i++){
        if(check[i] != 0){
            continue;
        }
        if(str[i] == '+' or str[i] == '-'){
            int change = i+1;
            check[i-1] = 1;
            while(true){
                if(change == str.size()){
                    break;
                }
                char a = str[change-1];
                str[change-1] = str[change];
                str[change] = a;
                check[change] = 1;
                check[change-1] = 1;
                change++;
                if(check[change] == 0){
                    break;
                }
            }
        }
    }
    return;
}

void find(int left, int right){
    for(int i = left; i <= right; i++){
        if(str[i] == '('){
            check[i] = 1;
            int nl = i;
            int nlnum = 1;
            while(true){
                i++;
                if(str[i] == '('){
                    nlnum++;
                }
                if(str[i] == ')' and check[i] == 0){
                    nlnum--;
                }
                if(nlnum == 0 or i == str.size()-1){
                    break;
                }
            }
            check[i] = 1;
            find(nl+1, i-1);
        }
    }
    calc(left, right);
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> str;
    find(0, str.size()-1);
    for(int i = 0; i < str.size(); i++){
        if(str[i] == '(' or str[i] == ')'){
            continue;
        }
        cout << str[i];
    }
    return 0;
}
