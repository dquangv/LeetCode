class Solution:
    def maxFreqSum(self, s: str) -> int:
        # Count frequencies of all characters
        freq = Counter(s)

        # maxCV[0] = max consonant frequency
        # maxCV[1] = max vowel frequency
        maxCV = [0] * 2

        for ch, f in freq.items():
            # Trick with bitmask:
            #   ord(ch) - 97 gives index of character in alphabet (0 for 'a', 25 for 'z')
            #   & with 0x104111 checks if the character is a vowel
            #   -> idx = True (1) if vowel, False (0) if consonant
            idx = ((1 << (ord(ch) - 97)) & 0x104111) != 0

            # Update the maximum frequency for vowel or consonant
            maxCV[int(idx)] = max(maxCV[int(idx)], f)

        # Return sum of best consonant + best vowel frequencies
        return maxCV[0] + maxCV[1]
