//
//  main.cpp
//  2872 우리집엔 도서관이 있어
//
//  Created by 이준영 on 2020/11/02.
//

#include <iostream>

using namespace std;

int N;
int cnt = 0;
int book[300005];
int check[300005];

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    for(int i = 1; i <= N; i++){
        int num;
        cin >> num;
        book[num] = i;
    }
    for(int i = N-1; i >= 1; i--){
        if(book[i] > book[i+1] or check[i+1] == 1){
            check[i] = 1;
            cnt++;
        }
    }
    cout << cnt << "\n";
    return 0;
}
