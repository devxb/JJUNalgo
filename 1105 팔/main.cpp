//
//  main.cpp
//  1105 팔
//
//  Created by 이준영 on 19/08/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <string>

using namespace std;
string L, R;
int num = 0;

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> L >> R;
    if(R.size() > L.size()){
        cout << 0 << "\n";
        return 0;
    }
    for(int i = 0; i < R.size(); i++){
        if(R[i] == L[i] and R[i] == '8'){
            num++;
            continue;
        }
        if(R[i] != L[i]){
            break;
        }
    }
    cout << num << "\n";
    return 0;
}
