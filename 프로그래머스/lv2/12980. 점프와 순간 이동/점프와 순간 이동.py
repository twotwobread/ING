# def solution(n):
#     fuel = 0
#     while n!=0:
#         n, fuel = move(n, fuel)
#     return fuel

# def move(n, fuel):
#     if n % 2 == 0: return (n / 2, fuel)
#     else: return (n - 1, fuel + 1)

def solution(n):
    fuel = 1
    while n != 1:
        if n % 2 == 1:
            n -= 1
            fuel +=1
        else:
            n /= 2
    return fuel