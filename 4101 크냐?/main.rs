use std::cmp::Ordering;
use std::io;
use std::str::FromStr;

fn main() {
    let mut left:i32 = -1;
    let mut right:i32 = -1;
    loop {
        let mut input = String::new();
        io::stdin().read_line(&mut input).expect("");
        let split: Vec<_> = input.split_whitespace()
            .filter_map(|i| i32::from_str(i).ok())
            .collect();
        left = split[0];
        right = split[1];
        if left == 0 && right == 0 {
            break;
        }
        match left.cmp(&right) {
            Ordering::Less => println!("No"),
            Ordering::Equal => println!("No"),
            Ordering::Greater => println!("Yes")
        }
    }
}
