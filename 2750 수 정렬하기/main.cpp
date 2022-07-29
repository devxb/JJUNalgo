// 정확성 테스트 - 2750

#include <iostream>
#include <algorithm>
#include <string>

using namespace std;

int N, P;

int quickSort(int arr[], int left, int right){
    int pivot = arr[(left+right)/2];
    int pivotIdx = 0;
    for(int i = left; i <= right; i++) if(arr[i] < pivot) pivotIdx++;
    int temp[right+1];
    temp[left + pivotIdx] = pivot;
    int li = left, ri = right;
    for(int i = left; i <= right; i++){
        if(i == (left+right)/2) continue;
        if(arr[i] <= pivot) {
            temp[li] = arr[i];
            li++;
            continue;
        }
        temp[ri] = arr[i];
        ri--;
    }
    for(int i = left; i <= right; i++) arr[i] = temp[i];
    return left + pivotIdx;
}

void quick(int arr[], int left, int right){ // 퀵 정렬
    if(left >= right) return;
    int pivotIdx = quickSort(arr, left, right);
    quick(arr, left, pivotIdx-1);
    quick(arr, pivotIdx+1, right);
}

void mergeSort(int arr[], int left, int right){
    int temp[right+1];
    int mid = (left + right) / 2;
    int i = left, j = mid+1, r = left-1;
    while(i <= mid && j <= right){
        r++;
        if(arr[i] <= arr[j]){
            temp[r] = arr[i];
            i++;
            continue;
        }
        temp[r] = arr[j];
        j++;
    }
    while(i <= mid) {
        r++;
        temp[r] = arr[i];
        i++;
    }
    while(j <= right){
        r++;
        temp[r] = arr[j];
        j++;
    }
    for(int i = left; i <= right; i++) arr[i] = temp[i];
}

void merge(int arr[], int left, int right){ // 병합 정렬
    if(left >= right) return;
    int mid = (left + right) / 2;
    merge(arr, left, mid);
    merge(arr, mid+1, right);
    mergeSort(arr, left, right);
}

void bubble(int arr[], int size){ // 버블 정렬
    for(int i = 0; i < size; i++){
        for(int j = 0; j < size-(i+1); j++){
            if(arr[j] > arr[j+1]){
                int temp = arr[j+1];
                arr[j+1] = arr[j];
                arr[j] = temp; 
            }
        }
    }
}

void inputArr(int arr[], int size){
    for(int i = 0; i < size; i++) cin >> arr[i];
}

void printArr(int arr[], int size){
    for(int i = 0; i < size; i++) cout << arr[i] << "\n";
}

int main() {
    ios::sync_with_stdio(false);
	cin.tie(NULL);
    cout.tie(NULL);
    int N;
    cin >> N;
    int arr[N];
    inputArr(arr, N);
    // bubble(arr, N);
    // merge(arr, 0, N-1);
    quick(arr, 0, N-1);
    printArr(arr, N);
    return 0;
}
