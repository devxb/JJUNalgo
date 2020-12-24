//
//  main.cpp
//  15970 화살표 그리기
//
//  Created by 이준영 on 2020/12/24.
//

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N, length;
vector<vector<int> > colors;

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    colors.resize(N+5);
    for(int i = 1; i <= N; i++){
        int pos, color;
        cin >> pos >> color;
        colors[color].push_back(pos);
    }
    for(int i = 1; i <= N; i++){
        sort(colors[i].begin(), colors[i].end());
    }
    for(int i = 1; i <= N; i++){
        for(int j = 0; j < colors[i].size(); j++){
            if(j == 0){
                length += colors[i][j+1] - colors[i][j];
                continue;
            }
            if(j == colors[i].size()-1){
                length += colors[i][j] - colors[i][j-1];
                continue;
            }
            length += min(colors[i][j] - colors[i][j-1], colors[i][j+1] - colors[i][j]);
        }
    }
    if(length < 0){
        length = 0;
    }
    cout << length << "\n";
    return 0;
}
