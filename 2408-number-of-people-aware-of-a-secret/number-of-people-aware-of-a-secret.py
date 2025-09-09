MAX = 10**9 + 7

class Solution:
    def peopleAwareOfSecret(self, T: int, delay: int, forget: int) -> int:
        # dp[t] = how many people *learn* the secret on day t
        dp = [0] * (T + 1)
        dp[1] = 1  # on day 1, exactly 1 person knows the secret initially

        # simulate day by day
        for t in range(1, T + 1):
            people = dp[t]  # number of people who got the secret at day t

            # each of these people will start sharing the secret
            # from day (t + delay) until day (t + forget - 1)
            for x in range(t + delay, min(t + forget, T + 1)):
                dp[x] += people  # they pass the secret to new people

        # At the end of day T, some people have already forgotten.
        # Only those who learned the secret within the last `forget - 1` days still remember.
        return sum(dp[-forget:]) % MAX