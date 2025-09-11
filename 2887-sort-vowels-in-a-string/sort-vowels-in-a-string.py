class Solution:
    def sortVowels(self, s: str) -> str:
        vowels = {"a", "e", "i", "o", "u", "A", "E", "I", "O", "U"}

        # Step 1: Extract all vowels from s
        l = []
        s = list(s)  # convert string to list for easier modification
        for i in s:
            if i in vowels:
                l.append(i)

        # Step 2: Sort the vowels by ASCII value
        l.sort()

        # Step 3: Put sorted vowels back in their positions
        j = 0
        for i in range(len(s)):
            if s[i] in vowels:
                s[i] = l[j]  # replace vowel with next sorted vowel
                j += 1

        # Step 4: Return final string
        return "".join(s)
