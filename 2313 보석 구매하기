//
//  main.cpp
//  2313
//
//  Created by 이준영 on 03/08/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <utility>
#include <algorithm>
using namespace std;

int n, L, num;
pair<int,pair<int,int> > print[1005];
int arr[1005];
int dp[1005];
pair<int,pair<int,int> > go(int idx, int left, int right, pair<int,pair<int,int> > num){
    if(idx > L){
        return num;
    }
    if(arr[idx] >= dp[idx-1] + arr[idx]){
        dp[idx] = arr[idx];
        left = idx;
        right = idx;
    }
    else if(arr[idx] < dp[idx-1] + arr[idx]){
        dp[idx] = dp[idx-1] + arr[idx];
        right = idx;
    }
    if(num.first < dp[idx]){
        num = {dp[idx],{left,right}};
    }
    else if(num.first == dp[idx]){
        if(right - left + 1 < num.second.second - num.second.first + 1){
            num = {dp[idx],{left,right}};
        }
        else if(right - left + 1 == num.second.second - num.second.first + 1){
            if(num.second.first > left){
                num = {dp[idx],{left,right}};
            }
            else if(num.second.first == left and num.second.second > right){
                num = {dp[idx],{left,right}};
            }
        }
    }
    return go(idx+1, left, right, num);
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> n;
    for(int i = 1; i <= n; i++){
        cin >> L;
        for(int j = 1; j <= L; j++){
            cin >> arr[j];
        }
        print[i] = go(1,1,1,{arr[1],{1,1}});
        num = num + print[i].first;
    }
    cout << num << "\n";
    for(int i = 1; i <= n; i++){
        cout << print[i].second.first << " " << print[i].second.second << "\n";
    }
    return 0;
}
