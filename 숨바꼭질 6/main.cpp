//
//  main.cpp
//  17087 숨바꼭질 6
//
//  Created by 이준영 on 2020/12/28.
//

#include <iostream>
#include <vector>
#include <algorithm>
#include <deque>

using namespace std;

int N, S;
vector<int> bro;
deque<int> num;

int getGCD(int a, int b){
    int r = 0;
    while(a != 0){
        r = b % a;
        b = a;
        a = r;
    }
    return b;
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> S;
    bro.push_back(S);
    for(int i = 1; i <= N; i++){
        int num;
        cin >> num;
        bro.push_back(num);
    }
    sort(bro.begin(),bro.end());
    for(int i = 0; i < bro.size()-1; i++){
        num.push_back(bro[i+1] - bro[i]);
    }
    while(num.size() > 1){
        int a = num.front();
        num.pop_front();
        int b = num.front();
        num.pop_front();
        num.push_back(getGCD(a,b));
    }
    cout << num.front() << "\n";
    return 0;
}
