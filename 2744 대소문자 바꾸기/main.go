package main

import "fmt"

func main() {
	var line string
	fmt.Scan(&line)

	for i := 0; i < len(line); i++ {
		if 'a' <= line[i] {
			fmt.Print(string(rune(int(line[i]) - 32)))
			continue
		}
		fmt.Print(string(rune(int(line[i]) + 32)))
	}
}
