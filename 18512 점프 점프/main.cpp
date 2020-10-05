//
//  main.cpp
//  18512 점프 점프
//
//  Created by 이준영 on 2020/10/05.
//

#include <iostream>

using namespace std;

int X, Y, P1, P2;
int check1[10000205];

void find_LCM(int num1, int num2){
    for(int i = 0; i <= 100000; i++){
        check1[num1 + (X*i)] = 1;
    }
    for(int i = 0; i <= 100000; i++){
        if(check1[num2 + (Y*i)] == 1){
            cout << num2 + (Y*i) << "\n";
            return;
        }
    }
    cout << -1 << "\n";
    return;
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> X >> Y >> P1 >> P2;
    find_LCM(P1,P2);
    return 0;
}
