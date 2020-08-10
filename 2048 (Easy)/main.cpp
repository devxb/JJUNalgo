//
//  main.cpp
//  2048
//
//  Created by 이준영 on 07/04/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
using namespace std;

int N,max_num,max_temp;
int board[25][25];
int temp[25][25][20];
int arr[25];
int max_check[15];
void up(){
    for(int j = 1; j <= N; j++){
        for(int l = 1; l <= N; l++){
            if(board[j][l] == 0){
                continue;
            }
            int now_y = j;
            int now_x = l;
            int temp_num = board[j][l];
            while(true){
                if(now_y == 1 or board[now_y-1][now_x] != 0){
                    break;
                }
                now_y = now_y - 1;
            }
            board[j][l] = 0;
            board[now_y][now_x] = temp_num;
        }
    }
}

void down(){
    for(int j = N; j >= 1; j--){
        for(int l = 1; l <= N; l++){
            if(board[j][l] == 0){
                continue;
            }
            int now_y = j;
            int now_x = l;
            int temp_num = board[j][l];
            while(true){
                if(now_y == N or board[now_y+1][now_x] != 0){
                    break;
                }
                now_y = now_y + 1;
            }
            board[j][l] = 0;
            board[now_y][now_x] = temp_num;
        }
    }
}

void right(){
    for(int j = 1; j <= N; j++){
        for(int l = N; l >= 1; l--){
            if(board[j][l] == 0){
                continue;
            }
            int now_y = j;
            int now_x = l;
            int temp_num = board[j][l];
            while(true){
                if(now_x == N or board[now_y][now_x+1] != 0){
                    break;
                }
                now_x = now_x + 1;
            }
            board[j][l] = 0;
            board[now_y][now_x] = temp_num;
        }
    }
}

void left(){
    for(int j = 1; j <= N; j++){
        for(int l = 1; l <= N; l++){
            if(board[j][l] == 0){
                continue;
            }
            int now_y = j;
            int now_x = l;
            int temp_num = board[j][l];
            while(true){
                if(now_x == 1 or board[now_y][now_x-1] != 0){
                    break;
                }
                now_x = now_x - 1;
            }
            board[j][l] = 0;
            board[now_y][now_x] = temp_num;
        }
    }
}

void go(int i){
    if(i == 1){
        up();
        for(int j = 1; j <= N; j++){
            for(int l = 1; l <= N; l++){
                if(board[j][l] == board[j+1][l]){
                    board[j][l] = board[j][l] + board[j+1][l];
                    board[j+1][l] = 0;
                }
            }
        }
        up();
    }
    if(i == 2){
        down();
        for(int j = N; j >= 1; j--){
            for(int l = 1; l <= N; l++){
                if(board[j][l] == board[j-1][l]){
                    board[j][l] = board[j][l] + board[j-1][l];
                    board[j-1][l] = 0;
                }
            }
        }
        down();
    }
    if(i == 3){
        right();
        for(int j = 1; j <= N; j++){
            for(int l = N; l >= 1; l--){
                if(board[j][l] == board[j][l-1]){
                    board[j][l] = board[j][l] + board[j][l-1];
                    board[j][l-1] = 0;
                }
            }
        }
        right();
    }
    if(i == 4){
        left();
        for(int j = 1; j <= N; j++){
            for(int l = 1; l <= N; l++){
                if(board[j][l] == board[j][l+1]){
                    board[j][l] = board[j][l] + board[j][l+1];
                    board[j][l+1] = 0;
                }
            }
        }
        left();
    }
}

int find_max(){
    int max = 0;
    for(int i = 1; i <= N; i++){
        for(int j = 1; j <= N; j++){
            if(board[i][j] > max){
                max = board[i][j];
            }
        }
    }
    return max;
}

void make_check(int idx){
    max_check[idx] = max_temp;
    for(int i = idx-1; i > 0; i--){
        max_check[i] = max_check[i+1] / 2;
    }
    return;
}

void return_board(int idx){
    for(int i = 1; i <= N; i++){
        for(int j = 1; j <= N; j++){
            board[i][j] = temp[i][j][idx];
        }
    }
}

void make_board(int idx){
    for(int i = 1; i <= N; i++){
        for(int j = 1; j <= N; j++){
            temp[i][j][idx] = board[i][j];
        }
    }
}

void make(int cnt){
    max_temp = find_max();
    if(max_temp <= max_check[cnt]){
        return;
    }
    if(cnt == 6){
        if(max_num < max_temp){
            max_num = max_temp;
        }
        if(max_temp > max_check[cnt]){
            make_check(10);
        }
        return;
    }
    make_board(cnt);
    for(int i = 1; i <= 4; i++){
        go(i);
        int c = 0;
        for(int j = 1; j <= N; j++){
            for(int l = 1; l <= N; l++){
                if(temp[j][l][cnt] != board[j][l]){
                    c = 1;
                    break;
                }
            }
            if(c == 1){
                break;
            }
        }
        if(c == 1){
            make(cnt+1);
            return_board(cnt);
        }
    }
    return;
}

int main() {
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N;
    for(int i = 1; i <= N; i++){
        for(int j = 1; j <= N; j++){
            cin >> board[i][j];
            if(board[i][j] > max_num){
                max_num = board[i][j];
            }
        }
    }
    for(int i = 1; i <= 11; i++){
        max_check[i] = -1;
    }
    make(1);
    cout << max_num << "\n";
    return 0;
}

