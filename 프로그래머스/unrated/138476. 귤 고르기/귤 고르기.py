from collections import Counter

def solution(k, tangerine):
    size_counter = get_size_distribution(tangerine)
    return get_min_size_type(sorted(size_counter.values(), reverse=True), k)

def get_size_distribution(tangerine):
    return Counter(tangerine)

def get_min_size_type(size_distribution_arr, k):
    result = 0
    for s in size_distribution_arr:
        if k <= 0: break
        k -= s
        result += 1;
    return result