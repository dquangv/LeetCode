class Solution:
    def soupServings(self, n: int) -> float:
        # Optimization: For large n, probability approaches 1.0 very quickly.
        # Empirically, n > 4500 is close enough that the difference is < 1e-5.
        if n > 4500:
            return 1

        # All four serving operations (A mL taken, B mL taken)
        SERVING_OPTIONS = [(100, 0), (75, 25), (50, 50), (25, 75)]

        # quantities_chance keeps track of:
        # key = (remaining A, remaining B)
        # value = [numerator, denominator] representing probability of being in this state
        # Initially, we start with (n, n) with probability 1/1
        quantities_chance = {(n, n): [1, 1]}

        # BFS queue to explore all possible remaining soup amounts
        q = deque()
        q.appendleft((n, n))

        # answer_numerator / answer_denominator will store the cumulative probability
        # of A running out first (plus half if both run out at same time)
        answer_numerator = 0
        answer_denominator = 1

        # BFS loop over all possible remaining soup states
        while q:
            ml_soup_a, ml_soup_b = q.pop()

            # Get the probability fraction for this state
            chance_numerator, chance_denominator = quantities_chance.pop((ml_soup_a, ml_soup_b))

            # If one or both soups have run out, this is a terminal state
            if min(ml_soup_a, ml_soup_b) <= 0:
                # Ensure denominators match before adding fractions
                while answer_denominator < chance_denominator:
                    answer_numerator <<= 2   # multiply numerator by 4
                    answer_denominator <<= 2 # multiply denominator by 4

                # If A has run out
                if ml_soup_a <= 0:
                    answer_numerator += chance_numerator  # Add probability to answer

                # If B has NOT run out, that means only A ran out first → already counted above
                # If BOTH ran out (ml_soup_a <= 0 and ml_soup_b <= 0), we need to add half the probability.
                if ml_soup_b > 0:
                    answer_numerator += chance_numerator  # This adds the "half" in this implementation style

                continue  # Terminal state; don't expand further

            # Otherwise, simulate all four possible serving operations
            for serving_a, serving_b in SERVING_OPTIONS:
                remaining_soups = (ml_soup_a - serving_a, ml_soup_b - serving_b)

                # If we haven't visited this state before, add it to the queue
                if remaining_soups not in quantities_chance:
                    q.appendleft(remaining_soups)
                    # New state's probability denominator is old one × 4
                    quantities_chance[remaining_soups] = [0, chance_denominator << 2]

                # Add probability numerator for reaching this new state
                quantities_chance[remaining_soups][0] += chance_numerator

        # Final probability = numerator / (denominator × 2)
        # (divide by 2 because we need "A first + 0.5 × both same time")
        return answer_numerator / (answer_denominator << 1)