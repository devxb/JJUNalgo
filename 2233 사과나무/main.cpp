//
//  main.cpp
//  2233 사과나무
//
//  Created by 이준영 on 04/08/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <string>
#include <vector>
#include <utility>

using namespace std;

int N, X, Y, cnt = 1;
string str;
int par[2005];
int arr[4005];

void go(int idx, int save, int parent){
    if(idx == str.size()){
        return;
    }
    if(str[idx] == '0'){
        arr[idx+1] = save;
        par[save] = parent;
        go(idx+1, save+1, save);
        return;
    }
    if(str[idx] == '1'){
        arr[idx+1] = parent;
        go(idx+1, save, par[parent]);
        return;
    }
}

int find(){
    for(int i = arr[X]; par[i] != -1; i = par[i]){
        for(int j = arr[Y]; par[j] != -1; j = par[j]){
            if(i == j){
                return j;
            }
        }
    }
    return 0;
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N >> str >> X >> Y;
    par[0] = -1;
    go(0,1,0);
    int del = find(), l = 0, j = 0;
    for(int i = 1; i <= N*2; i++){
        if(arr[i] == del){
            if(l == 0){
                l = i;
            }
            else{
                j = i;
                break;
            }
        }
    }
    cout << l << " " << j << "\n";
    return 0;
}
