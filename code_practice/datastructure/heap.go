/*
	- heap 직접 구현함으로써 이해하기
	- 일반적으로 힙을 이용하여 정렬할 때 많이 사용하고 우선순위 큐 자료구조를 이용하여 구현함.
	- Less 조건 수정 시 MinHeap 구현 가능
*/

package datastructure

import "fmt"

type Heap []int

func NewHeap() *Heap {
	return &Heap{0}
}

func (m Heap) Print() {
	for i := 1; i <= m.Len(); i += 1 {
		fmt.Printf("%d ", m[i])
	}
}

func (m Heap) Less(sIndex int, tIndex int) bool {
	return m[sIndex] < m[tIndex]
}

func (m Heap) Len() int {
	return len(m) - 1
}

func (m Heap) IsEmpty() bool {
	return m.Len() == 0
}

func (m Heap) Peek() (int, error) {
	if m.Len() == 0 {
		return 0, fmt.Errorf("Heap is empty")
	}
	return m[1], nil
}

func (m Heap) GetParent(now int) int {
	return int(now / 2)
}

func (m *Heap) Push(num int) int {
	*m = append(*m, num)
	nowIndex := m.Len()
	parent := m.GetParent(nowIndex)
	return m.upHeap(parent, nowIndex)
}

func (m Heap) upHeap(pIndex int, nIndex int) int {
	if pIndex >= 1 && m.Less(pIndex, nIndex) {
		m.swap(pIndex, nIndex)
		nIndex, pIndex = pIndex, m.GetParent(pIndex)
		return m.upHeap(pIndex, nIndex)
	}
	return nIndex
}

func (m Heap) swap(tIndex int, sIndex int) {
	m[sIndex], m[tIndex] = m[tIndex], m[sIndex]
}

func (m *Heap) DeleteMax() (int, error) {
	if m.IsEmpty() {
		return 0, fmt.Errorf("Empty heap can't delete max")
	}

	mInt := m.pop()
	m.downHeap(1)
	return mInt, nil
}

func (m *Heap) pop() int {
	m.swap(1, m.Len())
	old := *m
	mInt := old[m.Len()]
	*m = old[0:m.Len()]
	return mInt
}

func (m Heap) downHeap(nIndex int) {
	if m.Len() < nIndex {
		return
	}

	if lChildIndex, err := m.GetLeftChildIndex(nIndex); err == nil && m.Less(nIndex, lChildIndex) {
		m.swap(nIndex, lChildIndex)
		m.downHeap(lChildIndex)
	}
	if rChildIndex, err := m.GetRightChildIndex(nIndex); err == nil && m.Less(nIndex, rChildIndex) {
		m.swap(nIndex, rChildIndex)
		m.downHeap(rChildIndex)
	}
}

func (m Heap) GetLeftChildIndex(nIndex int) (int, error) {
	lChildIndex := nIndex * 2
	if m.Len() < lChildIndex {
		return 0, fmt.Errorf("Not present left child")
	}
	return lChildIndex, nil
}

func (m Heap) GetRightChildIndex(nIndex int) (int, error) {
	rChildIndex := nIndex*2 + 1
	if m.Len() < rChildIndex {
		return 0, fmt.Errorf("Not present right child")
	}
	return rChildIndex, nil
}
