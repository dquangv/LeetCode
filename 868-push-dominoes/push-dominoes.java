class Solution {
    public String pushDominoes(String dominoes) {
        int rightPointer = -1;
        int standCount = 0;

        char[] result = dominoes.toCharArray();

        for (int i = 0; i < dominoes.length(); i++) {
            char c = dominoes.charAt(i);

            if (c == '.') {
                standCount++;
                if (rightPointer >= 0) {
                    result[i] = 'R';
                }
            } else if (c == 'L') {
                if (rightPointer >= 0) {
                    for (int j = 0; j < standCount / 2; j++) {
                        result[i - j - 1] = 'L';
                        result[rightPointer + j + 1] = 'R';
                    }

                    if (standCount % 2 == 1) {
                        result[rightPointer + standCount / 2 + 1] = '.';
                    }

                    standCount = 0;
                } else {
                    for (int j = 1; j <= standCount; j++) {
                        result[i - j] = 'L';
                    }
                    standCount = 0;
                }
                rightPointer = -1;
            } else if (c == 'R') {
                rightPointer = i;
                standCount = 0;
            }
        }

        return new String(result);
    }
}
