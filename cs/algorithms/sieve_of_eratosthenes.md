# 에라토스테네스의 체
- 소수 판별을 O(N * log logN)에 가능.
## 원리
- 소수의 배수는 소수가 아니다.
- 위 성질을 이용해서 체로 거르듯 판별.
- loop을 낮은 수부터 돌면서 그 수가 소수일 때, 배수들은 모두 소수가 아님을 flagging. (이를 특정 확인하고자 하는 수 이하까지만 확인)
## 코드
- https://www.codetree.ai/missions/4/problems/get-prime/description
```go
package main

import "fmt"

func main() {
	var n int
	fmt.Scanln(&n)
	if n <= 1 {
		return
	}

	primes := SeiveOfEratosthenes(n)
	for i := 2; i <= n; i++ {
		if primes[i] {
			fmt.Printf("%d ", i)
		}
	}
}

func SeiveOfEratosthenes(num int) []bool {
	primes := make([]bool, num+1)
	for i := 2; i <= num; i++ {
		primes[i] = true // 2이상의 수들을 소수가 맞다고 initialize
	}

	for i := 2; i*i <= num; i++ { // 순회 시 바로 소수를 가져오기 위해서 2부터 출발.
		if primes[i] { // 해당 값이 소수인 경우
			for j := i * i; j <= num; j += i {
				primes[j] = false // 배수들을 소수가 아니라고 flagging
			}
		}
	}
	return primes
}

```
