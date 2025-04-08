# Write your MySQL query statement below
select s1.score,
    (
        select count(distinct s2.score)
        from Scores s2
        where s2.score > s1.score
    ) + 1 as 'rank'
from Scores s1
order by s1.score desc;

-- SELECT score,
--        DENSE_RANK() OVER (ORDER BY score DESC) AS 'rank'
-- FROM Scores
-- ORDER BY score DESC;
