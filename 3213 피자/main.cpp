//
//  main.cpp
//  3213 피자
//
//  Created by 이준영 on 2020/10/05.
//

#include <iostream>
#include <string>
#include <algorithm>
#include <deque>

using namespace std;

int N;

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N;
    deque<int> deq1, deq2, deq3;
    string str;
    for(int i = 1; i <= N; i++){
        cin >> str;
        if(str[0] == '1' and str[2] == '2'){
            deq1.push_back(1);
        }
        else if(str[0] == '1' and str[2] == '4'){
            deq2.push_back(1);
        }
        else{
            deq3.push_back(1);
        }
    }
    int num = 0;
    while(!deq2.empty() and !deq3.empty()){
        deq2.pop_front();
        deq3.pop_front();
        num++;
    }
    while(!deq2.empty() and !deq1.empty()){
        deq2.pop_front();
        if(deq2.empty()){
            deq1.pop_front();
            num++;
            break;
        }
        deq2.pop_front();
        deq1.pop_front();
        num++;
    }
    while(!deq3.empty()){
        deq3.pop_front();
        num++;
    }
    while(!deq2.empty()){
        deq2.pop_front();
        num++;
    }
    while(!deq1.empty()){
        deq1.pop_front();
        if(deq1.empty()){
            num++;
            break;
        }
        deq1.pop_front();
        num++;
    }
    cout << num << "\n";
    return 0;
}
