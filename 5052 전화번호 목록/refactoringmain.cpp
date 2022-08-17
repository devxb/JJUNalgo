#include <iostream>
#include <algorithm>

using namespace std;

int trig = 1;

struct PhoneNumber{
    
    int num;
    bool endOfPhoneNumbers;
    PhoneNumber *nextNums[10];

    void initNextNums(){
        for(int i = 0; i < 10; i++) nextNums[i] = NULL;
    }

    PhoneNumber(int num, bool endOfPhoneNumbers){
        this -> num = num;
        this -> endOfPhoneNumbers = endOfPhoneNumbers;
    }

    bool isPhoneHasNumber(int num){
        if(this -> nextNums[num] == NULL) return false;
        return true;
    }

    void addNextPhoneNumber(int num, bool endOfPhoneNumbers){
        if(isPhoneHasNumber(num)){
            this -> nextNums[num] -> endOfPhoneNumbers = 
            (this -> nextNums[num] -> endOfPhoneNumbers == true) ? true : endOfPhoneNumbers;
            return;
        }
        PhoneNumber *nextPhoneNumber = new PhoneNumber(num, endOfPhoneNumbers);
        this -> nextNums[num] = nextPhoneNumber;
    }

    PhoneNumber *findNextPhoneNumber(int num){
        if(!isPhoneHasNumber(num)) return NULL;
        return nextNums[num];
    }

    bool isConsistentPhoneNumber(){
        if(!endOfPhoneNumbers) return true;
        for(int i = 0; i < 10; i++) if(this -> isPhoneHasNumber(i)) return false;
        return true;
    }

};

void searchPhoneBook(PhoneNumber *cursor){
    if(trig == 2) return;
    if(!(cursor -> isConsistentPhoneNumber())) trig = 2;
    for(int i = 0; i < 10; i++)
        if(cursor -> isPhoneHasNumber(i)) searchPhoneBook(cursor -> findNextPhoneNumber(i));
    
}

PhoneNumber *makePhoneBook(){
    PhoneNumber *head = new PhoneNumber(-1, false);
    int phoneCount = 0;
    cin >> phoneCount;
    for(int i = 0; i < phoneCount; i++){
        string phoneNumber = "";
        cin >> phoneNumber;
        PhoneNumber *cursor = head;
        for(int j = 0; j < phoneNumber.length(); j++){
            int nextNum = (int)phoneNumber[j] - (int)'0';
            cursor -> addNextPhoneNumber(nextNum, (j == phoneNumber.length()-1 ? true : false));
            cursor = cursor -> findNextPhoneNumber(nextNum);
        }
    }
    return head;
}

int main() {
    ios::sync_with_stdio(false);
	cin.tie(NULL);
    cout.tie(NULL);
    int testCases;
    cin >> testCases;
    for(int i = 0; i < testCases; i++){
        PhoneNumber *head = makePhoneBook();
        trig = 1;
        searchPhoneBook(head);
        cout << (trig == 1 ? "YES" : "NO") << "\n";
    }
    return 0;
}
