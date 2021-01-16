//
//  main.cpp
//  1062 가르침
//
//  Created by 이준영 on 2021/01/15.
//

#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <utility>

using namespace std;
int N, K, check[30], sen;
char antic[] = {'a', 'n', 't', 'i', 'c'};
vector<string> vec;

void set(){
    for(int i = 0; i < 5; i++){
        check[(int)(antic[i] - 'a')] = 1;
    }
    return;
}

void study(int start, int words){
    if(words >= K){
        int num = 0;
        for(int i = 0; i < vec.size(); i++){
            bool learn = true;
            for(int j = 0; j < vec[i].size(); j++){
                if(check[(int)(vec[i][j] - 'a')] == 0){
                    learn = false;
                    break;
                }
            }
            if(learn == true){
                num++;
            }
        }
        sen = max(sen, num);
        return;
    }
    for(int i = start; i <= (int)('z' - 'a'); i++){
        if(check[i] != 0){
            continue;
        }
        check[i] = 1;
        study(i+1, words+1);
        check[i] = 0;
    }
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    set();
    cin >> N >> K;
    K -= 5;
    for(int i = 1; i <= N; i++){
        string str;
        cin >> str;
        vec.push_back(str);
    }
    if(K < 0){
        cout << 0 << "\n";
        return 0;
    }
    study(0, 0);
    cout << sen << "\n";
    return 0;
}
