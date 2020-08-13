//
//  main.cpp
//  5397 키로거
//
//  Created by 이준영 on 13/08/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>
#include <string>

using namespace std;

struct password{
    char c;
    struct password *left;
    struct password *right;
};

int L;

int main(int argc, const char * argv[]) {
    ios::sync_with_stdio();
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> L;
    for(int i = 1; i <= L; i++){
        string str;
        cin >> str;
        password* mouse = new password;
        mouse -> left = NULL;
        mouse -> right = NULL;
        mouse -> c = ':';
        for(int i = 0; i < str.size(); i++){
            if(str[i] == '<'){
                if(mouse -> left == NULL){
                    continue;
                }
                mouse -> right = mouse -> left;
                mouse -> left = mouse -> left -> left;
            }
            else if(str[i] == '>'){
                if(mouse -> right == NULL){
                    continue;
                }
                mouse -> left = mouse -> right;
                mouse -> right = mouse -> right -> right;
            }
            else if(str[i] == '-'){
                if(mouse -> left == NULL){
                    continue;
                }
                if(mouse -> left -> left == NULL){
                    mouse -> left = NULL;
                    if(mouse -> right != NULL){
                        mouse -> right -> left = NULL;
                    }
                    continue;
                }
                if(mouse -> right == NULL){
                    mouse -> left -> left -> right = mouse -> right;
                    mouse -> left = mouse -> left -> left;
                    continue;
                }
                mouse -> left -> left -> right = mouse -> right;
                mouse -> right -> left = mouse -> left -> left;
                mouse -> left = mouse -> left -> left;
            }
            else{
                password* new_p = new password;
                new_p -> c = str[i];
                new_p -> right = mouse -> right;
                new_p -> left = mouse -> left;
                if(mouse -> left != NULL){
                    mouse -> left -> right = new_p;
                }
                if(mouse -> right != NULL){
                    mouse -> right -> left = new_p;
                }
                mouse -> left = new_p;
            }
        }
        while(mouse -> left != NULL){
            mouse = mouse -> left;
        }
        while(mouse -> right != NULL){
            if(mouse -> c != ':'){
                cout << mouse -> c;
            }
            mouse = mouse -> right;
        }
        if(mouse -> c != ':'){
            cout << mouse -> c;
        }
        cout << "\n";
    }
    
    return 0;
}
