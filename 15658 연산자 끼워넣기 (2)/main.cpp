//
//  main.cpp
//  15658 연산자 끼워넣기 (2)
//
//  Created by 이준영 on 14/09/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;

int N;
int num[15];
int ope[10], check[15];
char oper[] = {'?', '+', '-', '*', '/'};
int max_num = -2000000000, min_num = 2000000000;

void go(int now_num, int idx){
    if(idx == N){
        max_num = max(max_num, now_num);
        min_num = min(min_num, now_num);
        return;
    }
    for(int j = 1; j <= 4; j++){
        if(ope[j] > 0){
            ope[j] = ope[j]-1;
            if(oper[j] == '+'){
                go(now_num + num[idx+1], idx+1);
            }
            else if(oper[j] == '-'){
                go(now_num - num[idx+1], idx+1);
            }
            else if(oper[j] == '*'){
                go(now_num * num[idx+1], idx+1);
            }
            else if(oper[j] == '/'){
                if(now_num < 0){
                    int temp = now_num;
                    temp = temp * -1;
                    temp = temp / num[idx+1];
                    temp = temp * -1;
                    go(temp, idx+1);
                }
                go(now_num / num[idx+1], idx+1);
            }
            ope[j] = ope[j]+1;
        }
    }
    
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N;
    for(int i = 1; i <= N; i++){
        cin >> num[i];
    }
    for(int i = 1; i <= 4; i++){
        cin >> ope[i];
    }
    go(num[1], 1);
    cout << max_num << "\n" << min_num << "\n";
    return 0;
}
