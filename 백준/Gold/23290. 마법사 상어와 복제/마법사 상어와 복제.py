# file name : 마법사 상어와 복제.py
# author : lee suyoung(2022-04-27)
# 4x4 격자 사용, 격자 가장 왼쪽 윗 칸 = (1, 1), 격자 오른쪽 아랫 칸 = (4,4)
# 격자에 물고기 M마리 존재, 격자의 칸하나에 들어가 있고 이동방향 가짐.
# 이동방향은 상하좌우, 대각선
# 마법사 상어도 격자의 한 칸에 들어가 있음. 둘 이상의 물고기가 같은 칸에 존재 가능.
# 상어와 물고기가 같은 칸에 있을 수도 있음.
# 상어의 마법 연습 과정
# 1. 모든 물고기에게 복제 시전, 복제 마법은 시간이 걸려 5번에서 물고기가 복제되어 나타남.
# 2. 모든 물고기 한 칸 이동. 상어있는 칸, 물고기 냄새 있는 칸, 격자 범위 벗어나는 칸 이동 불가.
#    각 물고기 자신의 이동방향으로 한 칸 이동 불가능하면 45도 반시계 회전. 이동 가능한 칸이
#    없다면 이동 x
# 3. 상어가 연속해서 3칸 이동. 상어는 상하좌우 인접한 칸 이동가능. 연속해서 이동하는 칸 중
#    격자의 점위를 벗어나는 칸이 있다면, 그 방법은 이동 불가능한 방법. 연속해서 이동하는 중
#    상어가 물고기가 있는 같은 칸으로 이동하면, 그 칸에 있는 모든 물고기는 격자에서 제외되고
#    모든 물고기는 물고기 냄새를 남김. 가능한 이동 방법 중 제외되는 물고기의 수가 가장 많은 방법
#    으로 이동하며, 그러한 방법이 여러가지인 경우 사전 순으로 가장 앞서는 방법을 이용.
# 4. 두 번 전 연습에서 생긴 물고기의 냄새가 격자에서 사라짐.
# 5. 1에서 사용한 복제 마법이 완료됨. 모든 복제된 물고기는 1에서의 위치와 방향을 그대로 가짐.
# S번 연습을 모두 마쳤을때, 격자에 있는 물고기의 수를 구하자.

# < 입력 >
# 1<=m<=10(물고기 수), 1<=s<=100(마법 연습한 횟수)
# m개의 줄, fx, fy, d (물고기 정보= 물고기 위치, 방향)
# 왼, 왼위, 위, 오위, 오, 오아래, 아래 왼아래 (1~8)
# 마지막 졸 sx, sy ( 상어의 위치 )
# 격자 위에 있는 물고기의 수가 항상 1,000,000 이하인 입력만 주어짐.

# < 출력 >
# s번 연습을 마친 후 격자에 있는 물고기의 수 출력

# 시간제한 : 2초, 메모리 제한 : 1024MB

N = 4
dx = (0, -1, -1, -1, 0, 1, 1, 1) #왼, 왼위, 위, 오위, 오, 오아래, 아래 왼아래 (1~8)
dy = (-1, -1, 0, 1, 1, 1, 0, -1)
def fishMove(fishInfo, sharkPos, fish_smell):
    new = [[[] for _ in range(N)] for _ in range(N)]
    for i in range(N):
        for j in range(N):
            fishList = fishInfo[i][j]
            for f in fishList:
                check = False
                for k in range(8):
                    direct = (f-k) if (f-k) > 0 else 8 + (f-k)
                    adj_row, adj_col = i + dx[direct-1], j + dy[direct-1]
                    if 0<=adj_row<N and 0<=adj_col<N and fish_smell[adj_row][adj_col] == 0 and (adj_row != sharkPos[0]-1 or adj_col != sharkPos[1]-1):
                        new[adj_row][adj_col].append(direct)
                        check = True
                        break
                if not check:
                    new[i][j].append(f)
            fishInfo[i][j].clear()
    for i in range(N):
        for j in range(N):
            fishInfo[i][j].extend(new[i][j])
