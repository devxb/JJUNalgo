#include <iostream>
#include <algorithm>
#include <cmath>
#include <string>
#include <vector>

using namespace std;

int width, height, mold;
bool breeded[1001][1001];
int dy[] = {0, 1, 1};
int dx[] = {1, 0, 1};

string isFloorFillable(){
    for(int i = 1; i <= 1000; i += 2){
        for(int y = 1; y <= height-i; y++){
            for(int x = 1; x <= width-i; x++){
                if(!breeded[y][x]) continue;
                int trig = 0;
                for(int d = 0; d < 3; d++){
                    int nextY = y + dy[d] * i;
                    int nextX = x + dx[d] * i;
                    if(breeded[nextY][nextX]) trig++;
                }
                if(trig == 3) return "YES";
            }
        }
    }
    return "NO";
}

void inputMold(){
    for(int i = 0; i < mold; i++){
        int y, x;
        cin >> y >> x;
        breeded[y][x] = true;
    }
}

int main() {
    ios::sync_with_stdio(false);
	cin.tie(NULL);
    cout.tie(NULL);
    cin >> height >> width >> mold;
    inputMold();
    cout << isFloorFillable() << "\n";
    return 0;
}
