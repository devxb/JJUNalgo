//
// xb205
// 2021.01.29
//

#include <iostream>
#include <string>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    while(true){
        string str;
        getline(cin, str);
        if(str.size() == 1 and str[0] == '#'){
            break;
        }
        int cnt = 0;
        for(int i = 0; i < str.size(); i++){
            if(
                str[i] == 'a' or str[i] == 'e' or str[i] == 'i' or str[i] == 'o' or str[i] == 'u'
                or str[i] == 'A' or str[i] == 'E' or str[i] == 'I' or str[i] == 'O' or str[i] == 'U'
            ){
                cnt++;
            }
        }
        cout << cnt << "\n";
    }
    return 0;
}
