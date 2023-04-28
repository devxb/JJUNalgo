extern crate core;

use std::cmp::max;
use std::collections::VecDeque;
use std::io;

fn main(){
    loop {
        let case = input_case();
        if case == 0 {
            return;
        }
        let maze = input(&case);
        println!("{}", match is_reachable(case, maze) {
            true => "Yes",
            false => "No"
        });
    }
}

fn input(case: &i32) -> Vec<(char, i32, Vec<i32>)> {
    let mut maze: Vec<(char, i32, Vec<i32>)> = Vec::new();
    maze.push(('s', 0, Vec::new()));
    for _i in 1..case+1 {
        let room_info = input_room();
        maze.push((room_info.0, room_info.1, room_info.2));
    }
    return maze;
}

fn input_case() -> i32 {
    let mut line = String::new();

    io::stdin().read_line(&mut line).unwrap();

    return line.trim().parse().unwrap();
}

fn input_room() -> (char, i32, Vec<i32>) {
    let mut line: String = String::new();

    io::stdin().read_line(&mut line).unwrap();

    let mut iter = line.split_whitespace();

    let character: char = iter.next().unwrap().parse().unwrap();
    let cost: i32 = iter.next().unwrap().parse().unwrap();
    let mut room_vec: Vec<i32> = Vec::new();

    loop {
        let room: i32 = iter.next().unwrap().parse().unwrap();
        if room == 0 {
            break;
        }
        room_vec.push(room);
    }

    return (character, cost, room_vec);
}

fn is_reachable(case: i32, maze: Vec<(char, i32, Vec<i32>)>) -> bool {
    let mut check: Vec<i32> = Vec::new();
    for _i in 0..case+1 {
        check.push(-1);
    }
    let mut status_deq: VecDeque<(i32, i32)> = VecDeque::new();
    status_deq.push_back((1, calc_cost(1,0, &maze)));
    while !status_deq.is_empty() {
        let current_status = status_deq.pop_front().unwrap();
        if current_status.0 == case {
            return true;
        }
        if check[current_status.0 as usize] >= current_status.1 {
            continue;
        }
        check[current_status.0 as usize] = current_status.1;
        for i in &maze[current_status.0 as usize].2 {
            let next_cost = calc_cost(*i, current_status.1, &maze);
            if check[*i as usize] > next_cost {
                continue;
            }
            status_deq.push_back((*i, next_cost));
        }
    }
    return false;
}

fn calc_cost(idx: i32, current_cost: i32, maze: &Vec<(char, i32, Vec<i32>)>) -> i32{
    return match maze[idx as usize].0 {
        'T' => current_cost - maze[idx as usize].1,
        'L' => max(current_cost, maze[idx as usize].1),
        'E' => current_cost,
        _ => -1
    };
}
