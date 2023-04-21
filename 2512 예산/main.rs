use std::cmp::{max, min};
use std::io;
use std::str::FromStr;

fn main() {
    let input_result = input();
    let vec = input_result.0;
    let budget = input_result.1;
    println!("{}", get_max_budget(budget, vec));
}

fn input() -> (Vec<i32>, i32) {
    let mut line = String::new();
    io::stdin().read_line(&mut line).expect("");
    line.clear();
    io::stdin().read_line(&mut line).expect("");
    let vec: Vec<_> = line.split_whitespace()
        .filter_map(|s| i32::from_str(s).ok())
        .collect();
    line.clear();
    io::stdin().read_line(&mut line).expect("");
    let m: i32 = line.trim().parse().expect("");
    return (vec, m);
}

fn get_max_budget(budget: i32, vec: Vec<i32>) -> i32 {
    let mut left = 0;
    let mut right = *vec.iter().max().expect("");
    let mut ans = 0;
    while left <= right {
        let mid = (left + right) / 2;
        if is_distribute_able(&budget, &mid, &vec) {
            ans = max(ans, mid);
            left = mid + 1;
            continue;
        }
        right = mid - 1;
    }
    return ans;
}

fn is_distribute_able(budget: &i32, exceed: &i32, vec: &Vec<i32>) -> bool {
    let mut distribute = 0;
    for i in vec {
        distribute += min(exceed, i);
    }
    return distribute <= *budget;
}
