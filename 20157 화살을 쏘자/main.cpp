//
//  main.cpp
//  F
//
//  Created by 이준영 on 2020/11/15.
//

#include <iostream>
#include <map>
#include <vector>
#include <algorithm>

using namespace std;

int N, cnt;
map<pair<int,int>,int> m;
vector<pair<int,int> > key;

int go(int num1, int num2){
    while(num2 != 0){
        int temp = num1%num2;
        num1 = num2;
        num2 = temp;
    }
    
    return num1;
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    for(int i = 1; i <= N; i++){
        long double x,y;
        cin >> x >> y;
        int l = go(x,y);
        l = max(l,-1*l);
        m.insert({{y/l,x/l},m[{y/l,x/l}]++});
        key.push_back({y/l,x/l});
    }
    for(int i = 0; i < key.size(); i++){
        cnt = max(cnt , m[key[i]]);
    }
    cout << cnt << "\n";
    return 0;
}
