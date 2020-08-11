#include<iostream>
#include<vector>
#include<algorithm>
#include<utility>
#include<deque>

using namespace std;

int N, M;
int large_num,check;
deque<int> deq;

int main() {
    cin >> N >> M;
    for (int i = 0; i < N; i++) {
        int num = 0;
        cin >> num;
        deq.push_back(num);
    }
    for (int i = 0; i < N - 2; i++) {
        for (int b = i + 1; b < N - 1; b++) {
            for (int c = b + 1; c < N; c++) {
                if (deq[i] + deq[b] + deq[c] <= M) {
                    if (deq[i] + deq[b] + deq[c] > large_num) {
                        large_num = deq[i] + deq[b] + deq[c];
                    }
                }
            }
        }
    }
    cout << large_num << endl;
    return 0;
}

