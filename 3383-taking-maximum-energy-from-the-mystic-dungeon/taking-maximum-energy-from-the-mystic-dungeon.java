class Solution {
    public int maximumEnergy(int[] energy, int k) {
        List<Integer> memo = new ArrayList<>(Collections.nCopies(k, 0));
        for (int x : energy)
            memo.add(Math.max(x + memo.get(memo.size() - k), x));

        return Collections.max(memo.subList(memo.size() - k, memo.size()));
    }
}