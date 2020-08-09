//
//  main.cpp
//  1027 고층 건물
//
//  Created by 이준영 on 10/04/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <algorithm>
#include <cmath>

using namespace std;
long long int N;
double max_num,now_num;
long long int arr[55];
long long int see[55];
void go(int A, int B){
    if(B > N){
        return;
    }
    now_num = (double)(arr[B] - arr[A]) / (B - A);
    if(now_num > max_num){
        max_num = now_num;
        see[A] = see[A] + 1;
        see[B] = see[B] + 1;
    }
    go(A,B+1);
}

int main() {
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N;
    for(int i = 1; i <= N; i++){
        cin >> arr[i];
    }
    for(int i = 1; i < N; i++){
        max_num = -2000000000;
        go(i,i+1);
    }
    long long int print = 0;
    for(int i = 1; i <= N; i++){
        print = max(print,see[i]);
    }
    cout << print << "\n";
    return 0;
}
