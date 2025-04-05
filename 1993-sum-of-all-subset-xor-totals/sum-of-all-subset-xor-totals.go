func subsetXORSum(nums []int) int {
    var sum int;

    var dfs func(index, curXor int);

    dfs = func(index, curXor int) {
        if index == len(nums) {
            sum += curXor;

            return;
        }

        dfs(index + 1, curXor ^ nums[index]);
        dfs(index + 1, curXor);
    }

    dfs(0, 0);

    return sum;
}