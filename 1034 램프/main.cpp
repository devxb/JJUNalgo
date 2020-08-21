//
//  main.cpp
//  1034 램프
//
//  Created by 이준영 on 21/08/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <string>
#include <vector>
using namespace std;
int N, M, K;
int arr[55][55];
int temp[55][55];
int max_num;
void turn_on(int X){
    for(int i = 1; i <= N; i++){
        if(arr[i][X] == 1){
            arr[i][X] = 0;
            continue;
        }
        arr[i][X] = 1;
    }
    return;
}

int count_on(){
    int cnt = 0;
    for(int i = 1; i <= N; i++){
        int c = 0;
        for(int j = 1; j <= M; j++){
            if(arr[i][j] == 0){
                c = 1;
                break;
            }
        }
        if(c == 0){
            cnt++;
        }
    }
    return cnt;
}

void go(int Y){
    if(Y > N){
        return;
    }
    int off_cnt = 0;
    for(int j = 1; j <= M; j++){
        if(arr[Y][j] == 0){
            off_cnt++;
        }
    }
    if((off_cnt == K) or (off_cnt < K and ((K - off_cnt) % 2 == 0))){
        vector<int> vec;
        for(int j = 1; j <= M; j++){
            if(arr[Y][j] == 0){
                vec.push_back(j);
                turn_on(j);
            }
        }
        max_num = max(count_on(),max_num);
        for(int j = 0; j < vec.size(); j++){
            turn_on(vec[j]);
        }
    }
    go(Y+1);
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N >> M;
    for(int i = 1; i <= N; i++){
        string str;
        cin >> str;
        for(int j = 1; j <= M; j++){
            arr[i][j] = (int)str[j-1] - 48;
        }
    }
    cin >> K;
    go(1);
    cout << max_num << "\n";
    return 0;
}
