//
//  main.cpp
//  1806 부분합
//
//  Created by 이준영 on 2021/01/02.
//

#include <iostream>
#include <algorithm>
using namespace std;

int N, S, arr[100005];

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> S;
    for(int i = 1; i <= N; i++){
        cin >> arr[i];
    }
    int p1=1, p2=1, sum=arr[1], length=100005;
    while(p2 <= N){
        if(sum >= S){
            length = min(p2 - p1 + 1, length);
            sum -= arr[p1];
            p1++;
            continue;
        }
        if(sum < S){
            p2++;
            sum += arr[p2];
            continue;
        }
    }
    if(length == 100005){
        cout << 0 << "\n";
    }
    else{
        cout << length << "\n";
    }
    return 0;
}
