//
//  main.cpp
//  6503 망가진 키보드
//
//  Created by 이준영 on 2021/01/13.
//

#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    int m = -1;
    cin >> m;
    while(m != 0){
        string str;
        int check[205] = {0, }, num = 0;
        getline(cin, str);
        getline(cin, str);
        int lp = 0, rp = 0, cnt = 1, length = 1;
        check[(int)str[lp]] = 1;
        while(lp < str.size() and rp < str.size()){
            while(cnt < m and lp < str.size() and rp < str.size()-1){
                rp++;
                length++;
                if(check[(int)str[rp]] < 1){
                    check[(int)str[rp]] = 1;
                    cnt++;
                }
                else{
                    check[(int)str[rp]]++;
                }
            }
            while(true){
                if(rp+1 >= str.size() or check[(int)str[rp+1]] == 0){
                    break;
                }
                if(check[(int)str[rp+1]] > 0){
                    check[(int)str[rp+1]]++;
                    length++;
                    rp++;
                }
            }
            num = max(num, length);
            check[(int)str[lp]]--;
            if(check[(int)str[lp]] == 0){
                cnt--;
            }
            lp++;
            length--;
        }
        cout << num << "\n";
        cin >> m;
    }
    return 0;
}
