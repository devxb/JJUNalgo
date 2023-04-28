use std::io;
use std::str::FromStr;

fn main() {
    let computer_count = input_computer_count();
    let computer_node: Vec<_> = input_computer_node(computer_count);
    println!(
        "{}",
        infect_virus(1, &computer_node, &mut init_check_vec(computer_count)) - 1
    );
}

fn input_computer_count() -> i32 {
    let mut line = String::new();

    io::stdin().read_line(&mut line).unwrap();

    return line.trim().parse().unwrap();
}

fn input_computer_node(computer_count: i32) -> Vec<Vec<i32>> {
    let mut line = String::new();
    io::stdin().read_line(&mut line).unwrap();
    let node_count = line.trim().parse().unwrap();

    let mut computer_node_vec: Vec<Vec<i32>> = Vec::new();
    for _i in 0..computer_count + 1 {
        computer_node_vec.push(Vec::new());
    }
    for _i in 0..node_count {
        line.clear();
        io::stdin().read_line(&mut line).unwrap();
        let split_vec: Vec<i32> = line
            .split_whitespace()
            .filter_map(|s| i32::from_str(s).ok())
            .collect();
        computer_node_vec[split_vec[0] as usize].push(split_vec[1]);
        computer_node_vec[split_vec[1] as usize].push(split_vec[0]);
    }
    return computer_node_vec;
}

fn init_check_vec(computer_count: i32) -> Vec<bool> {
    let mut check: Vec<bool> = Vec::new();
    for _i in 0..computer_count + 1 {
        check.push(false);
    }
    return check;
}

fn infect_virus(idx: i32, computer_node: &Vec<Vec<i32>>, check: &mut Vec<bool>) -> i32 {
    let mut ans: i32 = 1;
    check[idx as usize] = true;
    for i in &computer_node[idx as usize] {
        if check[*i as usize] {
            continue;
        }
        ans += infect_virus(*i, computer_node, check);
    }
    return ans;
}
