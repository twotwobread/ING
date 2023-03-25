-- 코드를 입력하세요
select u1.title, u1.board_id, u2.reply_id, u2.writer_id, u2.contents, DATE_FORMAT(u2.created_date, "%Y-%m-%d") created_date 
from used_goods_board u1 
join used_goods_reply u2 on u1.board_id = u2.board_id
where DATE_FORMAT(u1.created_date, "%Y-%m") = "2022-10"
order by u2.created_date, u1.title;

# SELECT AS CREATED_DATE
# FROM USED_GOODS_BOARD A JOIN USED_GOODS_REPLY B
# ON A.BOARD_ID = B.BOARD_ID
# WHERE A.CREATED_DATE BETWEEN '2022-10-01' AND '2022-10-31'
# ORDER BY B.CREATED_DATE,A.TITLE;