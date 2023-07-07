def solution(n, computers):
    result = 0
    visited = [ False for _ in range(n) ]
    for i in range(n):
        if not visited[i]:
            dfs(computers, visited, i)
            result += 1
    return result

def dfs(computers, visited, now):
    visited[now] = True
    for adj in range(len(computers[now])):
        if is_possible_go(computers, visited, now, adj): 
            dfs(computers, visited, adj)

def is_possible_go(computers, visited, now, adj):
    if now == adj or visited[adj]: return False
    if not computers[now][adj]: return False
    return True