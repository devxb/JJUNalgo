//
//  main.cpp
//  마피아
//
//  Created by 이준영 on 07/04/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <algorithm>
#include <vector>
#include <deque>
#include <utility>

using namespace std;
int N,mafia;
int player[25];
int check_player[25];
int R[25][25];
int print = -99999999;
void go(int player_num, int num){
    if(player_num == 1){
        if(print < num){
            print = num;
        }
        return;
    }
    if(check_player[mafia] == -1){
        if(print < num){
            print = num;
        }
        return;
    }
    if(player_num % 2 == 0){
        num = num + 1;
        for(int i = 0; i < N; i++){
            if(check_player[i] != -1 and i != mafia){
                for(int j = 0; j < N; j++){
                    player[j] = player[j] + R[i][j];
                }
                check_player[i] = -1;
                go(player_num - 1, num);
                check_player[i] = 0;
                for(int j = 0; j < N; j++){
                    player[j] = player[j] - R[i][j];
                }
            }
        }
    }
    else if(player_num % 2 != 0){
        int max_num = -99999999,j = -1;
        for(int i = 0; i < N; i++){
            if(player[i] > max_num and check_player[i] != -1){
                max_num = player[i];
                j = i;
            }
        }
        check_player[j] = -1;
        go(player_num - 1, num);
        check_player[j] = 0;
    }
}
int main() {
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N;
    for(int i = 0; i < N; i++){
        cin >> player[i];
    }
    for(int i = 0; i < N; i++){
        for(int j = 0; j < N; j++){
            cin >> R[i][j];
        }
    }
    cin >> mafia;
    go(N,0);
    cout << print << "\n";
    return 0;
}
