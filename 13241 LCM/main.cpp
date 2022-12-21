#include <iostream>
#include <string>
#include <algorithm>
#include <cmath>
#include <vector>

using namespace std;

long long num1, num2;

long long gcd(long long num1, long long num2){
    if(num2 == 0) return num1;
    return gcd(num2, num1%num2);
}

void input(){
    cin >> num1 >> num2;
}

int main(void){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    input();
    cout << (num1*num2)/gcd(num1, num2) << "\n";
}
