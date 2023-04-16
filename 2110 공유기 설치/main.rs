use std::cmp::{max};
use std::io;
use std::str::FromStr;

fn main(){
    let input_value = input();
    let m = input_value.1;
    let cities = input_value.2;
    println!("{}", get_switch(m, &cities));
}

fn input() -> (i64, i64, Vec<i64>){
    let mut input: String = String::new();
    io::stdin().read_line(&mut input).expect("");
    let split: Vec<_> = input.split_whitespace()
        .filter_map(|s| i64::from_str(s).ok())
        .collect();
    let n = split[0];
    let m = split[1];

    let mut ans: Vec<_> = Vec::new();
    for _i in 0..n {
        input.clear();
        io::stdin().read_line(&mut input).expect("");
        ans.push(input.trim().parse().unwrap());
    }
    ans.sort();
    return (n, m, ans);
}

fn get_switch(m: i64, cities: &Vec<i64>) -> i64{
    let mut left: i64 = 1;
    let mut right: i64 = 1000000000;
    let mut ans = 0;
    while left <= right {
        let middle = (left + right) / 2;
        if is_settable(m, middle, cities){
            left = middle + 1;
            ans = max(ans, middle);
            continue;
        }
        right = middle - 1;
    }
    return ans;
}

fn is_settable(mut m: i64, middle: i64, cities: &Vec<i64>) -> bool{
    let mut last_set_idx = cities[0];
    m -= 1;
    for i in cities{
        if m <= 0 {
            break;
        }
        if i - last_set_idx >= middle {
            last_set_idx = *i;
            m -= 1;
        }
    }
    return m == 0;
}
