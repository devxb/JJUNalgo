#include <iostream>
#include <algorithm>
#include <string>

using namespace std;

int N;

int getMaxReceiveVotes(int votes[]){
    int ans = 0;
    for(int i = N-1; i > N/2; i--) ans += votes[i];
    for(int i = N/2; i >= 0; i--) ans += (votes[i]/2);
    return ans;
}

void inputVotes(int votes[]){
    for(int i = 0; i < N; i++) cin >> votes[i];
    sort(votes, votes+N);
}

int main() {
    ios::sync_with_stdio(false);
	cin.tie(NULL);
    cout.tie(NULL);
    cin >> N;
    int votes[N];
    inputVotes(votes);
    cout << getMaxReceiveVotes(votes) << "\n";
    return 0;
}
