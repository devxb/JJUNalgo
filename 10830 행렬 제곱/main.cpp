//
// xb205
// 2021.01.26
//

#include <iostream>
#include <vector>
using namespace std;
typedef long long ll;
ll N, B, rem = 1000;
vector<vector<ll> > arr;
vector<vector<ll> > sumvec(vector<vector<ll> > vec1, vector<vector<ll> > vec2){
    vector<vector<ll> > answer(N+2, vector<ll>(N+2));
    for(int i = 1; i <= N; i++){
        for(int j = 1; j <= N; j++){
            for(int l = 1; l <= N; l++){
                answer[i][j] += vec1[i][l] * vec2[l][j];
            }
            answer[i][j] %= 1000;
        }
    }
    return answer;
}

vector<vector<ll> > getvec(ll squ){
    if(squ == 1){
        return arr;
    }
    vector<vector<ll> > temp = getvec(squ/2);
    if(squ % 2 == 0){
        return sumvec(temp, temp);
    }
    else{
        return sumvec(sumvec(temp, temp), getvec(1));
    }
    return arr;
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> B;
    arr.resize(N+2, vector<ll>(N+2));
    for(int i = 1; i <= N; i++){
        for(int j = 1; j <= N; j++){
            cin >> arr[i][j];
        }
    }
    vector<vector<ll> > print;
    print = getvec(B);
    for(int i = 1; i <= N; i++){
        for(int j = 1; j <= N; j++){
            cout << print[i][j] % rem << " ";
        }
        cout << "\n";
    }
    return 0;
}
