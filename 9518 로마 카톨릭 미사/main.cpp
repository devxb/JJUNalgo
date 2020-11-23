//
//  main.cpp
//  9518 로마 카톨릭 미사
//
//  Created by 이준영 on 2020/11/23.
//

#include <iostream>
#include <string>
#include <algorithm>
#include <utility>
#include <map>

using namespace std;

int R, S, print;
string arr[100][100];
int cnt[100][100];
int check[2505][2505];

int dy[] = {-1,-1,-1,0,0,1,1,1};
int dx[] = {0,1,-1,-1,1,0,-1,1};

map<pair<int,int>,int> m;

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> R >> S;
    int b = 1;
    string str;
    for(int i = 1; i <= R; i++){
        cin >> str;
        for(int j = 1; j <= S; j++){
            arr[i][j] = str[j-1];
            if(arr[i][j] == "o"){
                m.insert({{i,j},b});
                b++;
                for(int l = 0; l < 8; l++){
                    cnt[i+dy[l]][j+dx[l]]++;
                }
            }
        }
    }
    pair<int,pair<int,int> > num = {0,{0,0}};
    for(int i = 1; i <= R; i++){
        for(int j = 1; j <= S; j++){
            if(num.first < cnt[i][j] and arr[i][j] != "o"){
                num.first = cnt[i][j];
                num.second = {i,j};
            }
        }
    }
    if(num.second.first != 0 and num.second.second != 0){
        m.insert({{num.second.first,num.second.second},b});
        arr[num.second.first][num.second.second] = "o";
    }
    for(int i = 1; i <= R; i++){
        for(int j = 1; j <= S; j++){
            if(arr[i][j] == "o"){
                for(int l = 0; l < 8; l++){
                    if(arr[i+dy[l]][j+dx[l]] == "o" and check[m[{i,j}]][m[{i+dy[l],j+dx[l]}]] != 1 and check[m[{i+dy[l],j+dx[l]}]][m[{i,j}]] == 0){
                        check[m[{i,j}]][m[{i+dy[l],j+dx[l]}]] = 1;
                        check[m[{i+dy[l],j+dx[l]}]][m[{i,j}]] = 1;
                        print++;
                    }
                }
            }
        }
    }
    cout << print << "\n";
    return 0;
}
