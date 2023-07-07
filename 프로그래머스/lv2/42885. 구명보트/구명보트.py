from collections import deque

def solution(people, limit):
    people.sort(reverse=True)
    return count_need_boat(people, limit)

def count_need_boat(people, limit):
    result = 0;
    min_index = len(people) - 1
    for i in range(len(people)):
        if i > min_index: break;
        if i < min_index and people[i] + people[min_index] <= limit:
            min_index -= 1
        result += 1
    return result
    
    