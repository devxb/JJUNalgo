//
//  main.cpp
//  1083 소트
//
//  Created by 이준영 on 2020/12/03.
//

#include <iostream>
#include <vector>
#include <algorithm>
#include <utility>
#include <deque>

using namespace std;

int N,S;
deque<int> deq;

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    for(int i = 1; i <= N; i++){
        int num;
        cin >> num;
        deq.push_back(num);
    }
    cin >> S;
    for(int i = 0; i < N; i++){
        int tempS = 0, nowS = 0, j = i;
        int nowmax = deq[i];
        deque<int> temp;
        while(nowS <= S and j < deq.size()){
            if(deq[j] > nowmax){
                nowmax = deq[j];
                tempS = nowS;
            }
            nowS++;
            j++;
        }
        S = S - tempS;
        j = 0;
        while(!deq.empty()){
            if(j == i){
                temp.push_back(nowmax);
                j++;
                continue;
            }
            if(deq.front() == nowmax){
                deq.pop_front();
                continue;
            }
            temp.push_back(deq.front());
            deq.pop_front();
            j++;
        }
        deq = temp;
    }
    for(int i = 0; i < deq.size(); i++){
        cout << deq[i] << " ";
    }
    return 0;
}
