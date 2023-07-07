import math

def solution(n,a,b):
    if a > b:
        a, b = b, a
    return function(n, 1, n, a, b)

def function(n, start, end, a, b):
    mid = (start + end) // 2
    if start >= end: return -1
    if a <= mid < b: return math.log(n, 2)
    return max(
        function(n//2, start, mid, a, b), 
        function(n//2, mid + 1, end, a, b))