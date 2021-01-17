//
//  main.cpp
//  5639 이진 검색 트리
//
//  Created by 이준영 on 2021/01/17.
//

#include <iostream>

using namespace std;
pair<int,int> tree[1000005];

void findIdx(int idx, int num){
    if(tree[idx].first == 0 and tree[idx].second == 0){
        tree[idx] = {num,num};
        return;
    }
    idx = tree[idx].first;
    while(true){
        if(idx < num){
            if(tree[idx].second == 0){
                tree[idx].second = num;
                break;
            }
            idx = tree[idx].second;
            continue;
        }
        if(idx > num){
            if(tree[idx].first == 0){
                tree[idx].first = num;
                break;
            }
            idx = tree[idx].first;
            continue;
        }
    }
    return;
}

void makeTree(){
    int num = 0;
    cin >> num;
    if(cin.eof() == true){
        return;
    }
    findIdx(0, num);
    makeTree();
}

void search(int idx){
    if(idx == 0){
        return;
    }
    search(tree[idx].first);
    search(tree[idx].second);
    cout << idx << "\n";
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    makeTree();
    search(tree[0].first);
    return 0;
}
