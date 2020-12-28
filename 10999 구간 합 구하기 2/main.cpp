//
//  main.cpp
//  10999 구간 합 구하기 2
//
//  Created by 이준영 on 2020/12/28.
//

#include <iostream>

using namespace std;

long long N, M, K;
long long arr[1000005], seg[1000005 * 6 + 5], lazy[1000005 * 6 + 5];
long long num;

long long set(long long left, long long right, long long idx){
    if(left > right){
        return 0;
    }
    if(left == right){
        return seg[idx] = arr[left];
    }
    long long mid = (left + right) / 2;
    return seg[idx] = set(left, mid, idx*2) + set(mid+1, right, idx*2+1);
}

void updateLazy(long long idx){
    lazy[idx*2] += lazy[idx];
    lazy[idx*2+1] += lazy[idx];
    lazy[idx] = 0;
    return;
}

long long update(long long left, long long right, long long idx, long long tl, long long tr, long long tn){
    if(left >= tl and right <= tr){
        lazy[idx] += tn;
//        cout << "left : " << left << " right : " << right << " seg[" << idx << "] : " << seg[idx] << " -> " << seg[idx] + ((right - left + 1) * lazy[idx]) << " lazy -> " << lazy[idx] << "\n";
        seg[idx] += ((right - left + 1) * lazy[idx]);
        if(right > left){
            updateLazy(idx);
        }
        else if(right == left){
            lazy[idx] = 0;
        }
        return seg[idx];
    }
    seg[idx] += ((right - left + 1) * lazy[idx]);
    updateLazy(idx);
    if(right < tl or left > tr or left >= right){
        return seg[idx];
    }
    long long mid = (left + right) / 2;
    return seg[idx] = update(left, mid, idx*2, tl, tr, tn) + update(mid+1, right, idx*2+1, tl, tr, tn);
}

long long search(long long left, long long right, long long idx, long long sl, long long sr){
    if(right < sl or left > sr){
        return 0;
    }
    if(left >= sl and right <= sr){
//        cout << "left : " << left << " right : " << right << " seg[" << idx << "] : " << seg[idx] << " -> " << seg[idx] + ((right - left + 1) * lazy[idx]) << " lazy -> " << lazy[idx] << "\n";
        seg[idx] += ((right - left + 1) * lazy[idx]);
        if(right > left){
            updateLazy(idx);
        }
        else if(right == left){
            lazy[idx] = 0;
        }
        return seg[idx];
    }
    seg[idx] += ((right - left + 1) * lazy[idx]);
    updateLazy(idx);
    if(right < sl or left > sr or left >= right){
        return seg[idx];
    }
    long long mid = (left + right) / 2;
    return num = search(left, mid, idx*2, sl, sr) + search(mid+1, right, idx*2+1, sl, sr);
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> M >> K;
    for(long long i = 1; i <= N; i++){
        cin >> arr[i];
    }
    set(1,N,1);
    for(long long i = 1; i <= M+K; i++){
        long long a, b, c, d;
        cin >> a;
        if(a == 1){
            cin >> b >> c >> d;
            update(1, N, 1, b, c, d);
        }
        else{
            cin >> b >> c;
            num = 0;
            cout << search(1, N, 1, b, c) << "\n";
        }
    }
    return 0;
}
