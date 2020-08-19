//
//  main.cpp
//  1241 머리 톡톡
//
//  Created by 이준영 on 19/08/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <vector>
#include <cmath>

using namespace std;
int N;
int arr[1000005];
int check[1000005];
vector<int> vec;

int go(int num){
    int re_num = 0;
    for(int i = 1; i <= sqrt(num); i++){
        if(num % i == 0){
            re_num = re_num + arr[i];
            if(num / i != i){
                re_num = re_num + arr[num / i];
            }
        }
    }
    return re_num;
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N;
    for(int i = 1; i <= N; i++){
        int num;
        cin >> num;
        arr[num]++;
        vec.push_back(num);
    }
    for(int i = 0; i < N; i++){
        if(check[vec[i]] != 0){
            cout << check[vec[i]] << "\n";
            continue;
        }
        check[vec[i]] = go(vec[i]) - 1;
        cout << check[vec[i]] << "\n";
    }
    return 0;
}
