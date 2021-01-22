//
// xb205
// 2021.1.22
//

#include <iostream>
#include <string>

using namespace std;
int T, fail[1000005];
string str;

void getfail(string str){
    int begin = 1, cnt = 0;
    while(begin + cnt < str.size()){
        if(str[cnt] != str[begin+cnt]){
            int temp = begin+cnt;
            while(cnt > 0 and str[begin+cnt] != str[cnt]){
                cnt = fail[cnt-1];
            }
            if(cnt == 0){
                begin++;
            }
            else{
                begin = temp - cnt;
            }
        }
        if(str[cnt] == str[begin+cnt]){
            fail[begin+cnt] = cnt+1;
            cnt++;
        }
    }
}

bool getcamel(){
    bool answer = false;
    int suffix = fail[str.size()-1];
    if(suffix == 0){
        return answer;
    }
    for(int i = str.size()-2; i >= 0; i--){
        if(fail[i] == suffix){
            answer = true;
            break;
        }
    }
    if(answer == true){
        for(int i = 0; i < fail[str.size()-1]; i++){
            cout << str[i];
        }
        return answer;
    }
    for(int i = str.size()-fail[str.size()-1]; i < fail[str.size()-1]; i++){
        answer = true;
        cout << str[i];
    }
    if(answer == false){
        string s;
        int temp = fail[str.size()-1];
        for(int i = 0; i < fail[str.size()-1]; i++){
            s += str[i];
        }
        getfail(s);
        if(fail[s.size()-1] != 0){
            for(int i = 0; i < fail[s.size()-1]; i++){
                answer = true;
                cout << s[i];
            }
        }
    }
    return answer;
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> str;
    getfail(str);
    if(getcamel() == false){
        cout << "-1\n";
    }
    return 0;
}
