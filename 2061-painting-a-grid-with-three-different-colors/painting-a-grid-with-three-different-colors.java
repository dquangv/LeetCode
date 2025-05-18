class Solution {
    private static final int MOD = 1_000_000_007;
    private List<int[]> validCols = new ArrayList<>();
    private Map<String, Integer> colIndex = new HashMap<>();
    private List<List<Integer>> transitions = new ArrayList<>();

    public int colorTheGrid(int m, int n) {
        generateValidColumns(m, new ArrayList<>());

        // map cấu hình thành index
        for (int i = 0; i < validCols.size(); i++) {
            String key = Arrays.toString(validCols.get(i));
            colIndex.put(key, i);
        }

        // tạo danh sách các transition
        for (int[] col1 : validCols) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < validCols.size(); i++) {
                int[] col2 = validCols.get(i);
                if (isCompatible(col1, col2)) {
                    list.add(i);
                }
            }
            transitions.add(list);
        }

        int k = validCols.size();
        int[] dp = new int[k];
        Arrays.fill(dp, 1);

        for (int step = 1; step < n; step++) {
            int[] newDp = new int[k];
            for (int i = 0; i < k; i++) {
                for (int j : transitions.get(i)) {
                    newDp[i] = (newDp[i] + dp[j]) % MOD;
                }
            }
            dp = newDp;
        }

        int result = 0;
        for (int val : dp) {
            result = (result + val) % MOD;
        }

        return result;
    }

    private void generateValidColumns(int m, List<Integer> col) {
        if (col.size() == m) {
            int[] arr = new int[m];
            for (int i = 0; i < m; i++) arr[i] = col.get(i);
            validCols.add(arr);
            return;
        }

        for (int color = 0; color < 3; color++) {
            if (!col.isEmpty() && col.get(col.size() - 1) == color) continue;
            col.add(color);
            generateValidColumns(m, col);
            col.remove(col.size() - 1);
        }
    }

    private boolean isCompatible(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == b[i]) return false;
        }
        
        return true;
    }
}