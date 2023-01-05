#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

vector<int> down;
vector<int> up;
int N, H, low = 2000000000;
int check[200005];
int u(int H){
    int left = 0, right = up.size()-1;
    int mid = (left + right) / 2;
    while(left < right){
        if(up[mid] >= H){
            right = mid - 1;
        }
        else if(up[mid] < H){
            left = mid + 1;
        }
        mid = (left + right) / 2;
    }
    if(up[mid] >= H){
        for(int i = mid; i >= 0; i--){
            if(up[i] < H){
                mid = i;
                break;
            }
        }
    }
    return ((up.size()-1) - mid);
}

int d(int H){
    int left = 0, right = down.size()-1;
    int mid = (left + right) / 2;
    while(left < right){
        if(down[mid] >= H){
            right = mid - 1;
        }
        else if(down[mid] < H){
            left = mid + 1;
        }
        mid = (left + right) / 2;
    }
    if(down[mid] >= H){
        for(int i = mid; i >= 0; i--){
            if(down[i] < H){
                mid = i;
                break;
            }
        }
    }
    return ((down.size()-1) - mid);
}

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N >> H;
    for(int i = 1; i <= N; i++){
        int num;
        cin >> num;
        if(i % 2 == 0){
            down.push_back(num);
            up.push_back(0);
        }
        else{
            up.push_back(num);
            down.push_back(0);
        }
    }
    sort(up.begin(),up.end());
    sort(down.begin(),down.end());
    for(int i = 1; i <= H; i++){
        int num = (u(i) + d(H - i + 1));
        low = min(low, num);
        check[num]++;
    }
    cout << low << " " << check[low] << "\n";
    return 0;
}
