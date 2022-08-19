#include <iostream>
#include <algorithm>
#include <cmath>

using namespace std;

string words[100000];

struct Dictionary{

    char c;
    Dictionary *nextChar[26];
    int nextDictinoaryCount = 0;
    bool endOfWord = false;

    Dictionary(char c, bool endOfWord){
        for(int i = 0; i < 26; i++) nextChar[i] = NULL;
        this -> c = c;
        this -> endOfWord = endOfWord;
    }

    ~Dictionary(){
        for(int i = 0; i < 26; i++) 
            if(nextChar[i]) 
                delete nextChar[i];
    }

    void addNextDictionary(Dictionary *dic){
        int charToInt = (int)(dic -> c) - (int)'a';
        if(this -> nextChar[charToInt] == NULL){
            this -> nextChar[charToInt] = dic;
            this -> nextDictinoaryCount++;
        }
        if(dic -> endOfWord) this -> nextChar[charToInt] -> endOfWord = true;
    }

    Dictionary *getNextDictionary(char word){
        return this -> nextChar[(int)word - (int)'a'];
    }

};

double typeEntireWord(int idx, Dictionary *cursor){
    double typedCount = 0;
    for(int i = 0; i < words[idx].length(); i++){
        if(!((cursor -> nextDictinoaryCount == 1) && !(cursor -> endOfWord)) || i == 0) typedCount++;
        cursor = cursor -> getNextDictionary(words[idx][i]);
    }
    return typedCount;
}

double getAverageTypeCount(int dicCount, Dictionary *startCursor){
    double entireTypedCount = 0;
    for(int i = 0; i < dicCount; i++) {
        Dictionary *cursor = startCursor;
        entireTypedCount += typeEntireWord(i, cursor);
    }
    entireTypedCount /= dicCount;
    return round(entireTypedCount * 100) / 100;
}

void wordToDictionary(int idx, Dictionary *cursor){
    for(int i = 0; i < words[idx].length(); i++){
        if(cursor -> getNextDictionary(words[idx][i]) == NULL || i == words[idx].length()-1)
            cursor -> addNextDictionary(new Dictionary(words[idx][i], (i == words[idx].length()-1 ? true : false)));
        cursor = cursor -> getNextDictionary(words[idx][i]);
    }
}

void makeDictionary(int dicCount, Dictionary *startCursor){
    for(int i = 0; i < dicCount; i++) wordToDictionary(i, startCursor);
}

int main() {
    ios::sync_with_stdio(false);
	cin.tie(NULL);
    cout.tie(NULL);
    cout << fixed;
    cout.precision(2);
    int dicCount = 0;
    while(cin >> dicCount){
        Dictionary *startCursor = new Dictionary('S', false);
        for(int i = 0; i < dicCount; i++) cin >> words[i];
        makeDictionary(dicCount, startCursor);
        cout << getAverageTypeCount(dicCount, startCursor) << "\n";
        delete startCursor;
    }
    return 0;
}
