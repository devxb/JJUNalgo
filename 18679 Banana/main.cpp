//
//  main.cpp
//  18679 Banana
//
//  Created by 이준영 on 17/08/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <string>
using namespace std;
string str[2500];
int N, T;

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N;
    for(int i = 1; i <= N; i++){
        string a;
        string b;
        char equal;
        cin >> a;
        int num = 0;
        for(int i = 0; i < a.size(); i++){
            num = num + (int)a[i];
        }
        cin >> equal;
        cin >> b;
        str[num] = b;
    }
    cin >> T;
    for(int i = 1; i <= T; i++){
        int K;
        cin >> K;
        string print;
        for(int j = 1; j <= K; j++){
            string a;
            cin >> a;
            int num = 0;
            for(int l = 0; l < a.size(); l++){
                num = num + (int)a[l];
            }
            print = print + str[num];
            if(j != K){
                print = print + " ";
            }
        }
        cout << print << "\n";
    }
    return 0;
}
