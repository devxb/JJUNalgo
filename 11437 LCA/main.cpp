#include <iostream>
#include <algorithm>
#include <cmath>
#include <string>
#include <vector>

using namespace std;

int N, M;
int par[50005][20];
int farRoot[50005];
int diffFar[50005];
vector<int> vec[50005];

int transFarTwoHeight(int far){
    return diffFar[far];
}

int liftUp(int &element1, int &element2, int &element1Par, int &element2Par){
    int height = 0;
    while(element1Par != element2Par && height <= transFarTwoHeight(farRoot[element1])){
        element1Par = par[element1][height];
        element2Par = par[element2][height];
        height++;
    }
    return height > 0 ? height-1 : height;
}

void balanceFarRoot(int &element1, int &element2){
    while(farRoot[element1] != farRoot[element2]){
        if(farRoot[element1] < farRoot[element2]) element2 = par[element2][transFarTwoHeight(farRoot[element2] - farRoot[element1])];
        if(farRoot[element1] > farRoot[element2]) element1 = par[element1][transFarTwoHeight(farRoot[element1] - farRoot[element2])];
    }
}

int findCommonAncestor(int element1, int element2){
    balanceFarRoot(element1, element2);
    if(element1 == element2) return element1;
    int element1Par = par[element1][0], element2Par = par[element2][0];
    int resultCommonPar = 1;
    while(element1 != element2){
        element1Par = par[element1][0], element2Par = par[element2][0];
        int maxHeight = transFarTwoHeight(farRoot[element1]);
        int lifted = liftUp(element1, element2, element1Par, element2Par);
        if(element1Par != element2Par){
            element1 = par[element1][lifted];
            element2 = par[element2][lifted];
        }
        else if(element1Par == element2Par && lifted > 0){
            element1 = par[element1][lifted-1];
            element2 = par[element2][lifted-1];
        }
        else if(lifted == 0){
            element1 = par[element1][0];
            element2 = par[element2][0];
        }
    }
    return element1;
}

int updateParAncestorElement(int idx, int dist){
    if(idx == 1) return 1;
    if(dist == 0 || par[idx][dist] != -1) return par[idx][dist];
    return par[idx][dist] = updateParAncestorElement(par[idx][dist-1], dist-1);
}

void updateParAncestors(){
    for(int j = 1; j <= transFarTwoHeight(N); j++)
        for(int i = 2; i <= N; i++) updateParAncestorElement(i, j);
}

int updateFarRootElement(int idx){
    if(idx == 1) return 0;
    if(farRoot[idx] != 0) return farRoot[idx];
    return farRoot[idx] = updateFarRootElement(par[idx][0])+1;
}

void updateFarRoot(){
    for(int i = N; i >= 2; i--) updateFarRootElement(i);
}

void inputQuery(){
    cin >> M;
    updateFarRoot();
    updateParAncestors();
    par[1][0] = 0;
    for(int i = 0; i < M; i++){
        int element1, element2;
        cin >> element1 >> element2;
        cout << findCommonAncestor(element1, element2) << "\n";
    }
}

void initParFromVec(int idx, int parNode){
    par[idx][0] = parNode;
    for(int i = 0; i < vec[idx].size(); i++)
        if(vec[idx][i] != parNode) initParFromVec(vec[idx][i], idx);
}

void inputPar(){
    for(int i = 1; i < N; i++){
        int node1, node2;
        cin >> node1 >> node2;
        if(node1 == node2) continue;
        vec[node1].push_back(node2);
        vec[node2].push_back(node1);
    }
    initParFromVec(1, -1);
}

void initDiffFar(){
    diffFar[0] = diffFar[1] = 0;
    int two = 2, height = 1;
    for(int i = 2; i <= 50000; i++){
        if(two*2 <= i) {
            two *= 2;
            height++;
        }
        diffFar[i] = height;
    }
}

int main() {
    ios::sync_with_stdio(false);
	cin.tie(NULL);
    cout.tie(NULL);
    cin >> N;
    initDiffFar();
    for(int i = 1; i <= N; i++)
        for(int j = 0; j <= 17; j++) par[i][j] = -1;
    inputPar();
    inputQuery();
    return 0;
}
