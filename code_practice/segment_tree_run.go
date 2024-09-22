package main

import (
	"fmt"

	"code_practice/datastructure"
)

var segmentTree *datastructure.SegmentTree

func main() {
	nums := []int{1, 2, 3, 4}
	segmentTree = datastructure.GetSegmentTree(nums)
	result := segmentTree.GetRangeSum(segmentTree.GetRoot(), 0, 2)
	fmt.Println(result)

	segmentTree.UpdateSegTree(2, 1)
	result = segmentTree.GetRangeSum(segmentTree.GetRoot(), 0, 3)
	fmt.Println(result)
}
