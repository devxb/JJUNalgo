//
// xb205
// 2021.01.27
//

#include <iostream>
#include <map>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;
int N, idx;

struct anthole{
    string food;
    vector<string> key;
    map<string, anthole *> next;
    anthole(string food){
        this -> food = food;
    }
};

void gethole(anthole *ant, int layer){
    if(layer > 0){
        for(int i = 1; i < layer; i++){
            cout << "--";
        }
        cout << ant -> food << "\n";
    }
    sort(ant->key.begin(), ant->key.end());
    for(int i = 0; i < ant->key.size(); i++){
        gethole(ant->next[ant->key[i]], layer+1);
    }
    return;
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    vector<string> vec;
    anthole *ent = new anthole("none");
    for(int i = 1; i <= N; i++){
        int cnt = 0;
        cin >> cnt;
        anthole *ant = ent;
        for(int j = 1; j <= cnt; j++){
            string str;
            cin >> str;
            if(ant->next.find(str) != ant->next.end()){
                ant = ant -> next[str];
                continue;
            }
            ant -> key.push_back(str);
            anthole *food = new anthole(str);
            ant -> next[str] = food;
            ant = food;
        }
    }
    gethole(ent, 0);
    return 0;
}
