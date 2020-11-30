//
//  main.cpp
//  19591 독특한 계산기
//
//  Created by 이준영 on 2020/11/01.
//

#include <iostream>
#include <string>
#include <vector>
#include <deque>

using namespace std;

string str;

deque <long long> num;
deque <char> oper;

long long calc(long long num1, long long num2, char cal){
    if(cal == '-'){
        return num1 - num2;
    }
    if(cal == '+'){
        return num1 + num2;
    }
    if(cal == '*'){
        return num1 * num2;
    }
    if(cal == '/'){
        return num1 / num2;
    }
    return 0;
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> str;
    string temp = "";
    for(int i = 0; i < str.size(); i++){
        if(str[i] == '-' and i == 0){
            temp.push_back('-');
            continue;
        }
        if(str[i] == '-' or str[i] == '+' or str[i] == '/' or str[i] == '*'){
            int minus = 0;
            long long gop = 0;
            for(int j = 0; j < temp.size(); j++){
                if(temp[j] == '-'){
                    minus = 1;
                    continue;
                }
                gop = gop * 10 + ((int)temp[j]-48);
            }
            if(minus == 1){
                num.push_back(gop*-1);
            }
            else{
                num.push_back(gop);
            }
            temp = "";
            oper.push_back(str[i]);
            continue;
        }
        temp.push_back(str[i]);
    }
    if(temp.size() >= 1){
        int minus = 0;
        long long gop = 0;
        for(int j = 0; j < temp.size(); j++){
            if(temp[j] == '-'){
                minus = 1;
                continue;
            }
            gop = gop * 10 + ((int)temp[j]-48);
        }
        if(minus == 1){
            num.push_back(gop*-1);
        }
        else{
            num.push_back(gop);
        }
    }
    while(true){
        if(num.size() == 1){
            cout << num.front() << "\n";
            break;
        }
        if((oper.front() == '*' or oper.front() == '/') and (oper.back() == '*' or oper.back() == '/')){
            long long left_num = calc(num[0], num[1], oper.front());
            long long right_num = calc(num[num.size()-2], num[num.size()-1], oper.back());
            if(left_num >= right_num){
                num.pop_front();
                num.pop_front();
                num.push_front(left_num);
                oper.pop_front();
            }
            else{
                num.pop_back();
                num.pop_back();
                num.push_back(right_num);
                oper.pop_back();
            }
        }
        else if((oper.front() == '-' or oper.front() == '+') and (oper.back() == '-' or oper.back() == '+')){
            long long left_num = calc(num[0], num[1], oper.front());
            long long right_num = calc(num[num.size()-2], num[num.size()-1], oper.back());
            if(left_num >= right_num){
                num.pop_front();
                num.pop_front();
                num.push_front(left_num);
                oper.pop_front();
            }
            else{
                num.pop_back();
                num.pop_back();
                num.push_back(right_num);
                oper.pop_back();
            }
        }
        else if((oper.front() == '*' or oper.front() == '/') and (oper.back() == '+' or oper.back() == '-')){
            long long left_num = calc(num[0], num[1], oper.front());
            num.pop_front();
            num.pop_front();
            num.push_front(left_num);
            oper.pop_front();
        }
        else if((oper.front() == '-' or oper.front() == '+') and (oper.back() == '*' or oper.back() == '/')){
            long long right_num = calc(num[num.size()-2], num[num.size()-1], oper.back());
            num.pop_back();
            num.pop_back();
            num.push_back(right_num);
            oper.pop_back();
        }
    }
    return 0;
}
