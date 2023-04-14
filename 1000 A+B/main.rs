use std::io;
use std::str::FromStr;

fn main() {
    let mut input = String::new();

    io::stdin().read_line(&mut input)
        .expect("Failed to read line");

    let split: Vec<_> = input.split_whitespace()
        .filter_map(|i| i32::from_str(i).ok())
        .collect();

    println!("{}", (split[0] + split[1]));
}
