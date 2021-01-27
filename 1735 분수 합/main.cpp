//
// xb205
// 2021.01.27
//

#include <iostream>
#include <algorithm>

using namespace std;
int A,B;

int getgcd(int a, int b){
    int r = b%a;
    if(r == 0){
        return a;
    }
    return getgcd(r,a);
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    int a1,b1,a2,b2;
    cin >> a1 >> b1 >> a2 >> b2;
    B = b1*b2;
    A = a1*b2+a2*b1;
    int gcd = getgcd(min(A,B), max(A,B));
    cout << A/gcd << " " << B/gcd << "\n";
    return 0;
}
