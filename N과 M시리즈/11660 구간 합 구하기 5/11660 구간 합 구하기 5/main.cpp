//
//  main.cpp
//  11660 구간 합 구하기 5
//
//  Created by 이준영 on 2021/01/02.
//
#include <iostream>

using namespace std;

int N, M;
int arr[1024+5][1024+5], seg[1024+5][1024*4+5];

int makeSeg(int left, int right, int idx, int layer){
    if(left >= right){
        return seg[layer][idx] = arr[layer][left];
    }
    int mid = (left + right) / 2;
    return seg[layer][idx] = makeSeg(left, mid, idx*2, layer) + makeSeg(mid+1, right, idx*2+1, layer);
}

int findSeg(int left, int right, int idx, int layer, int fl, int fr){
    if(fl <= left and right <= fr){
        return seg[layer][idx];
    }
    if(left > fr or right < fl){
        return 0;
    }
    int mid = (left + right) / 2;
    return findSeg(left, mid, idx*2, layer, fl, fr) + findSeg(mid+1, right, idx*2+1, layer, fl, fr);
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> M;
    for(int i = 1; i <= N; i++){
        for(int j = 1; j <= N; j++){
            cin >> arr[i][j];
        }
    }
    for(int i = 1; i <= N; i++){
        makeSeg(1, N, 1, i);
    }
    for(int i = 1; i <= M; i++){
        int x1, y1, x2, y2;
        cin >> x1 >> y1 >> x2 >> y2;
        int num = 0;
        for(int j = x1; j <= x2; j++){
            num += findSeg(1, N, 1, j, y1, y2);
        }
        cout << num << "\n";
        
    }
    return 0;
}

