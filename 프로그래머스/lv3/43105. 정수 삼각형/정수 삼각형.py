def solution(triangle):
    dp = [
        [ 0 for _ in range(len(triangle[len(triangle)-1]))] 
        for _ in range(len(triangle))
    ]
    dp[0][0] = triangle[0][0]
    find_all_path(dp, triangle)
    return get_max_sum_path(dp)

def find_all_path(dp, triangle):
    for r in range(1, len(triangle)):
        for c in range(len(triangle[r])):
            if c == 0: 
                dp[r][c] = dp[r-1][c] + triangle[r][c]
            elif c == len(triangle[r]) - 1: 
                dp[r][c] = dp[r-1][c-1] + triangle[r][c]
            else:
                dp[r][c] = max(dp[r-1][c], dp[r-1][c-1]) + triangle[r][c]

def get_max_sum_path(dp):
    return max(list(map(max, dp)))