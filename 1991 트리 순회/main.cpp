//
//  main.cpp
//  1991 트리 순회
//
//  Created by 이준영 on 2021/01/17.
//

#include <iostream>
#include <deque>

using namespace std;
int N;
char types[] = {'f','m','b'};
pair<int ,int> tree[30];

void search(int idx, int type){
    if((char)(idx + (int)'A') == '.'){
        return;
    }
    if(type == 'f'){
        cout << (char)(idx + (int)'A');
    }
    search(tree[idx].first, type);
    if(type == 'm'){
        cout << (char)(idx + (int)'A');
    }
    search(tree[idx].second, type);
    if(type == 'b'){
        cout << (char)(idx + (int)'A');
    }
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    for(int i = 1; i <= N; i++){
        char now, left, right;
        cin >> now >> left >> right;
        tree[(int)(now - 'A')] = {(int)(left - 'A'), (int)(right - 'A')};
    }
    for(int i = 0; i < 3; i++){
        search(0, types[i]);
        cout << "\n";
    }
    return 0;
}
