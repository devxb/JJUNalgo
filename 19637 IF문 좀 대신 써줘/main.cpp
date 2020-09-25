//
//  main.cpp
//  19637 IF문 좀 대신 써줘
//
//  Created by 이준영 on 2020/09/25.
//

#include <iostream>
#include <vector>
#include <utility>
#include <string>
#include <algorithm>

using namespace std;

int N, M;
vector<int> vec;
vector<string> vec_str;

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N >> M;
    int last_num = -1;
    for(int i = 1; i <= N; i++){
        string str;
        int num;
        cin >> str >> num;
        if(num == last_num){
            continue;
        }
        last_num = num;
        vec.push_back(num);
        vec_str.push_back(str);
    }
    for(int i = 1; i <= M; i++){
        int num;
        cin >> num;
        cout << vec_str[lower_bound(vec.begin(),vec.end(), num) - vec.begin()] << "\n";
    }
    return 0;
}
