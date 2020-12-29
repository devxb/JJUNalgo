//
//  main.cpp
//  1395 스위치
//
//  Created by 이준영 on 2020/12/29.
//

#include <iostream>

using namespace std;

int N, M;
int seg[100000*4+5], lazy[100000*4+5];

void updateLazy(int idx){
    if(idx*2 < 100000*4){
        lazy[idx*2] += lazy[idx];
        lazy[idx*2+1] += lazy[idx];
    }
    lazy[idx] = 0;
}

int update(int left, int right, int idx, int ul, int ur){
    if(ul <= left and right <= ur){
        lazy[idx] += 1;
        if(lazy[idx] % 2 == 1){
            seg[idx] = (right - left + 1) - seg[idx];
        }
        updateLazy(idx);
        return seg[idx];
    }
    int mid = (left + right) / 2;
    if(lazy[idx] % 2 == 1){
        seg[idx] = (right - left + 1) - seg[idx];
        updateLazy(idx);
    }
    if(left >= right or right < ul or left > ur){
        return seg[idx];
    }
    return seg[idx] = update(left, mid, idx*2, ul, ur) + update(mid+1, right, idx*2+1, ul, ur);
}

int get(int left, int right, int idx, int gl, int gr){
    if(gl <= left and right <= gr){
        if(lazy[idx] % 2 == 1){
            seg[idx] = (right - left + 1) - seg[idx];
        }
        updateLazy(idx);
        return seg[idx];
    }
    int mid = (left + right) / 2;
    if(lazy[idx] % 2 == 1){
        seg[idx] = (right - left + 1) - seg[idx];
        updateLazy(idx);
    }
    if(left >= right or right < gl or left > gr){
        return 0;
    }
    return get(left, mid, idx*2, gl, gr) + get(mid + 1, right, idx*2+1, gl, gr);
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> M;
    for(int i = 1; i <= M; i++){
        int O,S,T;
        cin >> O >> S >> T;
        if(O == 0){
            update(1, N, 1, S, T);
        }
        else{
            cout << get(1, N, 1, S, T) << "\n";
        }
    }
    return 0;
}
