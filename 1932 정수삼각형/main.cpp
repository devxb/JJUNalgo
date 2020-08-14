//
//  main.cpp
//  1932 정수 삼각형
//
//  Created by 이준영 on 28/07/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

pair<int,unsigned long long int> tree[100000*4];
unsigned long long int num, print_num;
int N;

void make_tree(int idx, int layer){
    if(layer > N){
        return;
    }
    for(int i = idx; i < idx + layer; i++){
        cin >> num;
        tree[i].first = layer;
        if(tree[i-(layer-1)].first == layer - 1){
            tree[i].second = max(tree[i-(layer-1)].second + num, tree[i].second);
        }
        if(tree[i-layer].first == layer - 1){
            tree[i].second = max(tree[i-layer].second + num, tree[i].second);
        }
        print_num = max(print_num,tree[i].second);
    }
    make_tree(idx+layer, layer+1);
}


int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N;
    make_tree(1, 1);
    cout << print_num << "\n";
    return 0;
}
