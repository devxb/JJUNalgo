package main

import "fmt"

func main() {
	var count int
	_, _ = fmt.Scan(&count)
	for i := 0; i < count; i++ {
		var x, y int
		_, _ = fmt.Scan(&x, &y)
		if x < y {
			fmt.Println("NO BRAINS")
			continue
		}
		fmt.Println("MMM BRAINS")
	}
}
