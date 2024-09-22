package datastructure

type Node struct {
	start, end     int
	value          int
	lChild, rChild *Node
}

func (n Node) GetLeftChild() *Node {
	return n.lChild
}

func (n Node) GetRightChild() *Node {
	return n.rChild
}

func (n Node) IsLeafNode() bool {
	return n.start == n.end
}

func (n Node) IsPossibleRange(length int) bool {
	rInfo := []int{n.start, n.end}
	for _, v := range rInfo {
		if v < 0 || v >= length {
			return false
		}
	}
	return n.start <= n.end
}

func (n *Node) sumChilds() {
	if n.lChild == nil && n.rChild == nil {
		return
	}
	n.value = n.lChild.value + n.rChild.value
}

type SegmentTree struct {
	root *Node
}

func GetSegmentTree(values []int) *SegmentTree {
	rNode := initSegTree(0, len(values)-1, values)
	return &SegmentTree{
		root: rNode,
	}
}

func initSegTree(s int, e int, values []int) *Node {
	n := Node{
		start: s,
		end:   e,
	}
	if !n.IsPossibleRange(len(values)) {
		return nil
	}
	if s == e {
		n.value = values[s]
		return &n
	}

	mid := int((s + e) / 2)
	n.lChild = initSegTree(s, mid, values)
	n.rChild = initSegTree(mid+1, e, values)
	n.sumChilds()
	return &n
}

func (s SegmentTree) GetRoot() *Node {
	return s.root
}

func (s SegmentTree) GetRangeSum(n *Node, start int, end int) int {
	if n.end < start || n.start > end || start > end {
		return 0
	}
	if n.start >= start && n.end <= end {
		return n.value
	}

	lValue := Max(n.start, start)
	rValue := Min(n.end, end)
	return s.GetRangeSum(n.lChild, lValue, rValue) + s.GetRangeSum(n.rChild, lValue, rValue)
}

func (s SegmentTree) UpdateSegTree(i int, v int) {
	s.updateNodeValue(s.GetRoot(), i, v)
}

func (s SegmentTree) updateNodeValue(n *Node, i int, v int) {
	if n.IsLeafNode() && n.start == i {
		n.value = v
		return
	}

	if n.start <= i && i <= n.end {
		s.updateNodeValue(n.lChild, i, v)
		s.updateNodeValue(n.rChild, i, v)
		n.value = n.lChild.value + n.rChild.value
	}
}

func Max(a int, b int) int {
	if a > b {
		return a
	}
	return b
}

func Min(a int, b int) int {
	if a < b {
		return a
	}
	return b
}
