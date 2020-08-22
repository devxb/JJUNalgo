//
//  main.cpp
//  13325 이진트리
//
//  Created by 이준영 on 22/08/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <cmath>
#include <algorithm>

using namespace std;

long long int K;
long long int tree[5000000];
long long int dp[5000000];
long long int max_num;

void make_tree(long long int idx, long long int H){
    if(H > K){
        return;
    }
    for(long long int i = idx; i < idx + pow(2,H); i++){
        cin >> tree[i];
    }
    make_tree(idx*2, H+1);
    return;
}

long long int up(long long int idx, long long int H){
    if(H > K){
        return 0;
    }
    long long int left = up(idx * 2, H + 1);
    long long int right = up(idx * 2 + 1, H + 1);
    dp[idx] = max(left,right) + tree[idx];
    if(left <= right){
        tree[idx*2] = tree[idx*2] + right - left;
    }
    else{
        tree[idx*2+1] = tree[idx*2+1] + left - right;
    }
    return dp[idx];
}

void down(long long int idx, long long int H){
    max_num = max_num + tree[idx];
    if(H == K){
        return;
    }
    down(idx*2, H+1);
    down(idx*2+1, H+1);
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> K;
    make_tree(2,1);
    up(1,0);
    down(1,0);
    cout << max_num << "\n";
    return 0;
}
