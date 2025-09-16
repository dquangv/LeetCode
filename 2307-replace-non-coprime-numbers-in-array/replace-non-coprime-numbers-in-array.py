class Solution:
    def replaceNonCoprimes(self, nums: List[int]) -> List[int]:
        res = []  # This will store the final result array

        # Loop through each number in the input list
        for num in nums:
            # While the result array is not empty AND the last element in res
            # has GCD > 1 with the current num (i.e., they are non-coprime)
            while res and gcd(res[-1], num) > 1:
                # Replace num with the LCM of res[-1] and num
                # Because the two adjacent non-coprime numbers should merge into one
                num = lcm(res[-1], num)

                # Remove the last element (since it merged into num)
                res.pop()

            # Push the current (possibly merged) number into res
            res.append(num)

        # Return the final modified array
        return res
