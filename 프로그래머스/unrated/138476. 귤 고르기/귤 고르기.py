MAX_SIZE = 10000000

def solution(k, tangerine):
    size_arr = function(tangerine)
    size_arr.sort(reverse=True)
    return get_min_size_type(size_arr, k)

def function(tangerine):
    size_arr = [ 0 for _ in range(MAX_SIZE + 1) ]
    for t in tangerine:
        size_arr[t] += 1
    return size_arr

def get_min_size_type(size_arr, k):
    result = 0
    for s in size_arr:
        if k <= 0: break
        k -= s
        result += 1
    return result