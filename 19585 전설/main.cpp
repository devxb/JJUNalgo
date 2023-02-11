#include <iostream>
#include <algorithm>
#include <string>
#include <unordered_set>
#include <cmath>
#include <vector>

using namespace std;

struct Trie{

    char c;
    bool isEnd;
    Trie* nextTrie[(int)'z' - (int)'a' + 1];

    Trie(char c, bool isEnd){
        for(int i = 0; i <= (int)'z' - (int)'a'; i++) nextTrie[i] = NULL;
        this -> c = c;
        this -> isEnd = isEnd;
    }

    ~Trie(){
        for(int i = 0; i <= (int)'z' - (int)'a'; i++) {
            if(nextTrie[i] != NULL){
                nextTrie[i] -> ~Trie();
                nextTrie[i] = NULL;
            }
        }
    }

    void addTrie(char c, bool isEnd){
        Trie* trie = nextTrie[(int)c - (int)'a'];
        if(trie != NULL && !(trie -> isEnd)) trie -> isEnd = isEnd;
        if(trie == NULL) trie = new Trie(c, isEnd);
        nextTrie[(int)c - (int)'a'] = trie;
    }

    Trie* getNextTrie(char c){
        return nextTrie[(int)c - (int)'a'];
    }

};

int C, N, Q;
unordered_set<string> teams;
Trie* entryPoint = new Trie('s', false);

bool isWinnableTeam(string str, vector<int> matchedList){
    for(int i = 0; i < matchedList.size(); i++){
        string substr = str.substr(matchedList[i] + 1);
        if(teams.find(substr) != teams.end()) return true;
    }
    return false;
}

vector<int> getMatchedList(string str){
    vector<int> matchedList;
    Trie* cursor = entryPoint;
    for(int i = 0; i < str.length(); i++){
        cursor = cursor -> getNextTrie(str[i]);
        if(cursor == NULL) return matchedList;
        if(cursor -> isEnd) matchedList.push_back(i);
    }
    return matchedList;
}

void findWinnableTeams(){
    cin >> Q;
    for(int i = 0; i < Q; i++){
        string str;
        cin >> str;
        vector<int> matchedList = getMatchedList(str);
        cout << (isWinnableTeam(str, matchedList) ? "Yes" : "No") << "\n";
    }
}

void inputTeam(){
    for(int i = 0; i < N; i++){
        string str;
        cin >> str;
        teams.insert(str);
    }
}

void makeTrie(string str){
    Trie* cursor = entryPoint;
    for(int i = 0; i < str.length(); i++){
        cursor -> addTrie(str[i], str.length()-1 == i);
        cursor = cursor -> getNextTrie(str[i]);
    }
}

void inputColor(){
    for(int i = 0; i < C; i++){
        string str;
        cin >> str;
        makeTrie(str);
    }
}

void input(){
    cin >> C >> N;
    inputColor();
    inputTeam();
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    input();
    findWinnableTeams();
    return 0;
}
