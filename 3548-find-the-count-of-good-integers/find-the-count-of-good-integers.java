class Solution {
    public long factorial(int num) {
        long result = 1;
        for (int i = 1; i <= num; i++) result *= i;

        return result;
    }

    public void generatePalindromes(char[] currentNumber, int index, List<String> validPalindromes, int k) {
        if (index >= (currentNumber.length + 1) / 2) {
            String palindrome = new String(currentNumber);
            if (palindrome.charAt(0) != '0' && Long.parseLong(palindrome) % k == 0) {
                validPalindromes.add(palindrome);
            }

            return;
        }

        for (char c = '0'; c <= '9'; c++) {
            if (index == 0 && c == '0') continue;
            currentNumber[index] = c;
            currentNumber[currentNumber.length - 1 - index] = c;
            generatePalindromes(currentNumber, index + 1, validPalindromes, k);
        }
    }

    public long countGoodIntegers(int n, int k) {
        List<String> validPalindromes = new ArrayList<>();
        char[] basePalindrome = new char[n];
        Arrays.fill(basePalindrome, '0');
        generatePalindromes(basePalindrome, 0, validPalindromes, k);

        Set<String> uniquePatterns = new HashSet<>();

        for (String palindrome : validPalindromes) {
            int[] freq = new int[10];
            for (char ch : palindrome.toCharArray()) freq[ch - '0']++;
            StringBuilder pattern = new StringBuilder();
            for (int f : freq) pattern.append((char) ('a' + f));
            uniquePatterns.add(pattern.toString());
        }

        long totalPermutations = factorial(n);
        long totalValidPermutations = 0;

        for (String pattern : uniquePatterns) {
            long permutations = totalPermutations;
            for (char ch : pattern.toCharArray()) {
                permutations /= factorial(ch - 'a');
            }

            if (pattern.charAt(0) != 'a') {
                int zeroCount = pattern.charAt(0) - 'a';
                if (zeroCount > 0) {
                    long invalidPerms = factorial(n - 1);
                    for (int j = 1; j < pattern.length(); j++) {
                        invalidPerms /= factorial(pattern.charAt(j) - 'a');
                    }
                    invalidPerms /= factorial(zeroCount - 1);
                    permutations -= invalidPerms;
                }
            }

            totalValidPermutations += permutations;
        }

        return totalValidPermutations;
    }
}

// Sinh ra tất cả các số đối xứng (palindrome) có n chữ số và chia hết cho k.
// Với mỗi số palindrome, tạo ra một chuỗi pattern tần suất chữ số (ví dụ aabc với a=2, b=1, c=1).
// Với mỗi pattern, tính số hoán vị khác nhau của n chữ số tương ứng pattern đó.
// Trừ đi các hoán vị bắt đầu bằng số 0.
// Cộng tất cả các hoán vị hợp lệ lại.