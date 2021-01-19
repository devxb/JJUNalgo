//
// xb205
// 2021.1.19
//

#include <iostream>
#include <string>
#include <algorithm>

using namespace std;
string str;
int num;

int getkmp(int bottom){
    int length=0, begin=1, cnt=0, fail[5005]={0, };
    while(begin+cnt+bottom < str.size()){
        if(str[bottom+begin+cnt] != str[bottom+cnt]){
            int temp = bottom+begin+cnt;
            while(cnt > 0 and str[bottom+begin+cnt] != str[bottom+cnt]){
                cnt = fail[bottom+cnt-1];
            }
            if(cnt == 0){
                begin++;
            }
            else{
                begin = temp-(cnt+bottom);
            }
        }
        else if(str[bottom+begin+cnt] == str[bottom+cnt]){
            fail[bottom+begin+cnt] = cnt+1;
            length = max(length,fail[bottom+begin+cnt]);
            cnt++;
        }
    }
    return length;
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> str;
    for(int i = 0; i < str.size(); i++){
        num = max(num,getkmp(i));
    }
    cout << num << "\n";
    return 0;
}
