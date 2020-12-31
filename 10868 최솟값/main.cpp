//
//  main.cpp
//  10868 최솟값
//
//  Created by 이준영 on 2020/12/31.
//

#include <iostream>
#include <algorithm>

using namespace std;

int N, M, arr[100005], seg[100005*4+5];

int makeSeg(int left, int right, int idx){
    if(left >= right){
        return seg[idx] = arr[left];
    }
    int mid = (left + right) / 2;
    return seg[idx] = min(makeSeg(left, mid, idx*2),makeSeg(mid+1, right, idx*2+1));
}

int findSeg(int left, int right, int idx, int fl, int fr){
    if(right < fl or left > fr){
        return 2000000000;
    }
    if(fl <= left and right <= fr){
        return seg[idx];
    }
    int mid = (left + right) / 2;
    return min(findSeg(left, mid, idx*2, fl, fr), findSeg(mid+1, right, idx*2+1, fl, fr));
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> M;
    for(int i = 1; i <= N; i++){
        cin >> arr[i];
    }
    makeSeg(1, N, 1);
    for(int i = 1; i <= M; i++){
        int l,r;
        cin >> l >> r;
        cout << findSeg(1, N, 1, l, r) << "\n";
    }
    return 0;
}
