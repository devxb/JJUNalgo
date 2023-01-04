#include <iostream>

using namespace std;

int N;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    int cnt = 0;
    int digit = 1;
    while(N > 0){
        cnt += N;
        N -= ((digit * 10) - digit);
        digit *= 10;
    }
    cout << cnt << "\n";
}
