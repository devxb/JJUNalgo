//
// xb205
// 2021.01.23
//

#include <iostream>
#include <algorithm>
#include <cmath>
#include <vector>
#include <algorithm>

typedef long double ld;
typedef long long int ll;
using namespace std;
ll minimum, maximum;

ll getsqrt(){
    ll cnt = 0;
    bool check[1000005] = {false, };
    for(ll i = 2; i <= sqrt(maximum); i++){
        ll num = (i*i) * (minimum / (i*i));
        if(num < minimum){
            num += (i*i);
        }
        while(num <= maximum){
            if(check[num-minimum] == false){
                cnt++;
            }
            check[num-minimum] = true;
            num += (i*i);
        }
    }
    return (maximum - minimum) + 1 - cnt;
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> minimum >> maximum;
    cout << getsqrt() << "\n";
    return 0;
}
