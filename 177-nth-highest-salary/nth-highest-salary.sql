CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
    declare a int;
    set a = n - 1;
  RETURN (
      # Write your MySQL query statement below.
    
    select (
        select distinct salary
        from employee
        order by salary desc
        limit 1 offset a
    ) as getNthHighestSalary
  );
END