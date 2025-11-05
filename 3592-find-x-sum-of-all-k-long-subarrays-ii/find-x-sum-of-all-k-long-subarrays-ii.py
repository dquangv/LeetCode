class Solution:
    def findXSum(self, nums: List[int], k: int, x: int) -> List[int]:
        res = []
        counter = Counter(nums[: k - 1])
        sl = SortedList(counter.keys(), key=lambda a: (-counter[a], -a))
        total = 0
        in_top_x = set()
        for i in range(min(x, len(sl))):
            total += sl[i] * counter[sl[i]]
            in_top_x.add(sl[i])

        for i in range(k - 1, len(nums)):
            if nums[i] in sl:
                sl.remove(nums[i])
            counter[nums[i]] += 1
            sl.add(nums[i])
            j = sl.bisect_left(nums[i])
            if j < x:
                if nums[i] in in_top_x:
                    total += nums[i]
                else:
                    total += nums[i] * counter[nums[i]]
                    in_top_x.add(nums[i])
            if len(sl) > x and sl[x] in in_top_x:
                total -= sl[x] * counter[sl[x]]
                in_top_x.remove(sl[x])

            res.append(total)

            sl.remove(nums[i - k + 1])
            counter[nums[i - k + 1]] -= 1
            sl.add(nums[i - k + 1])
            j = sl.bisect_left(nums[i - k + 1])
            if nums[i - k + 1] in in_top_x:
                if j >= x:
                    total -= nums[i - k + 1] * (counter[nums[i - k + 1]] + 1)
                    in_top_x.remove(nums[i - k + 1])
                else:
                    total -= nums[i - k + 1]
            if len(sl) >= x and sl[x - 1] not in in_top_x:
                total += sl[x - 1] * counter[sl[x - 1]]
                in_top_x.add(sl[x - 1])
        return res
