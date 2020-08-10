//
//  main.cpp
//  ALCUK
//
//  Created by 이준영 on 06/04/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <deque>

using namespace std;

int N,K,L;
int arr[200][200];
int check[200][200];
char time_check[100005];
deque<int> move_Y;
deque<int> move_X;

void clear(){
    for(int i = 0; i <= 100005; i++){
        time_check[i] = 'A';
    }
}
void print(){
    for(int i = 1; i <= 10; i++){
        for(int j = 1; j <= 10; j++){
            cout << check[i][j] << " ";
        }
        cout << "\n";
    }
}
int main() {
    clear();
    scanf("%d",&N);
    scanf("%d",&K);
    for(int i = 0; i < K; i++){
        int Y,X;
        scanf("%d %d", &Y, &X);
        arr[Y][X] = 1;
    }
    scanf("%d",&L);
    for(int i = 0; i < L; i++){
        int count;
        char a;
        scanf("%d %c", &count, &a);
        time_check[count] = a;
    }
    int t = 0;
    char go = 'E';
    move_Y.push_back(1);
    move_X.push_back(1);
    check[1][1] = 2;
    while(true){
        if(time_check[t] == 'L'){
            if(go == 'E'){
                go = 'N';
            }
            else if(go == 'W'){
                go = 'S';
            }
            else if(go == 'S'){
                go = 'E';
            }
            else if(go == 'N'){
                go = 'W';
            }
        }
        else if(time_check[t] == 'D'){
            if(go == 'E'){
                go = 'S';
            }
            else if(go == 'W'){
                go = 'N';
            }
            else if(go == 'S'){
                go = 'W';
            }
            else if(go == 'N'){
                go = 'E';
            }
        }
        if(go == 'E'){
            move_Y.push_front(move_Y.front());
            move_X.push_front(move_X.front()+1);
        }
        else if(go == 'W'){
            move_Y.push_front(move_Y.front());
            move_X.push_front(move_X.front()-1);
        }
        else if(go == 'S'){
            move_Y.push_front(move_Y.front()+1);
            move_X.push_front(move_X.front());
        }
        else if(go == 'N'){
            move_Y.push_front(move_Y.front()-1);
            move_X.push_front(move_X.front());
        }
        t++;
        int head_Y = move_Y.front();
        int head_X = move_X.front();
        int tail_Y = move_Y.back();
        int tail_X = move_X.back();
        if(check[head_Y][head_X] == 2 || head_Y > N || head_X > N || head_Y < 1 || head_X < 1){
            break;
        }
        check[head_Y][head_X] = 2;
        if(arr[head_Y][head_X] == 0){
            check[tail_Y][tail_X] = 0;
            move_Y.pop_back();
            move_X.pop_back();
        }
        else if(arr[head_Y][head_X] == 1){
            arr[head_Y][head_X] = 0;
        }
    }
    printf("%d\n",t);
    return 0;
}
