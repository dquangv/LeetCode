class Solution:
    def totalFruit(self, fruits: List[int]) -> int:
        n = len(fruits)
        last_fruit = -1
        second_last_fruit = -1
        last_count = 0
        curr_max = 0
        max_fruits = 0

        for fruit in fruits:
            # Continue collecting if fruit matches one of the two baskets
            if fruit == last_fruit or fruit == second_last_fruit:
                curr_max += 1
            else:
                # Reset current max to last sequence of the same fruit + current one
                curr_max = last_count + 1

            # If same as last fruit, increase its count
            if fruit == last_fruit:
                last_count += 1
            else:
                # Update fruit types and reset count
                last_count = 1
                second_last_fruit = last_fruit
                last_fruit = fruit

            max_fruits = max(max_fruits, curr_max)

        return max_fruits