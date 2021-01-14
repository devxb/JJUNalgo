//
//  main.cpp
//  2941 크로아티아 알파벳
//
//  Created by 이준영 on 2021/01/14.
//

#include <iostream>
#include <string>
#include <map>
#include <utility>

using namespace std;

map<string, bool> m;

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    m["dz="] = m["c="] = m["c-"] = m["d-"] = m["lj"] = m["nj"] = m["s="] = m["z="] = true;
    string str;
    cin >> str;
    int cnt = 0;
    for(int i = 0; i < str.size(); i++){
        if(str[i] == '=' or str[i] == '-' or str[i] == 'j'){
            if(i > 1 and m[str.substr(i-2,3)] == true){
                cnt += 2;
            }
            else if(i > 0 and m[str.substr(i-1,2)] == true){
                cnt++;
            }
        }
    }
    cout << str.size() - cnt << "\n";
    return 0;
}
