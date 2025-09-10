class Solution:
    def minimumTeachings(
        self, n: int, languages: List[List[int]], friendships: List[List[int]]
    ) -> int:
        # Convert each user's languages into a set for fast intersection checks
        languages = [set(l) for l in languages]

        dontspeak = set()  # users who cannot currently communicate with a friend

        # Step 1: Identify friendships where users cannot communicate
        for u, v in friendships:
            u = u - 1  # adjust to 0-based indexing
            v = v - 1
            if languages[u] & languages[v]:
                # if they share at least one common language -> they can communicate
                continue
            # otherwise, mark both as needing teaching
            dontspeak.add(u)
            dontspeak.add(v)

        # Step 2: Count how many users (in dontspeak set) already know each language
        langcount = Counter()
        for f in dontspeak:
            for l in languages[f]:
                langcount[l] += 1

        # Step 3: We need to teach ONE language to all users in dontspeak
        # Best choice = language already known by the maximum number of them
        # So minimum teaching = total to teach - those who already know that best language
        return 0 if not dontspeak else len(dontspeak) - max(langcount.values())
