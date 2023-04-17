use std::cmp::{min};
use std::io;
use std::str::FromStr;

fn main() {
    let input_result = input();
    let m = input_result.1;
    let pair_vec = input_result.2;
    if is_never_divide_able(m, &pair_vec) {
        println!("-1");
        return;
    }
    println!("{}", find_beer(m, &pair_vec));
}

fn input() -> (i64, i64, Vec<(i64, i64)>) {
    let mut input: String = String::new();
    io::stdin().read_line(&mut input).expect("");
    let split: Vec<_> = input.split_whitespace().filter_map(|s| i64::from_str(s).ok()).collect();
    let n = split[0];
    let m = split[1];
    let mut pair_vec: Vec<(i64, i64)> = Vec::new();
    for _i in 0..n{
        input.clear();
        io::stdin().read_line(&mut input).expect("");
        let split: Vec<_> = input.split_whitespace().filter_map(|s| i64::from_str(s).ok()).collect();
        pair_vec.push((split[0], split[1]));
    }
    pair_vec.sort();
    return (n, m, pair_vec);
}

fn is_never_divide_able(m: i64, pair_vec: &Vec<(i64, i64)>) -> bool{
    let mut lower_bound: i64 = 0;
    let mut upper_bound: i64 = 0;
    for i in pair_vec {
        lower_bound += i.0;
        upper_bound += i.1;
    }
    return lower_bound > m || upper_bound < m;
}

fn find_beer(m: i64, pair_vec: &Vec<(i64, i64)>) -> i64{
    let mut ans = 1000000000;
    let mut left = 1;
    let mut right: i64 = i32::MAX as i64;
    while left <= right {
        let middle = (left + right) / 2;
        if is_beer_divide_able(m, middle, pair_vec) {
            right = middle - 1;
            ans = min(ans, middle);
            continue;
        }
        left = middle + 1;
        continue;
    }
    return ans;
}

fn is_beer_divide_able(m: i64, s: i64, pair_vec: &Vec<(i64, i64)>) -> bool{
    let mut divided = 0;
    for i in pair_vec {
        if s < i.0 {
            return false;
        }
        divided += min(s, i.1);
    }
    return m <= divided;
}
