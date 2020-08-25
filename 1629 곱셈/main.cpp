//
//  main.cpp
//  1629 곱셈
//
//  Created by 이준영 on 27/07/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>

using namespace std;

long long int A, B, C;

long long int go(long long int num, long long int squ){
    if(squ == 0){
        return 1;
    }
    if(squ == 1){
        return num;
    }
    if(squ % 2 == 0){
        return (go(num, squ / 2) * go(num, squ / 2)) % C;
    }
    else{
        return ((go(num, (squ-1) / 2) * go(num, (squ-1) / 2)) % C * num) % C;
    }
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> A >> B >> C;
    cout << go(A,B) % C << "\n";
    return 0;
}
