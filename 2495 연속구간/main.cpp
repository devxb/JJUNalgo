//
//  main.cpp
//  2495 연속구간
//
//  Created by 이준영 on 2020/12/23.
//

#include <iostream>
#include <string>
#include <algorithm>

using namespace std;
int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    for(int i = 1; i <= 3; i++){
        string str;
        cin >> str;
        int num = 1;
        int temp_num = 1;
        char rep = str[0];
        for(int j = 1; j < str.size(); j++){
            if(rep == str[j]){
                temp_num++;
                num = max(temp_num,num);
                continue;
            }
            temp_num = 1;
            rep = str[j];
        }
        cout << num << "\n";
    }
    return 0;
}
