#include <iostream>
#include <algorithm>
#include <unordered_map>
#include <map>
#include <set>
#include <string>
#include <vector>
#include <queue>

using namespace std;

int N;
int itemCount;
unordered_map<string, int> itemToInitialNum;
unordered_map<int, string> initialNumToItem;
vector<vector<int> > itemGraph(400005);
vector<vector<int> > reverseItemGraph(400005);
vector<pair<int, string> > itemWithWeight;
int itemsWeight[400005];
int inDegree[400005];
bool falseToBuyAllItems = false;

void printItemWithWeight(){
    for(int i = 0; i < itemCount; i++) cout << itemWithWeight[i].second << "\n";
}

void mapItemWeightToItemName(){
    for(int i = 0; i < itemCount; i++) itemWithWeight.push_back({itemsWeight[i], initialNumToItem[i]});
    sort(itemWithWeight.begin(), itemWithWeight.end());
}

void makeItemWeight(){
    for(int i = 0; i < itemCount; i++) {
        itemsWeight[i] = -1;
    }
    queue<pair<int, int> > itemQue;
    for(int i = 0; i < itemCount; i++) if(inDegree[i] == 0) itemQue.push({i, 0});
    int loopCount = 0;
    while(!itemQue.empty()){
        int itemInitial = itemQue.front().first;
        int itemWeight = itemQue.front().second;
        itemQue.pop();
        loopCount++;
        itemsWeight[itemInitial] = itemWeight;
        for(int i = 0; i < itemGraph[itemInitial].size(); i++) {
            int nextItem = itemGraph[itemInitial][i];
            inDegree[nextItem]--;
            if(inDegree[nextItem] == 0) itemQue.push({nextItem, itemWeight+1});
        }
    }
    if(loopCount != itemCount) falseToBuyAllItems = true;
}

void input(){
    cin >> N;
    set<pair<int, int> > graphDupCheck;
    for(int i = 0; i < N; i++){
        string sonItem, parentItem;
        cin >> sonItem >> parentItem;
        if(itemToInitialNum.find(parentItem) == itemToInitialNum.end()){
            itemToInitialNum.insert({parentItem, itemCount});
            initialNumToItem.insert({itemCount, parentItem});
            itemCount++;
        }
        if(itemToInitialNum.find(sonItem) == itemToInitialNum.end()){
            itemToInitialNum.insert({sonItem, itemCount});
            initialNumToItem.insert({itemCount, sonItem});
            itemCount++;
        }
        int parentItemInitial = itemToInitialNum[parentItem];
        int sonItemInitial = itemToInitialNum[sonItem];
        if(graphDupCheck.find({parentItemInitial, sonItemInitial}) == graphDupCheck.end()){
            itemGraph[sonItemInitial].push_back(parentItemInitial);
            reverseItemGraph[parentItemInitial].push_back(sonItemInitial);
            inDegree[parentItemInitial]++;
            graphDupCheck.insert({parentItemInitial, sonItemInitial});
        }
    }
}

int main() {
    ios::sync_with_stdio(false);
	cin.tie(NULL);
    cout.tie(NULL);
    input();
    makeItemWeight();
    if(falseToBuyAllItems) cout << -1 << "\n";
    else{
        mapItemWeightToItemName();
        printItemWithWeight();
    }
    return 0;
}
