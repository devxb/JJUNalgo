//
//  main.cpp
//  18679 Banana
//
//  Created by 이준영 on 2021/01/10.
//

#include <iostream>
#include <map>
#include <string>

using namespace std;

int N, T;
map<string, string> mini;

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    for(int i = 1; i <= N; i++){
        string pep, equal, minimi;
        cin >> pep >> equal >> minimi;
        mini[pep] = minimi;
    }
    cin >> T;
    for(int i = 1; i <= T; i++){
        int K;
        cin >> K;
        string str;
        for(int j = 1; j <= K; j++){
            cin >> str;
            cout << mini[str] << " ";
        }
        cout << "\n";
    }
    return 0;
}
