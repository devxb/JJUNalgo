#include <iostream>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

int N, ans;
int pi[500001];
vector<vector<pair<int, char>>> vec(500001);
string P;

struct Trie{

    int duplicate = 1;
    bool isEnd = true;
    char c;
    Trie *nexts[(int)'z' - (int)'a'+1];

    Trie(char c){
        this -> c = c;
    }

    void addNextTrie(char c){
        isEnd = false;
        if(isExist(c)) {
            nexts[(int)c - (int)'a'] -> increaseDup();
            return;
        }
        nexts[(int)c - (int)'a'] = new Trie(c);
    }

    void increaseDup(){
        duplicate++;
    }

    Trie *findNextTrie(char c){
        if(!isExist(c)) return NULL;
        return nexts[(int)c - (int)'a'];
    }

    bool isExist(char c){
        return nexts[(int)c - (int)'a'] != NULL;
    }

};

void kmp(int p, Trie *cursor){
    while(cursor->c != P[p] && p > 0) p = pi[p-1];
    if(cursor->c == P[p]) p++;
    if(p == P.length()){
        ans += cursor->duplicate;
        p = pi[p-1];
    }
    if(cursor -> isEnd) return;
    for(int i = 0; i <= (int)'z' - (int)'a'; i++){
        if(cursor -> findNextTrie((char)(i + (int)'a')) == NULL) continue;
        kmp(p, cursor->findNextTrie((char)(i + (int)'a')));
    }
}

void initPi(){
    int p = 0;
    for(int i = 1; i < P.length(); i++){
        while(P[i] != P[p] && p > 0) p = pi[p-1];
        if(P[p] == P[i]) pi[i] = ++p;
    }
}

void buildTrie(int idx, Trie *cursor){
    for(int i = 0; i < vec[idx].size(); i++){
        cursor->addNextTrie(vec[idx][i].second);
        buildTrie(vec[idx][i].first, cursor->findNextTrie(vec[idx][i].second));
    }
}

void input(){
    for(int i = 1; i < N; i++){
        int u, v;
        char c;
        cin >> u >> v >> c;
        vec[u].push_back({v, c});
    }
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    input();
    Trie *cursor = new Trie('1');
    buildTrie(1, cursor);
    cin >> P;
    initPi();
    for(int i = 0; i <= (int)'z' - (int)'a'; i++){
        if(cursor -> findNextTrie((char)(i + (int)'a')) == NULL) continue;
        kmp(0, cursor->findNextTrie((char)(i + (int)'a')));
    }
    cout << ans << "\n";
}
