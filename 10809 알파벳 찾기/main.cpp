#include <iostream>
#include <string>
#include <algorithm>
#include <cmath>

using namespace std;

int ascii[(int)'z' + 1];

void initAscii(){
    for(char i = 'a'; i <= 'z'; i++) ascii[i] = -1;
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    initAscii();
    string str;
    cin >> str;
    for(int i = 0; i < str.length(); i++){
        if(ascii[str[i]] != -1) continue;
        ascii[str[i]] = i;
    }
    for(char i = 'a'; i <= 'z'; i++) cout << ascii[i] << " ";
}
