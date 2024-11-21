package main

import (
	"fmt"

	"code_practice/datastructure"
)

var segmentTree *datastructure.SegmentTree

func heap_run() {
	var n int
	fmt.Scanln(&n)

	m := datastructure.NewHeap()
	for i := 0; i < n; i++ {
		var num int
		fmt.Scanf("%d", &num)
		m.Push(num)
	}
	fmt.Print("Initialize: ")
	m.Print()
	fmt.Println()

	for {
		if p, err := m.Peek(); err == nil {
			fmt.Println("Peek: ", p)
		}

		mInt, err := m.DeleteMax()
		if err != nil {
			break
		}

		fmt.Println("Delete: ", mInt)
		fmt.Println()
	}
}

func seg_run() {
	nums := []int{1, 2, 3, 4}
	segmentTree = datastructure.GetSegmentTree(nums)
	result := segmentTree.GetRangeSum(segmentTree.GetRoot(), 0, 2)
	fmt.Println(result)

	segmentTree.UpdateSegTree(2, 1)
	result = segmentTree.GetRangeSum(segmentTree.GetRoot(), 0, 3)
	fmt.Println(result)
}

func main() {
	// seg_run()
	v := 10
	pV := &v
	println(pV)
	println(*pV)
}
