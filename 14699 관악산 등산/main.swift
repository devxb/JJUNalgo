//
//  main.swift
//  14699 관악산 등산
//
//  Created by 이준영 on 2020/11/18.
//

import Foundation

func insert(N: Int, M: Int) -> Void{
    let num = readLine()!.components(separatedBy: " ")
    for i in 0..<num.count{
        height[i+1] = Int(num[i])!
    }
    for _ in 1...M{
        let fromto = readLine()!.components(separatedBy: " ")
        let from = Int(fromto.first!)!
        let to = Int(fromto.last!)!
        if(height[from] > height[to]){
            graph[to].append(from)
        }
        else if(height[from] < height[to]){
            graph[from].append(to)
        }
    }
    return
}

func go(idx: Int) -> Int{
    if(graph[idx].count == 0){
        dp[idx] = 1
        return dp[idx]
    }
    if(dp[idx] != -1){
        return dp[idx]
    }
    for i in 0..<graph[idx].count{
        dp[idx] = max(dp[idx],1+go(idx: graph[idx][i]));
    }
    return dp[idx]
}

var dp : [Int] = Array(repeating: -1, count: 5005)
var height: [Int] = Array(repeating: -1, count: 5005)
var graph: [[Int]] = Array(repeating: Array(), count: 5005)
let NM = readLine()?.components(separatedBy: " ")
let N = Int((NM?.first!)!)!
let M = Int((NM?.last!)!)!
insert(N: N, M: M)
for i in 1...N{
    go(idx: i)
}
for i in 1...N{
    print(dp[i])
}
