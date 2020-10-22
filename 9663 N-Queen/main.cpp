//
//  main.cpp
//  N-Queen
//
//  Created by 이준영 on 11/04/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <deque>
#include <utility>
using namespace std;
int N,print = 0;
int arr[20][20];
int X_check[20];
int Y_check[20];
int check_p[100];
int check_m[100];
int check[20][20];
deque<pair<int,int > > deq;

void go(int Queen){
    if(Queen > N){
        print++;
        return;
    }
    for(int i = 1; i <= N; i++){
            if(Y_check[i] == 0 and check_p[i+(Queen-1)] == 0 and check_m[Queen-(i-1)+15] == 0){
            Y_check[i] = 1;
            check_p[i+(Queen-1)] = 1;
            check_m[Queen-(i-1)+15] = 1;
            go(Queen + 1);
            Y_check[i] = 0;
            check_p[i+(Queen-1)] = 0;
            check_m[Queen-(i-1)+15] = 0;
        }
    }
    return;
}

int main() {
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N;
    go(1);
    cout << print << "\n";
    return 0;
}

