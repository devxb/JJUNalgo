//
//  main.cpp
//  17349 1루수가 누구야
//
//  Created by 이준영 on 07/08/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <utility>
#include <vector>

using namespace std;

pair <int,int> arr[15];
int check[15];
vector<int> vec;
int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    cin.tie(NULL);
    cout.tie(NULL);
    for(int i = 1; i <= 9; i++){
        cin >> arr[i].first >> arr[i].second;
    }
    for(int i = 1; i <= 9; i++){
        for(int j = 1; j <= 9; j++){
            check[j] = 0;
        }
        if(arr[i].first == 1){
            check[arr[i].second] = 1;
        }
        if(arr[i].first == 0){
            check[arr[i].second] = 2;
        }
        for(int j = 1; j <= 9; j++){
            if(i == j){
                continue;
            }
            if(arr[i].first == arr[j].first and arr[i].second == arr[j].second){
                for(int l = 1; l <= 9; l++){
                    check[l] = 1;
                }
                break;
            }
            if(arr[j].first == 0){
                check[arr[j].second] = 1;
            }
            if(arr[j].first == 1){
                check[arr[j].second] = 2;
            }
        }
        int c = 0, num, c2 = 0;
        for(int j = 1; j <= 9; j++){
            if(check[j] == 2){
                c2++;
                num = j;
            }
        }
        if(c2 == 1){
            vec.push_back(num);
        }
        if(c2 < 1){
            for(int j = 1; j <= 9; j++){
                if(check[j] == 0){
                    vec.push_back(j);
                }
            }
        }
    }
    if(vec.size() == 1){
        cout << vec.front() << "\n";
        return 0;
    }
    cout << -1 << "\n";
    return 0;
}
