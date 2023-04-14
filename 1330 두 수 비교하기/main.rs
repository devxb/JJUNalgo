use std::cmp::Ordering;
use std::io;
use std::str::FromStr;

fn main() {
    let mut input = String::new();

    io::stdin().read_line(&mut input)
        .expect("Cannot read line");

    let split: Vec<_> = input.split_whitespace()
        .filter_map(|i| i32::from_str(&i).ok())
        .collect();

    match split[0].cmp(&split[1]){
        Ordering::Less => println!("<"),
        Ordering::Equal => println!("=="),
        Ordering::Greater => println!(">")
    };
}
