use std::io;
use std::str::FromStr;

fn input() -> i32 {
    let mut input = String::new();
    io::stdin().read_line(&mut input).expect("Input error");
    return i32::from_str(input.trim()).expect("Parse error");
}

fn main() {
    let mut input_arr: Vec<i32> = Default::default();
    for _i in 0..5 {
        input_arr.push(input());
    }
    input_arr[0..3].sort();
    input_arr[3..5].sort();
    println!("{}", (input_arr[0] + input_arr[3] - 50));
}
