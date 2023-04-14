use std::io;
use std::str::FromStr;

fn main(){
    let mut input: String = Default::default();
    io::stdin().read_line(&mut input).expect("");
    let t = i32::from_str(input.trim()).expect("");
    for _i in 0..t {
        input.clear();
        io::stdin().read_line(&mut input).expect("");
        let split: Vec<_> = input.split_whitespace()
            .filter_map(|i| i32::from_str(i).ok())
            .collect();

        println!("{}", (split[0] + split[1]));
    }
}
