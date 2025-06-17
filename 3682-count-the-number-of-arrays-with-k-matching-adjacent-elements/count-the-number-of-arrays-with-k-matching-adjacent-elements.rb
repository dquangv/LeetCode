MOD = 1_000_000_007
MAXN = 100001
$fact = Array.new(MAXN, 0)

# Giai thừa với memo
def factorial(n)
  return $fact[n] if $fact[n] != 0
  return $fact[0] = 1 if n == 0
  $fact[n] = factorial(n - 1) * n % MOD
end

# Lũy thừa nhanh (a^b % mod)
def power(a, b)
  result = 1
  base = a % MOD
  while b > 0
    result = result * base % MOD if b.odd?
    base = base * base % MOD
    b >>= 1
  end
  result
end

# Nghịch đảo modulo theo Fermat
def mod_inv(a)
  power(a, MOD - 2)
end

# C(n, k)
def comb(n, k)
  return 0 if k < 0 || k > n
  factorial(n) * mod_inv(factorial(k)) % MOD * mod_inv(factorial(n - k)) % MOD
end

# Hàm chính
def count_good_arrays(n, m, k)
  diff = n - 1 - k
  m * power(m - 1, diff) % MOD * comb(n - 1, diff) % MOD
end