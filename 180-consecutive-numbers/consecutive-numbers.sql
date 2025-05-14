# Write your MySQL query statement below
select distinct num as ConsecutiveNums 
from (
    select num,
        lag(num, 1) over (order by id) as prev,
        lag(num, 2) over (order by id) as prev2
    from logs
) t
where num = prev and num = prev2;