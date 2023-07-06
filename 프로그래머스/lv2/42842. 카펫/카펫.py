import math

def solution(brown, yellow):
    area = brown + yellow
    candidate_pairs = get_candidate_pair(area)
    return get_possible_pair(candidate_pairs, yellow)

def get_candidate_pair(area):
    result = []
    for l in range(1, math.ceil(area**(1/2)) + 1):
        for w in range(math.trunc(area/l), area+1):
            if area < w * l: break
            if area == w * l:
               result.append([w, l])
    return result

def get_possible_pair(pairs, yellow):
    diff_inside_outside = 2
    for width, length in pairs:
        if (width - diff_inside_outside) * (length - diff_inside_outside) == yellow:
            return [width, length]
