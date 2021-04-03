// 
// xb205
// 2021.04.03
// 2121
//

#include <iostream>
#include <vector>
#include <algorithm>
#include <utility>
#include <map>

using namespace std;

int N, A, B;
map<int, vector<int> > posY;
vector<pair<int,int> > cand;

bool getpos(int tY, int tX){
    int l = 0, r = posY[tY].size(), m;
	if(r <= 0) return false;
    for(int i = 0; i <= 9; i++){
        m = (l + r) / 2;
        if(posY[tY][m] == tX) return true;
        if(posY[tY][m] > tX) r = m-1;
        else if (posY[tY][m] < tX) l = m+1;
    }
    return posY[tY][m] == tX;
}

int getc(int wi, int he){
    int ret = 0, candSize = cand.size();
    for(int i = 0; i < candSize; i++){
        int y = cand[i].first;
        int x = cand[i].second;
        if(getpos(y+he, x) && getpos(y, x+wi) && getpos(y+he,x+wi)) ret++;
    }
    return ret;
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> A >> B;
    for(int i = 1; i <= N; i++){
        int X,Y;
        cin >> X >> Y;
        posY[Y].push_back(X);
        cand.push_back({Y,X});
    }
    sort(cand.begin(), cand.end());
    for(int i = 0; i < cand.size(); i++){
        pair<int,int> p = cand[i];
        sort(posY[p.first].begin(), posY[p.first].end());
    }
    cout << getc(A,B) << "\n";
    return 0;
}
