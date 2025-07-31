class Solution:
    def subarrayBitwiseORs(self, arr: List[int]) -> int:
        seen = set()

        for i in range(len(arr)):
            seen.add(arr[i])  # Add single element

            # Go backward to compute OR results
            for j in range(i - 1, -1, -1):
                if (arr[j] | arr[i]) == arr[j]:
                    break  # Early exit
                arr[j] |= arr[i]
                seen.add(arr[j])

        return len(seen)
