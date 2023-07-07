import heapq


class DoubledPriorityQueue:
    def __init__(self):
        self.__min_heapq = []
        self.__max_heapq = []
        self.__heapq_length = 0
    
    def push(self, number):
        heapq.heappush(self.__min_heapq, number)
        heapq.heappush(self.__max_heapq, number*-1)
        self.__increase_length()
    
    def pop_min(self):
        value = heapq.heappop(self.__min_heapq)
        self.__decrease_length()
        return value
    
    def pop_max(self):
        value = heapq.heappop(self.__max_heapq) * -1
        self.__decrease_length()
        return value
        
    def is_empty(self):
        return self.__heapq_length == 0
        
    def __clear(self):
        self.__min_heapq.clear()
        self.__max_heapq.clear()
    
    def __increase_length(self):
        self.__heapq_length += 1
    
    def __decrease_length(self):
        self.__heapq_length -= 1
        if self.is_empty(): self.__clear()
        

def solution(operations):
    double_priorityq = DoubledPriorityQueue()
    
    for oper in operations:
        command, number = oper.split(" ")
        if is_insert_command(command):
            double_priorityq.push(int(number))
        else:
            if double_priorityq.is_empty(): continue
            if int(number) == 1:
                double_priorityq.pop_max()
            else:
                double_priorityq.pop_min()
    
    return [0, 0] if double_priorityq.is_empty() else [double_priorityq.pop_max(), double_priorityq.pop_min()]

def is_insert_command(command):
    return command == "I"
