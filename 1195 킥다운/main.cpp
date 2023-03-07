#include <iostream>
#include <algorithm>
#include <string>
#include <unordered_set>
#include <cmath>
#include <vector>

using namespace std;

string topGear;
string bottomGear;

bool isMatched(int move, string &top, string &bottom){
    for(int i = 0; i <= min(top.length(), bottom.length() - move); i++){
        if(top[i] == '2' && bottom[i + move] == '2') return false;
    }
    return true;
}

int moveGear(string top, string bottom){
    int ans = top.length() + bottom.length();
    for(int move = 0; move < bottom.length(); move++){
        if(isMatched(move, top, bottom)){
            ans = min(ans, (int)max(top.length() + move, bottom.length()));
        }
    }
    return ans;
}

void input(){
    cin >> topGear >> bottomGear;
}

int main() {
    ios::sync_with_stdio(false);
	cin.tie(NULL);
    cout.tie(NULL);
    input();
    int ans = min(moveGear(topGear, bottomGear), moveGear(bottomGear, topGear));
    cout << ans << "\n";
    return 0;
}
