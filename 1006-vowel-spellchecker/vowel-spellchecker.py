class Solution:
    def lowerIt(self, s: str) -> str:
        # Convert string to lowercase for case-insensitive comparison
        return s.lower()

    def devowel(self, s: str) -> str:
        # Replace all vowels with '#' to normalize vowel variations
        return "".join("#" if c in "aeiou" else c for c in s)

    def spellchecker(self, wordlist, queries):
        # Rule 1: Exact match (case-sensitive) → store in a set for O(1) lookup
        exact = set(wordlist)

        # Rule 2: Case-insensitive match
        # Use a dict {lowercase_word: original_word}
        lower = {}

        # Rule 3: Vowel-error match
        # Use a dict {devowel_word: original_word}
        vow = {}

        # Build the dictionaries from the given wordlist
        for w in wordlist:
            lw = self.lowerIt(w)  # lowercase version
            lower.setdefault(lw, w)  # keep the FIRST appearance
            vow.setdefault(self.devowel(lw), w)

        res = []
        for q in queries:
            if q in exact:
                # Case 1: Exact match
                res.append(q)
            elif (lq := self.lowerIt(q)) in lower:
                # Case 2: Case-insensitive match
                res.append(lower[lq])
            elif (dq := self.devowel(self.lowerIt(q))) in vow:
                # Case 3: Vowel-error match
                res.append(vow[dq])
            else:
                # Case 4: No match
                res.append("")
        return res
