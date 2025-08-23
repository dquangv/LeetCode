class Solution {
    static class Key {
        int i1, j1, i2, j2, k;

        Key(int i1, int j1, int i2, int j2, int k) {
            this.i1 = i1;
            this.j1 = j1;
            this.i2 = i2;
            this.j2 = j2;
            this.k = k;
        }

        public boolean equals(Object o) {
            if (!(o instanceof Key))
                return false;

            Key other = (Key) o;

            return i1 == other.i1 && j1 == other.j1 && i2 == other.i2 && j2 == other.j2 && k == other.k;
        }

        public int hashCode() {
            return Objects.hash(i1, j1, i2, j2, k);
        }
    }

    public int minimumSum(int[][] grid) {
        Map<Key, Integer> memo = new HashMap<>();
        int m = grid.length, n = grid[0].length;

        // Helper: compute bounding rectangle of 1's in subgrid
        java.util.function.Function<int[], Integer> getOne = (arr) -> {
            int i1 = arr[0], j1 = arr[1], i2 = arr[2], j2 = arr[3];
            int minx = Integer.MAX_VALUE, maxx = Integer.MIN_VALUE;
            int miny = Integer.MAX_VALUE, maxy = Integer.MIN_VALUE;

            for (int i = i1; i <= i2; i++)
                for (int j = j1; j <= j2; j++)
                    if (grid[i][j] == 1) {
                        minx = Math.min(minx, i);
                        maxx = Math.max(maxx, i);
                        miny = Math.min(miny, j);
                        maxy = Math.max(maxy, j);
                    }

            if (minx == Integer.MAX_VALUE)
                return 0;

            return (maxx - minx + 1) * (maxy - miny + 1);
        };

        // Recursive with memoization
        class DFS {
            int getNext(int i1, int j1, int i2, int j2, int k) {
                Key key = new Key(i1, j1, i2, j2, k);

                if (memo.containsKey(key))
                    return memo.get(key);

                int res = Integer.MAX_VALUE;

                if (k == 1)
                    res = getOne.apply(new int[] { i1, j1, i2, j2 });
                else if (k == 2) {
                    for (int i = i1; i < i2; i++)
                        res = Math.min(res,
                                getNext(i1, j1, i, j2, 1) + getNext(i + 1, j1, i2, j2, 1));

                    for (int j = j1; j < j2; j++)
                        res = Math.min(res,
                                getNext(i1, j1, i2, j, 1) + getNext(i1, j + 1, i2, j2, 1));
                } else if (k == 3) {
                    for (int i = i1; i < i2; i++) {
                        res = Math.min(res,
                                getNext(i1, j1, i, j2, 1) + getNext(i + 1, j1, i2, j2, 2));
                        res = Math.min(res,
                                getNext(i1, j1, i, j2, 2) + getNext(i + 1, j1, i2, j2, 1));
                    }

                    for (int j = j1; j < j2; j++) {
                        res = Math.min(res,
                                getNext(i1, j1, i2, j, 1) + getNext(i1, j + 1, i2, j2, 2));
                        res = Math.min(res,
                                getNext(i1, j1, i2, j, 2) + getNext(i1, j + 1, i2, j2, 1));
                    }
                }

                memo.put(key, res);

                return res;
            }
        }

        DFS solver = new DFS();

        return solver.getNext(0, 0, m - 1, n - 1, 3);
    }
}
