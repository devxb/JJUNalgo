//
//  main.cpp
//  8901 화학 제품
//
//  Created by 이준영 on 16/09/2020.
//  Copyright © 2020 이준영. All rights reserved.
//

#include <iostream>

using namespace std;

int T;

int main(int argc, const char * argv[]) {
    scanf("%d",&T);
    for(int i = 1; i <= T; i++){
        int arr[5], price[5];
        int max_num = 0;
        for(int j = 1; j <= 3; j++){
            scanf("%d",&arr[j]);
        }
        for(int j = 1; j <= 3; j++){
            scanf("%d",&price[j]);
        }
        int AB = min(arr[1], arr[2]);
        int BC = min(arr[2], arr[3]);
        int CA = min(arr[3], arr[1]);
        for(int j = 0; j <= AB; j++){
            int A1 = arr[1], B1 = arr[2], C1 = arr[3], num = 0;
            if(A1 - j >= 0 and B1 - j >= 0){
                A1 = A1 - j;
                B1 = B1 - j;
                num = num + (price[1] * j);
            }
            if(price[2] > price[3]){
                int temp = min(B1, C1);
                num = num + price[2] * temp;
                B1 = B1 - temp;
                C1 = C1 - temp;
                temp = min(A1, C1);
                num = num + price[3] * temp;
            }
            else{
                int temp = min(A1, C1);
                num = num + price[3] * temp;
                A1 = A1 - temp;
                C1 = C1 - temp;
                temp = min(B1, C1);
                num = num + price[2] * temp;
            }
            max_num = max(num, max_num);
        }
        printf("%d\n",max_num);
    }
    return 0;
}
