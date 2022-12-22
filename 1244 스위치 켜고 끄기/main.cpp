#include <iostream>
#include <string>
#include <algorithm>
#include <cmath>

using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    int N;
    cin >> N;
    int arr[N+1];
    arr[0] = 0;
    for(int i = 1; i <= N; i++) cin >> arr[i];

    int stu;
    cin >> stu;
    for(int i = 0; i < stu; i++){
        int se, idx;
        cin >> se >> idx;
        if(se == 1){
            for(int j = idx; j <= N; j += idx) arr[j] = arr[j] == 1 ? 0 : 1;
            continue;
        }
        int l = idx-1, r = idx+1;
        arr[idx] = arr[idx] == 1 ? 0 : 1;
        while(l >= 1 && r <= N){
            if(arr[l] != arr[r]) break;
            arr[l] = arr[l] == 1 ? 0 : 1;
            arr[r] = arr[r] == 1 ? 0 : 1;
            l--;
            r++;
        }
    }

    for(int i = 1; i <= N; i++) {
        cout << arr[i];
        if(i%20 == 0) cout << "\n";
        else cout << " ";
    }
}
