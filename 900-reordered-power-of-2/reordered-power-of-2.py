class Solution:
    def reorderedPowerOf2(self, n: int) -> bool:
        count = self.digit_count(n)

        power = 1
        for _ in range(31):
            power_count = self.digit_count(power)
            if count == power_count:
                return True
            power *= 2

        return False

    def digit_count(self, n: int) -> list[int]:
        count = [0] * 10
        while n != 0:
            count[n % 10] += 1
            n //= 10
        return count
