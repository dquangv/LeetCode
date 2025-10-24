class Solution:
    def nextBeautifulNumber(self, n: int) -> int:
        x = n + 1
        while True:
            s = str(x)
            if "0" not in s:
                c = Counter(s)
                if all(c[d] == int(d) for d in c):
                    return x
            x += 1
