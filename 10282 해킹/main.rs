use std::cmp::max;
use std::collections::BinaryHeap;
use std::io;
use std::str::FromStr;

fn main() {
    let mut line: String = String::new();
    io::stdin().read_line(&mut line).ok();
    let test_case: i32 = line.trim().parse().unwrap();
    for _i in 0..test_case {
        let input_result = input();
        let dijkstra_result = dijkstra(input_result);
        print!("{} {}\n", dijkstra_result.0, dijkstra_result.1);
    }
}

fn input() -> (i32, i32, Vec<Vec<(i32, i32)>>) {
    let mut line: String = String::new();
    io::stdin().read_line(&mut line).ok();
    let splited: Vec<_> = line
        .split_whitespace()
        .map(|s| i32::from_str(s).ok())
        .collect();
    let mut computer_edge: Vec<Vec<(i32, i32)>> = Default::default();
    for _i in 0..splited[0].unwrap() + 1 {
        computer_edge.push(Vec::new());
    }
    for _i in 0..splited[1].unwrap() {
        line.clear();
        io::stdin().read_line(&mut line).ok();
        let splited_node: Vec<_> = line
            .split_whitespace()
            .map(|s| i32::from_str(s).ok())
            .collect();

        computer_edge[splited_node[1].unwrap() as usize]
            .push((splited_node[0].unwrap(), splited_node[2].unwrap()));
    }
    return (splited[2].unwrap(), splited[0].unwrap(), computer_edge);
}

fn dijkstra(input_result: (i32, i32, Vec<Vec<(i32, i32)>>)) -> (i32, i32) {
    let mut check: Vec<_> = Vec::new();
    for _i in 0..input_result.1 + 1 {
        check.push(987654321);
    }
    let mut pq: BinaryHeap<(i32, i32)> = BinaryHeap::new();
    pq.push((0, input_result.0));
    while !pq.is_empty() {
        let item = pq.pop().unwrap();
        if check[item.1 as usize] <= -1 * item.0 {
            continue;
        }
        check[item.1 as usize] = -1 * item.0;
        for i in 0..input_result.2[item.1 as usize].len() {
            let next_idx: i32 = input_result.2[item.1 as usize][i].0;
            let next_cost: i32 = -1 * item.0 + input_result.2[item.1 as usize][i].1;
            if check[next_idx as usize] <= next_cost {
                continue;
            }
            pq.push((-1 * next_cost, next_idx));
        }
    }
    let mut ans = 0;
    let mut cnt = 0;
    for i in 1..input_result.1 + 1 {
        if check[i as usize] == 987654321 {
            continue;
        }
        cnt += 1;
        ans = max(ans, check[i as usize]);
    }
    return (cnt, ans);
}
