//
// 2021.01.29
// xb205
//

#include <iostream>
#include <algorithm>
#include <utility>
#include <vector>
using namespace std;
int T;
int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> T;
    for(int i = 1; i <= T; i++){
        int N;
        cin >> N;
        vector<pair<int,int> > vec;
        for(int j = 1; j <= N; j++){
            int a,b;
            cin >> a >> b;
            vec.push_back({a,b});
        }
        sort(vec.begin(), vec.end());
        int picked = 1, sta = vec[0].second;
        for(int j = 1; j < N; j++){
            if(vec[j].second < sta){
                picked++;
                sta = vec[j].second;
            }
        }
        cout << picked << "\n";
    }
    return 0;
}
