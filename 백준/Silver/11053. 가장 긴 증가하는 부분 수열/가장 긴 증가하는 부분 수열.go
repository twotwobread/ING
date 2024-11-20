// https://www.acmicpc.net/problem/11053

package main

import (
	"bufio"
	"fmt"
	"os"
)

var (
	reader  *bufio.Reader
	printer *bufio.Writer
)

func init() {
	reader = bufio.NewReader(os.Stdin)
	printer = bufio.NewWriter(os.Stdout)
}

func main() {
	defer printer.Flush()

	var n int
	fmt.Fscanln(reader, &n)

	nums := make([]int, n+1)
	for i := 1; i <= n; i++ {
		fmt.Fscan(reader, &nums[i])
	}

	lis := GetLongestIncreasingSeq(n, nums)
	result := GetMaxCnt(lis)
	fmt.Fprintln(printer, result)
}

func GetLongestIncreasingSeq(n int, nums []int) []int {
	dp := make([]int, n+1)
	for i := 1; i <= n; i++ {
		for j := 0; j < i; j++ {
			if nums[i] > nums[j] && dp[i] < dp[j]+1 {
				dp[i] = dp[j] + 1
			}
		}
	}
	return dp
}

func GetMaxCnt(nums []int) int {
	max := 0
	for _, n := range nums {
		if max < n {
			max = n
		}
	}
	return max
}
