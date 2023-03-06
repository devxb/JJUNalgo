#include <iostream>
#include <algorithm>
#include <string>
#include <unordered_set>
#include <cmath>
#include <vector>

using namespace std;

int N, K;
int arr[9][9];
int pep[3][20];
int win[3];
bool visited[9];
int playerIdx[3];

bool isLeftWin(int leftHand, int rightHand){
    return arr[leftHand][rightHand] == 2;
}

int whoIsWin(int winner, int nextPlayer, int handIdx){
    int left = -1;
    int right = -1;
    if(winner == 0){
        left = handIdx;
        right = pep[nextPlayer][playerIdx[nextPlayer]];
    }
    else if(nextPlayer == 0){
        left = pep[winner][playerIdx[winner]];
        right = handIdx;
    }
    else{
        left = pep[winner][playerIdx[winner]];
        right = pep[nextPlayer][playerIdx[nextPlayer]];
    }
    return isLeftWin(left, right) ? winner : nextPlayer;
}

int getNextPlayer(int winner, int loser){
    return 3 - (winner + loser);
}

int amIWin(){
    if(win[0] < K && win[1] < K && win[2] < K) return -1;
    if(win[1] == K || win[2] == K) return 1;
    return 0;
}

bool isNeedToSwap(int leftPlayer, int rightPlayer){
    if(leftPlayer == 2) return true;
    if(leftPlayer == 1 && rightPlayer == 0) return true;
    return false;
}

bool doGame(int idx, int winner, int loser){
    if(idx >= 20) return false;
    int isWin = amIWin();
    if(isWin != -1){
        return isWin == 0;
    }
    bool ans = false;
    int left = winner;
    int right = getNextPlayer(winner, loser);
    if(isNeedToSwap(left, right)){
        int temp = left;
        left = right;
        right = temp;
    }
    if(left != 0 && right != 0){
        int nextWinner = whoIsWin(left, right, -1);
        int nextLoser = (nextWinner == left) ? right : left;
        playerIdx[left]++;
        playerIdx[right]++;
        win[nextWinner]++;
        ans = doGame(idx+1, nextWinner, nextLoser);
        win[nextWinner]--;
        playerIdx[left]--;
        playerIdx[right]--;
    }
    else{
        for(int i = 0; i < N; i++){
            if(visited[i] || ans) continue;
            int nextWinner = whoIsWin(left, right, i);
            int nextLoser = (nextWinner == left) ? right : left;
            playerIdx[left]++;
            playerIdx[right]++;
            visited[i] = true;
            win[nextWinner]++;
            ans = doGame(idx+1, nextWinner, nextLoser);
            win[nextWinner]--;
            visited[i] = false;
            playerIdx[left]--;
            playerIdx[right]--;
        }
    }
    return ans;
}

void input(){
    cin >> N >> K;
    for(int i = 0; i < N; i++){
        for(int j = 0; j < N; j++){
            cin >> arr[i][j];
        }
    }
    for(int i = 0; i < 20; i++){
        cin >> pep[1][i];
        pep[1][i] -= 1;
    }
    for(int i = 0; i < 20; i++){
        cin >> pep[2][i];
        pep[2][i] -= 1;
    }
    playerIdx[0] = playerIdx[1] = playerIdx[2] = 0;
}

int main() {
    ios::sync_with_stdio(false);
	cin.tie(NULL);
    cout.tie(NULL);
    input();
    cout << doGame(0, 0, 2) << "\n";
    return 0;
}
