//
//  main.cpp
//  14754 Pizza Boxes
//
//  Created by 이준영 on 10/08/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <algorithm>
#include <utility>

using namespace std;

int arr[1005][1005];
int check[1005][1005];
int n,m;
long long int print_num,max_num;
int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> n >> m;
    for(int i = 1; i <= n; i++){
        for(int j = 1; j <= m; j++){
            cin >> arr[i][j];
            print_num = print_num + arr[i][j];
        }
    }
    for(int i = 1; i <= n; i++){
        pair<long long int,pair<int,int> > num = {0,{0,0}};
        for(int j = 1; j <= m; j++){
            if(num.first < arr[i][j]){
                num.first = arr[i][j];
                num.second.first = i;
                num.second.second = j;
            }
        }
        if(check[num.second.first][num.second.second] == 0){
            check[num.second.first][num.second.second] = 1;
            max_num = max_num + num.first;
        }
    }
    for(int i = 1; i <= m; i++){
        pair<long long int,pair<int,int> > num = {0,{0,0}};
        for(int j = 1; j <= n; j++){
            if(num.first < arr[j][i]){
                num.first = arr[j][i];
                num.second.first = j;
                num.second.second = i;
            }
        }
        if(check[num.second.first][num.second.second] == 0){
            check[num.second.first][num.second.second] = 1;
            max_num = max_num + num.first;
        }
    }
    cout << print_num - max_num << "\n";
    return 0;
}
