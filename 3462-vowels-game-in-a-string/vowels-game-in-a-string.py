class Solution:
    def doesAliceWin(self, s: str) -> bool:
        vowels = set('aeiou')

        # even = number of prefixes with even number of vowels (initially prefix = 0 → even)
        # odd = number of prefixes with odd number of vowels
        even = 1 
        odd = 0
        parity = 0  # current prefix parity (0 = even, 1 = odd)

        for ch in s:
            if ch in vowels:
                parity ^= 1  # flip parity when encountering a vowel

            # Count how many prefixes of each parity exist
            if parity == 0:
                even += 1
            else:
                odd += 1

        # If there are both even and odd prefixes, Alice wins
        # Otherwise, Bob wins (Alice has no valid move)
        return even * odd > 0