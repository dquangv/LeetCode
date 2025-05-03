class Solution {

    private int check(int[] tops, int[] bottoms, int val) {
        int top_res = 0, bottom_res = 0;

        for (int i = 0; i < tops.length; i++) {
            if (tops[i] != val && bottoms[i] != val) 
                return -1;
            else if (tops[i] != val) 
                top_res++;
            else if (bottoms[i] != val) 
                bottom_res++;
        }

        return Math.min(top_res, bottom_res);
    }

    public int minDominoRotations(int[] tops, int[] bottoms) {
        int result = check(tops, bottoms, tops[0]);

        if (result != -1 || tops[0] == bottoms[0]) return result;

        return check(tops, bottoms, bottoms[0]);
    }
}
