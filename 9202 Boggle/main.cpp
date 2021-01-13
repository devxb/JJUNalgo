//
//  main.cpp
//  9202 Boggle
//
//  Created by 이준영 on 2021/01/12.
//

#include <iostream>
#include <string>
#include <utility>
#include <algorithm>
#include <map>
#include <vector>

using namespace std;

int w, b, idx, score, cnt;
int dy[] = {-1, 0, 1, 0, 1, -1, 1, -1};
int dx[] = {0, -1, 0, 1, -1, 1, 1, -1};
int getScore[] = {0, 0, 1, 1, 2, 3, 5, 11};
int check[10][10];
char arr[10][10];
map<string,int> findBoggle;
string print;

struct dictionary{
    char c;
    bool end;
    dictionary *next[35];
    dictionary(char c, bool end){
        this -> c = c;
        this -> end = end;
        for(int i = 0; i < 30; i++){
            next[i] = NULL;
        }
    }
};

dictionary *exist(dictionary *go, string str){
    int i = 0;
    while(i < str.size()){
        if(go -> next[(int)str[i] - 65] != NULL){
            go = go -> next[(int)str[i] - 65];
            i++;
        }
        else{
            break;
        }
    }
    idx = i;
    return go;
}

void Boggle(dictionary *go, int y, int x, string str){
    if(str.size() > 8){
        return;
    }
    if(go -> end == true and findBoggle[str] == false){
        findBoggle[str] = true;
        score += getScore[str.size() - 1];
        cnt++;
        if(print.size() < str.size()){
            print = str;
        }
        else if(print.size() == str.size()){
            for(int i = 0; i < print.size(); i++){
                if((int)print[i] - 65 > (int)str[i] - 65){
                    print = str;
                }
                else if((int)print[i] - 65 < (int)str[i] - 65){
                    break;
                }
            }
        }
    }
    for(int i = 0; i < 8; i++){
        int ny = y + dy[i];
        int nx = x + dx[i];
        if(ny > 4 or nx > 4 or ny < 1 or nx < 1 or check[ny][nx] == 1 or go -> next[(int)arr[ny][nx] - 65] == NULL){
            continue;
        }
        check[ny][nx] = 1;
        Boggle(go -> next[(int)arr[ny][nx] - 65], ny, nx, str + arr[ny][nx]);
        check[ny][nx] = 0;
    }
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> w;
    dictionary *dic = new dictionary('x', false);
    vector<pair<int, string> > vec;
    for(int i = 1; i <= w; i++){
        string str;
        cin >> str;
        vec.push_back({str.size(), str});
    }
    sort(vec.begin(), vec.end());
    for(int i = 0; i < vec.size(); i++){
        dictionary *temp = dic;
        idx = 0;
        temp = exist(temp, vec[i].second);
        while(idx < vec[i].second.size()){
            bool end = false;
            if(idx == vec[i].second.size()-1){
                end = true;
            }
            dictionary *word = new dictionary(vec[i].second[idx], end);
            temp -> next[(int)vec[i].second[idx] - 65] = word;
            temp = word;
            idx++;
        }
    }
    cin >> b;
    for(int T = 1; T <= b; T++){
        score = 0;
        print = "";
        cnt = 0;
        for(int i = 0; i < vec.size(); i++){
            findBoggle[vec[i].second] = false;
        }
        for(int y = 1; y <= 4; y++){
            string str;
            cin >> str;
            for(int x = 1; x <= 4; x++){
                arr[y][x] = str[x-1];
                check[y][x] = 0;
            }
        }
        for(int y = 1; y <= 4; y++){
            for(int x = 1; x <= 4; x++){
                if(check[y][x] == 0 and dic -> next[(int)arr[y][x] - 65] != NULL){
                    check[y][x] = 1;
                    string str = "";
                    str = str + arr[y][x];
                    Boggle(dic -> next[(int)arr[y][x] - 65], y, x, str);
                    check[y][x] = 0;
                }
            }
        }
        cout << score << " " << print << " " << cnt << "\n";
    }
    return 0;
}
