//
// xb205
// 2021.1.19
//

#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;
int N, M, idx, check[30];

struct dictionary{
    int end;
    dictionary *next[30];
    dictionary(int end){
        this -> end = end;
        for(int i = 0; i <= 26; i++){
            next[i] = NULL;
        }
    }
};

string getstr(string str){
    int check[30] = {0, };
    string answer = "";
    for(int i = 0 ; i < str.size(); i++){
        if(check[(int)(str[i] - 'a')] == 0){
            answer += str[i];
            check[(int)(str[i]-'a')] = 1;
        }
    }
    return answer;
}

dictionary *exist(dictionary *go, string str){
    int i = 0;
    while(i < str.size()){
        if(go -> next[(int)(str[i] - 'a')] == NULL){
            break;
        }
        go = go -> next[(int)(str[i] - 'a')];
        i++;
    }
    if(i == str.size()){
        go -> end += 1;
    }
    idx = i;
    return go;
}

int find(dictionary *go){
    int num = 0;
    num += go->end;
    for(int i = (int)('a'-'a'); i <= (int)('z'-'a'); i++){
        if(go -> next[i] != NULL and check[i] == 0){
            num += find(go -> next[i]);
        } 
    }
    return num;
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> M;
    vector<string> vec;
    for(int i = 1; i <= N; i++){
        string str;
        cin >> str;
        str = getstr(str);
        vec.push_back(str);
    }
    sort(vec.begin(), vec.end());
    dictionary *start = new dictionary(0);
    for(int i = 0 ; i < vec.size(); i++){
        idx = 0;
        dictionary *temp = exist(start, vec[i]);
        while(idx < vec[i].size()){
            int end = 0;
            if(idx == vec[i].size()-1){
                end = 1;
            }
            dictionary *word = new dictionary(end);
            temp -> next[(int)(vec[i][idx] - 'a')] = word;
            temp = word;
            idx++;
        }
    }
    for(int i = 1; i <= M; i++){
        int o;
        char x;
        cin >> o >> x;
        if(o == 1){
            check[(int)(x-'a')] = 1;
        }
        if(o == 2){
            check[(int)(x-'a')] = 0;
        }
        cout << find(start) << "\n";
    }
    return 0;
}