MINSTR = 1e9
FISHNUM = 0
MAXNUM = -10
RESULT = []
BEFORESHARKPOS = [-1, -1]
sharkDx = (0, -1, 0, 1, 0) # 상, 좌, 하, 우
sharkDy = (0, 0, -1, 0, 1)
def sharkMove(fishInfo, sharkPos):
    global MINSTR, MAXNUM, FISHNUM, BEFORESHARKPOS
    if len(RESULT) == 3:
        temp = "".join(RESULT)
        if FISHNUM > MAXNUM:
            MAXNUM = FISHNUM
            MINSTR = int(temp)
        elif FISHNUM == MAXNUM:
            if MINSTR > int(temp):
                MINSTR = int(temp)
        return

    for i in range(1, 5):
        adj_row, adj_col = (sharkPos[0]-1) + sharkDx[i], (sharkPos[1]-1) + sharkDy[i]
        if 0<=adj_row<N and 0<=adj_col<N: #and (adj_row != (BEFORESHARKPOS[0]-1) or adj_col != (BEFORESHARKPOS[1]-1)):
            FISHNUM += len(fishInfo[adj_row][adj_col])
            RESULT.append(str(i))
            before = []
            if len(fishInfo[adj_row][adj_col]) > 0:
                before.extend(fishInfo[adj_row][adj_col])
            fishInfo[adj_row][adj_col].clear()
            #beforesharkrow, beforesharkcol = BEFORESHARKPOS[0], BEFORESHARKPOS[1]
            #BEFORESHARKPOS[0], BEFORESHARKPOS[1] = sharkPos[0], sharkPos[1]
            sharkMove(fishInfo, [adj_row+1, adj_col+1])
            fishInfo[adj_row][adj_col].extend(before)
            #BEFORESHARKPOS[0], BEFORESHARKPOS[1] = beforesharkrow, beforesharkcol
            RESULT.pop()
            FISHNUM -= len(fishInfo[adj_row][adj_col])
    return MINSTR

def solution(m, s, fishInfo, sharkPos):
    global MINSTR, FISHNUM, MAXNUM, RESULT, BEFORESHARKPOS
    fish_smell = [[0]*N for _ in range(N)] # 이거도 2차원으로 만들어야할듯.
    for _ in range(s):
        MINSTR = 1e9
        FISHNUM = 0
        MAXNUM = -10
        RESULT = []
        BEFORESHARKPOS = [-1, -1]
        # 1. 복제하기. 5에서 실제 적용
        copyFish = [[[] for _ in range(N)] for _ in range(N)]
        for i in range(N):
            for j in range(N):
                copyFish[i][j].extend(fishInfo[i][j])
        # 복제하는 거 확실하게 공부하기.

        # 2. 모든 물고기 이동.
        fishMove(fishInfo, sharkPos, fish_smell)
        # 3. 상어의 연속적인 이동
        # - 상하좌우로 이동 ( 연속 이동 전부 격자의 범위 안에 있어야함. )
        # - 연속 이동 중 상어가 물고기가 있는 같은 칸으로 이동 시 모든 물고기 제외
        #   그리고 물고기 냄새 남기기.
        # - 가능한 이동 방법 중 제외되는 물고기의 수가 가장 많은 방법으로 이동.
        # - 방법이 여러가지인 경우 사전 순으로 가장 앞서는 방법 사용
        intRoute = sharkMove(fishInfo, sharkPos)
        # 이부분 밖에서 최종적으로 값을 받았을때 써야할듯.
        new_row, new_col = sharkPos[0]-1, sharkPos[1]-1
        for s in str(intRoute):
            new_row, new_col = new_row + sharkDx[int(s)], new_col + sharkDy[int(s)]
            if len(fishInfo[new_row][new_col]) > 0:
                fish_smell[new_row][new_col] = 3
                fishInfo[new_row][new_col].clear()
        sharkPos[0], sharkPos[1] = new_row+1, new_col+1
        # 4. 2회 전 물고기의 냄새 사라지기
        for i in range(N):
            for j in range(N):
                if fish_smell[i][j] > 0:
                    fish_smell[i][j] -= 1
        # 5. 복제 마법 완료
        for i in range(N):
            for j in range(N):
                fishInfo[i][j].extend(copyFish[i][j])
    SUM = 0
    for i in range(N):
        for j in range(N):
           SUM += len(fishInfo[i][j])
    return SUM

if __name__ == "__main__":
    m, s = map(int, input().split(" "))
    fishInfo = [[[] for _ in range(N)] for _ in range(N)]
    for _ in range(m):
        fx, fy, d = map(int, input().split(" "))
        fishInfo[fx-1][fy-1].append(d)
    sharkPos = list(map(int, input().split(" ")))
    print(solution(m, s, fishInfo, sharkPos))