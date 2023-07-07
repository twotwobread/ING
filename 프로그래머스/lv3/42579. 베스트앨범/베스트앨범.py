def solution(genres, plays):
    genres_count = dict()
    musics = []
    for m in range(len(genres)):
        if genres[m] in genres_count:
            genres_count[genres[m]] += plays[m]
        else:
            genres_count[genres[m]] = plays[m]
        musics.append((m, genres[m], plays[m])) # id, 장르, 재생횟수
    
    sorted_music = sorted(musics, key = lambda m : (-genres_count[m[1]], -m[2], m[0]))
    return get_two_music_by_genre(sorted_music)

def get_two_music_by_genre(sorted_music):
    result = []
    check = dict()
    for id, genre, play_count in sorted_music:
        if genre in check:
            if check[genre] >= 2: continue
            result.append(id)
            check[genre] += 1
        else:
            result.append(id)
            check[genre] = 1
    return result